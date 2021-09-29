package projectATM;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
	private String passwordString = "123";
	private ArrayList<Account> accounts = new ArrayList<Account>();
	private boolean verified = false;

	public Admin(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	public void login(String adminPassword) {
		if (this.passwordString.equals(adminPassword)) {
			setVerified(true);
			System.out.println("Conectare reusita");
			adminMenu();
		}
		System.out.println("Parola incorecta");
	}

	@SuppressWarnings("resource")
	private void adminMenu() {
		Scanner scanner = new Scanner(System.in);

		while (isVerified()) {
			System.out.println("1. Stergere cont");
			System.out.println("2. Deblocare cont");
			System.out.println("3. Iesire");
			System.out.print("Alegeti operatia dorita: ");
			int option = scanner.nextInt();

			switch (option) {
			case 1:
				deleteAccount();
				break;
			case 2:
				unlockAccount();
				break;
			case 3:
				setVerified(false);
				break;
			default:
				break;
			}
		}
	}

	@SuppressWarnings("resource")
	private void unlockAccount() {
		Scanner scanner = new Scanner(System.in);
		int i = 1;
		if (accounts.size() != 0) {
			for (Account account : accounts) {
				if (account.isBlocked()) {
					System.out.println("[" + i + "]");
					System.out.println(account.toStringShort());
					i++;
				}
			}

			if (i != 1) {
				System.out.println("Alegeti contul care doriti sa il deblocati: ");
				int option = scanner.nextInt();
				Account account = new Account();
				account = accounts.get(option - 1);
				account.setBlocked(false);
			} else {
				System.out.println("Nu exista conturi blocate.");
			}
		} else {
			System.out.println("Nu exista conturi create.");
		}
	}

	@SuppressWarnings("resource")
	private void deleteAccount() {
		Scanner scanner = new Scanner(System.in);
		int i = 1;
		if (accounts.size() != 0) {
			for (Account account : accounts) {
				System.out.println("[" + i + "]");
				System.out.println(account.toStringShort());
				i++;
			}
			System.out.println("Alegeti contul care doriti sa il stergeti: ");
			int option = scanner.nextInt();
			accounts.remove(option - 1);
		} else {
			System.out.println("Nu exista conturi create.");
		}
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isVerified() {
		return verified;
	}
}
