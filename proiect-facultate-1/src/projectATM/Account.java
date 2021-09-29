package projectATM;

import java.util.ArrayList;
import java.util.Scanner;

public class Account {
	private Person person;
	private String password;
	private ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
	private boolean verified = false;
	private boolean blocked = false;

	public Account(Person person, String password) {
		this.person = person;
		this.password = password;
	}

	public Account() {
	}

	@Override
	public String toString() {
		return "Nume: " + person.getFirstName() + "\nPrenume: " + person.getLastName() + "\nData nasterii: "
				+ person.getDateOfBith() + "\nCNP: " + person.getCNP() + "\nNumar de telefon: "
				+ person.getPhoneNumber();
	}

	public String toStringShort() {
		return "Nume: " + person.getFirstName() + " Prenume: " + person.getLastName();
	}

	public String getPassword() {
		return password;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	@SuppressWarnings("resource")
	public void menu() {
		Scanner scanner = new Scanner(System.in);
		boolean test = true;

		while (test) {
			System.out.println("1.Vizulaizare date cont.");
			System.out.println("2.Actualizare date cont.");
			System.out.println("3.Creare cont bancar nou.");
			System.out.println("4.Utilizare cont bancar existent.");
			System.out.println("5.Iesire");
			System.out.print("Alegeti optiunea doirta: ");

			int option = scanner.nextInt();
			switch (option) {
			case 1:
				System.out.println(toString());
				break;
			case 2:
				updateAccount();
				break;
			case 3:
				createBankAccount();
				break;
			case 4:
				useBankAccount();
				break;
			case 5:
				test = false;
				break;
			default:
				break;
			}
		}
	}

	@SuppressWarnings("resource")
	private void useBankAccount() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Lista conturi existente: ");
		int i = 1;
		for (BankAccount bankAccount : bankAccounts) {
			System.out.println("[" + i + "]");
			System.out.println(bankAccount.toString());
			i++;
		}
		System.out.println("Alegeti contul dorit: ");
		int option = scanner.nextInt();
		BankAccount selectedBankAccount = new BankAccount();
		selectedBankAccount = bankAccounts.get(option - 1);

		bankAccountLogin(selectedBankAccount);
	}

	@SuppressWarnings("resource")
	private void bankAccountLogin(BankAccount selectedBankAccount) {
		Scanner scanner = new Scanner(System.in);
		int pin;
		boolean test = true;

		while (test) {
			System.out.println("Introduceti pin-ul: ");
			pin = scanner.nextInt();
			int currentPin = selectedBankAccount.getPin();
			if (pin == currentPin) {
				test = false;
				bankAccountMenu(selectedBankAccount);
				break;
			}
			System.out.println("Pin-ul introdus este gresit, va rugam reincercati.");
		}

	}

	@SuppressWarnings("resource")
	private void bankAccountMenu(BankAccount selectedBankAccount) {
		Scanner scanner = new Scanner(System.in);
		boolean test = true;

		while (test) {
			System.out.println("1. Interogare sold");
			System.out.println("2. Depunere sold");
			System.out.println("3. Retragere sold");
			System.out.println("4. Iesire");
			System.out.print("Alegeti operatia dorita: ");
			int option = scanner.nextInt();

			switch (option) {
			case 1:
				System.out.println("Soldul este: " + selectedBankAccount.getBalance());
				break;
			case 2:
				System.out.println("Introduceti valoarea dorita: ");
				int depositAmount = scanner.nextInt();
				selectedBankAccount.setBalance(depositAmount);
				break;
			case 3:
				System.out.println("Introduceti suma care doriti sa o retrageti: ");
				int withdrawAmount = scanner.nextInt();
				selectedBankAccount.withdraw(withdrawAmount);
				break;
			case 4:
				test = false;
				break;
			default:
				break;
			}
		}
	}

	@SuppressWarnings("resource")
	private void createBankAccount() {
		Scanner scanner = new Scanner(System.in);
		boolean test = false;
		String name = null;
		int pin = -1;

		while (!test) {
			test = true;
			System.out.print("Introduceti numele contului dorit: ");
			name = scanner.nextLine();
			System.out.print("Introduceti pin-ul contului dorit(4 cifre): ");
			pin = scanner.nextInt();
			if (name.isEmpty()) {
				System.out.println("Numele introdus este gresit.");
				test = false;
			}
			if (pin <= 1000 || pin >= 10000) {
				System.out.println("Pinul introdus este gresit.");
				test = false;
			}
		}

		BankAccount bankAccount = new BankAccount(name, pin);
		bankAccounts.add(bankAccount);

		System.out.println(bankAccount.toString());
	}

	@SuppressWarnings("resource")
	private void updateAccount() {
		Scanner scanner = new Scanner(System.in);
		boolean test = true;

		while (test) {
			String string;
			System.out.println("1. Nume");
			System.out.println("2. Prenume");
			System.out.println("3. Data Nasterii");
			System.out.println("4. CNP");
			System.out.println("5. Numar telefon");
			System.out.println("6. Iesire");
			System.out.print("Alegeti optiunea dorita: ");

			int option = scanner.nextInt();
			switch (option) {
			case 1:
				System.out.println("Introduceti numele nou dorit: ");
				string = scanner.next();
				person.setFirstName(string);
				break;
			case 2:
				System.out.println("Introduceti prenumele nou dorit: ");
				string = scanner.next();
				person.setLastName(string);
				break;
			case 3:
				System.out.println("Introduceti noua data de nastere(ZZ-LL-AAAA): ");
				string = scanner.next();
				person.setDateOfBith(string);
				break;
			case 4:
				System.out.println("Introduceti noul CNP: ");
				string = scanner.next();
				person.setCNP(string);
				break;
			case 5:
				System.out.println("Introduceti noul numar de telefon: ");
				string = scanner.next();
				person.setPhoneNumber(string);
				break;
			case 6:
				test = false;
				break;
			default:
				break;
			}
		}
	}

}
