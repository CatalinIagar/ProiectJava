package carDealership;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
	private String password = "abc123xyz@";
	private ArrayList<Account> accounts;
	private ArrayList<Car> cars;
	private boolean connected = false;
	private int balance = 0;

	public boolean isConnected() {
		return connected;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public ArrayList<Car> getCars() {
		return cars;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public int getBalance() {
		return balance;
	}

	public void addBalance(int value) {
		if (value < 0) {
			return;
		}
		this.balance += value;
	}

	@SuppressWarnings("resource")
	public void login(ArrayList<Account> accounts, ArrayList<Car> cars) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please insert password: ");
		String password = scanner.nextLine();

		if (!isConnected()) {
			if (password.equals(this.password)) {
				System.out.println("Login succesfull");
				setConnected(true);
				adminMenu();
			} else {
				System.out.println("[Incorrect password]");
			}
		}
	}

	@SuppressWarnings("resource")
	private void adminMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean isActive = true;

		while (isActive) {
			System.out.println("[1]. List of all cars");
			System.out.println("[2]. List of all accounts");
			System.out.println("[3]. Block account");
			System.out.println("[4]. Unblock account");
			System.out.println("[5]. Add car");
			System.out.println("[6]. Change account password");
			System.out.println("[7]. Show Balance");
			System.out.println("[8]. Exit");
			System.out.print("Please select your option: ");
			int option = scanner.nextInt();
			switch (option) {
			case 1:
				carList();
				break;
			case 2:
				accountLists();
				break;
			case 3:
				blockAccount();
				break;
			case 4:
				unblockAccount();
				break;
			case 5:
				addCar();
				break;
			case 6:
				changePassword();
				break;
			case 7:
				System.out.println("[Balance]: " + getBalance());
				break;
			case 8:
				setConnected(false);
				isActive = false;
				break;

			default:
				break;
			}
		}

	}

	@SuppressWarnings("resource")
	private void unblockAccount() {
		Scanner scanner = new Scanner(System.in);
		int nOfAccounts = 0;
		for (Account account : accounts) {
			nOfAccounts++;
			System.out.println(
					"[" + nOfAccounts + "] " + account.toStringShort() + ", [is blocked]: " + account.isBlocked());
		}
		System.out.print("Select the account you want to unblock: ");
		int option = scanner.nextInt();
		Account selectedAccount = accounts.get(option - 1);
		if (!selectedAccount.isBlocked()) {
			System.out.println("The account is already unblocked.");
		} else {
			System.out.println("Account unblocked.");
			selectedAccount.setBlocked(false);
		}
	}

	@SuppressWarnings("resource")
	private void blockAccount() {
		Scanner scanner = new Scanner(System.in);
		int nOfAccounts = 0;
		for (Account account : accounts) {
			nOfAccounts++;
			System.out.println(
					"[" + nOfAccounts + "] " + account.toStringShort() + ", [is blocked]: " + account.isBlocked());
		}
		System.out.print("Select the account you want to block: ");
		int option = scanner.nextInt();
		Account selectedAccount = accounts.get(option - 1);
		if (selectedAccount.isBlocked()) {
			System.out.println("The account is already blocked.");
		} else {
			System.out.println("Account blocked.");
			selectedAccount.setBlocked(true);
		}
	}

	@SuppressWarnings("resource")
	private void addCar() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Please insert the make of the vehicle: ");
		String make = scanner.nextLine();
		System.out.print("Please insert the model of the vehicle: ");
		String model = scanner.nextLine();
		System.out.print("Please insert the year of the vehicle: ");
		int year = scanner.nextInt();
		System.out.print("Please insert the rent price: ");
		int rentPrice = scanner.nextInt();

		Car newCar = new Car(make, model, year, rentPrice);

		cars.add(newCar);
	}

	@SuppressWarnings("resource")
	private void changePassword() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please insert your old password: ");
		String oldPassword = scanner.nextLine();

		if (oldPassword.equals(this.password)) {
			System.out.print("Please insert your new password: ");
			String newPassword = scanner.nextLine();
			this.password = newPassword;
		} else {
			System.out.println("Wrong password");
		}

	}

	private void accountLists() {
		int nOfAccounts = 0;
		for (Account account : accounts) {
			nOfAccounts++;
			System.out.println("[" + nOfAccounts + "]");
			System.out.println(account.toString());
		}
	}

	private void carList() {
		int nOfCars = 0;
		for (Car car : cars) {
			nOfCars++;
			System.out.println("[" + nOfCars + "]");
			System.out.println(car.toString());
		}
	}
}
