package projectATM;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		Scanner scanner = new Scanner(System.in);
		boolean test = true;

		while (test) {
			System.out.println("1. Creare cont nou");
			System.out.println("2. Conectare cont existent");
			System.out.println("3. Conectare admin");
			System.out.println("4. Iesire");
			System.out.print("Alegeti operatia dorita: ");
			int option = scanner.nextInt();
			for (int i = 0; i < 5; i++) {
				System.out.println();
			}
			switch (option) {
			case 1:
				addAccounts(accounts);
				break;
			case 2:
				connectAccount(accounts);
				break;
			case 3:
				Admin admin = new Admin(accounts);
				System.out.print("Conectare cont admin, introduceti parola: ");
				String adminPassword = scanner.next();
				admin.login(adminPassword);
				break;
			case 4:
				test = false;
				break;
			default:
				for (int i = 0; i < 10; ++i)
					System.out.println();
				System.out.println("Optiune gresita. Incercati din nou.");
				break;
			}
		}
	}

	@SuppressWarnings("resource")
	private static void connectAccount(ArrayList<Account> accounts) {
		Scanner scanner = new Scanner(System.in);
		int i = 1;

		for (Account account : accounts) {
			System.out.println(i + " " + account.toStringShort());
			i++;
		}

		System.out.print("Alegeti contul la care doriti sa va conectati: ");
		int option = scanner.nextInt();
		Account account = new Account();
		account = accounts.get(option - 1);
		accountLogin(account);
	}

	@SuppressWarnings("resource")
	private static void accountLogin(Account account) {
		Scanner scanner = new Scanner(System.in);
		int nOfTimes = 1;

		while (!account.isVerified()) {

			if (account.isBlocked()) {
				System.out.println("Contul este blocat.");
				break;
			}

			System.out.println("Introduceti parola: ");
			String password = scanner.nextLine();
			String currentPassword = account.getPassword();
			if (password.equals(currentPassword)) {
				System.out.println("Conectare reusita.");
				account.setVerified(true);
				account.menu();
			}
			System.out.println("Parola gresita, va rugam reincercati.");
			nOfTimes++;
			if (nOfTimes == 3) {
				account.setBlocked(true);
			}
		}
	}

	@SuppressWarnings("resource")
	public static ArrayList<Account> addAccounts(ArrayList<Account> accounts) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Introduceti numele de familie: ");
		String firstName = scanner.nextLine();
		System.out.print("Introduceti prenumle: ");
		String lastName = scanner.nextLine();
		System.out.print("Introduceti data nasterii(ZZ-MM-AAAA): ");
		String dateOfBirth = scanner.nextLine();
		System.out.print("Introduceti CNP-ul: ");
		String CNP = scanner.nextLine();
		System.out.print("Introduceti un numar de telefon: ");
		String phoneNumber = scanner.nextLine();
		System.out.print("Introduceti parola dorita(minim 8 caractere): ");
		String password = scanner.nextLine();

		if (firstName.isEmpty()) {
			System.out.println("Numele introdus este gresit. Va rugam reincercati.");
			return accounts;
		}
		if (lastName.isEmpty()) {
			System.out.println("Prenumele introdus este gresit. Va rugam reincercati.");
			return accounts;
		}
		if (dateOfBirth.isEmpty()) {
			System.out.println("Data nasterii introdusa gresit. Va rugam reincercati.");
			return accounts;
		}
		if (CNP.length() != 13) {
			System.out.println("CNP-ul introdus este gresit. Va rugam reincercati.");
			return accounts;
		}
		if (phoneNumber.length() != 10) {
			System.out.println("Numarul de telefon introdus este gresit. Va rugam reincercati.");
			return accounts;
		}
		if (password.length() < 8) {
			System.out.println("Parola introdusa este prea scurta. Va rugam reincercati.");
			return accounts;
		}
		Person person = new Person(firstName, lastName, dateOfBirth, CNP, phoneNumber);
		Account account = new Account(person, password);

		accounts.add(account);

		return accounts;
	}
}
