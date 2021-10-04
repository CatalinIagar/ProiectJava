package carDealership;

import java.util.ArrayList;
import java.util.Scanner;

public class Account {
	private String firstName;
	private String lastName;
	private String idNumber;
	private String phoneNumber;
	private String adress;
	private String password;
	private boolean connected = false;
	private boolean blocked = false;

	private int balance = 0;
	private ArrayList<Car> borrowedCars = new ArrayList<>();

	public Account(String firstName, String lastName, String idNumber, String adress, String phoneNumber, int balance,
			String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.balance = balance;
		this.password = password;
	}

	public Account(String firstName, String lastName, String idNumber, String adress, String phoneNumber,
			String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.password = password;
	}

	public Account() {

	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public void addBalance(int value) {
		if (value < 0) {
			return;
		}
		this.balance += value;
	}

	public void removeBalance(int value) {
		if (value < 0) {
			return;
		}
		if (this.balance - value < 0) {
			System.out.println("[Balance too low]");
			return;
		}
		this.balance -= value;
	}

	@SuppressWarnings("resource")
	public void login(Admin admin) {
		Scanner scanner = new Scanner(System.in);
		if (isBlocked()) {
			System.out.println("[Account blocked]");
			return;
		} else if (!isConnected()) {
			System.out.print("Please insert your password: ");
			String passwordString = scanner.nextLine();
			if (password.equals(passwordString)) {
				System.out.println("[Connection succesfull]");
				setConnected(true);
				accountMenu(admin);
			} else {
				System.out.println("[Incorrect password]");
			}
		}

	}

	@SuppressWarnings("resource")
	private void accountMenu(Admin admin) {
		Scanner scanner = new Scanner(System.in);
		boolean isActive = true;

		while (isActive) {
			System.out.println("[1]. Account detalis");
			System.out.println("[2]. Balance ammount");
			System.out.println("[3]. Add balance");
			System.out.println("[4]. Rent a car");
			System.out.println("[5]. Return car");
			System.out.println("[6]. Exit");
			System.out.print("Please choose the option you want: ");
			int option = scanner.nextInt();
			switch (option) {
			case 1:
				System.out.println(toString());
				break;
			case 2:
				System.out.println("[Balance amount]: " + balance);
				break;
			case 3:
				System.out.print("Please insert the value you want to add: ");
				int value = scanner.nextInt();
				addBalance(value);
				break;
			case 4:
				rentCar(admin);
				break;
			case 5:
				returnCar(admin);
				break;
			case 6:
				isActive = false;
				setConnected(false);
				break;
			default:
				break;
			}
		}
	}

	@SuppressWarnings("resource")
	private void returnCar(Admin admin) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Car> tempCars = admin.getCars();
		int nOfCars = 0;
		for (Car car : borrowedCars) {
			nOfCars++;
			System.out.println("[" + nOfCars + "]" + car.toStringRented());
		}
		if (nOfCars == 0) {
			return;
		}
		System.out.print("Select the car you want to return: ");
		int option = scanner.nextInt();
		int index = tempCars.indexOf(borrowedCars.get(option - 1));
		Car selectedCar = tempCars.get(index);
		selectedCar.setRented(false);
		borrowedCars.remove(option - 1);
	}

	@SuppressWarnings("resource")
	private void rentCar(Admin admin) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Car> tempCars = admin.getCars();
		int nOfCars = 0;
		for (Car car : tempCars) {
			nOfCars++;
			System.out.println("[" + nOfCars + "]: " + car.toString() + ", [is rented]: " + car.isRented());
		}
		System.out.print("Select the car you want to rent: ");
		int option = scanner.nextInt();

		Car selectedCar = tempCars.get(option - 1);

		if (!selectedCar.isRented()) {
			if (this.balance < selectedCar.getPriceToRent()) {
				System.out.println("Your balance is too low.");
			} else {
				System.out.print("Are you sure you want to rent this car? Y/N : ");
				String optionString = scanner.next();
				if (optionString.equals("n") || optionString.equals("N")) {
					return;
				} else if (optionString.equals("y") || optionString.equals("Y")) {
					borrowedCars.add(selectedCar);
					selectedCar.setRented(true);
					this.balance -= selectedCar.getPriceToRent();
					admin.addBalance(selectedCar.getPriceToRent());
					return;
				} else {
					return;
				}
			}
		} else {
			System.out.println("The car is already rented.");
		}
	}

	public String toStringShort() {
		return "First name: " + this.firstName + " Last name: " + this.lastName;
	}

	@Override
	public String toString() {
		return "[First name] " + this.firstName + "\n[Last name] " + this.lastName + "\n[Adress] " + this.adress
				+ "\n[ID Number] " + this.idNumber + "\n[Phone Number] " + this.phoneNumber + "\n[Balance] "
				+ this.balance;
	}
}
