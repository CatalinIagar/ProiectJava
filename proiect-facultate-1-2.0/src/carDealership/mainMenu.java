package carDealership;

import java.util.ArrayList;
import java.util.Scanner;

public class mainMenu {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Account> accounts = new ArrayList<>();
		Account newAccount = new Account("dani", "dani", "1234567890123", "sat", "1234567890", 1000, "12345678");
		accounts.add(newAccount);
		ArrayList<Car> cars = new ArrayList<>();
		Admin admin = new Admin();
		admin.setAccounts(accounts);
		admin.setCars(cars);
		boolean isActive = true;

		cars = premadeCars(cars);

		while (isActive) {
			System.out.println("[Car dealership]");
			System.out.println("[1]. Create new account");
			System.out.println("[2]. Connect to an account");
			System.out.println("[3]. Connect as admin");
			System.out.println("[4]. Exit]");
			System.out.print("Select your option: ");
			int option = scanner.nextInt();

			switch (option) {
			case 1:
				accounts = createAccount(accounts);
				break;
			case 2:
				connectAccountList(accounts, admin);
				break;
			case 3:
				admin.login(accounts, cars);
				break;
			case 4:
				isActive = false;
				break;
			default:
				System.out.println("[Wrong option]");
				break;
			}
		}
	}

	private static ArrayList<Car> premadeCars(ArrayList<Car> cars) {
		Car car1 = new Car("BMW", "M3", 2015, 500);
		Car car2 = new Car("Dacia", "Logan", 2020, 100);
		Car car3 = new Car("Mazda", "MX-5", 2001, 50);
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		return cars;
	}

	@SuppressWarnings("resource")
	private static void connectAccountList(ArrayList<Account> accounts, Admin admin) {
		Scanner scanner = new Scanner(System.in);
		int nOfAccounts = 0;
		for (Account account : accounts) {
			nOfAccounts++;
			System.out.println("[" + nOfAccounts + "] " + account.toStringShort());
		}
		System.out.print("Choose the account you want to connect to: ");
		int option = scanner.nextInt();
		Account currentAccount = accounts.get(option - 1);
		currentAccount.login(admin);
	}

	@SuppressWarnings("resource")
	private static ArrayList<Account> createAccount(ArrayList<Account> accounts) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Please insert the password you want (at least 8 charaters): ");
		String password = scanner.nextLine();
		if (password.length() < 8) {
			System.out.println("[Password is not long enough]");
			return accounts;
		}

		System.out.print("Please insert your first name: ");
		String firstName = scanner.nextLine();
		if (firstName.isEmpty()) {
			System.out.println("[First name not valid]");
			return accounts;
		}

		System.out.print("Please insert your last name: ");
		String lastName = scanner.nextLine();
		if (lastName.isEmpty()) {
			System.out.println("[Last name not valid]");
			return accounts;
		}

		System.out.print("Please insert your ID number: ");
		String idNumber = scanner.nextLine();
		if (idNumber.length() != 13) {
			System.out.println("[ID number not valid 1]");
			return accounts;
		}
		for (int i = 0; i < idNumber.length(); i++) {
			char temp = idNumber.charAt(i);
			if (temp < '0' && temp > '9') {
				System.out.println("[ID number not valid 2]");
				return accounts;
			}
		}

		System.out.print("Please insert you home adress: ");
		String adress = scanner.nextLine();
		if (adress.isEmpty()) {
			System.out.println("[Adress not valid]");
			return accounts;
		}

		System.out.print("Please insert your phone number: ");
		String phoneNumber = scanner.nextLine();
		if (phoneNumber.length() != 10) {
			System.out.println("[Phone number not valid]");
			return accounts;
		}
		for (int i = 0; i < phoneNumber.length(); i++) {
			char temp = phoneNumber.charAt(i);
			if (temp < '0' && temp > '9') {
				System.out.println("[Phone number not valid]");
				return accounts;
			}
		}

		System.out.println("Do you want to add money to your account?");
		System.out.print("Y/N: ");
		String value = scanner.nextLine();

		if (value.equals("N") || value.equals("n")) {
			Account newAccount = new Account(firstName, lastName, idNumber, adress, phoneNumber, password);
			accounts.add(newAccount);
		} else if (value.equals("Y") || value.equals("y")) {
			System.out.println("Please insert the amount you want to deposit: ");
			int deposit = scanner.nextInt();
			Account newAccount = new Account(firstName, lastName, idNumber, adress, phoneNumber, deposit, password);
			accounts.add(newAccount);
		} else {
			System.out.println("[Wrong option]");
			return accounts;
		}

		System.out.println("[Account succesfully created]");

		return accounts;
	}

}
