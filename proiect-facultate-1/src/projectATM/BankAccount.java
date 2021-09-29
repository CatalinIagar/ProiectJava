package projectATM;

public class BankAccount {
	private String name;
	private int balance;
	private int pin;

	public BankAccount(String name, int pin) {
		this.name = name;
		this.pin = pin;
		this.balance = 0;
	}

	public BankAccount() {
	}

	@Override
	public String toString() {
		return "Nume cont bancar: " + this.name + "\nBalanta cont bancar: " + this.balance;
	}

	public int getBalance() {
		return balance;
	}

	public int getPin() {
		return pin;
	}

	public void setBalance(int balance) {
		if (balance <= 0) {
			System.out.println("Eroare introducere sold");
		} else {
			this.balance += balance;
		}
	}

	public void withdraw(int withdrawAmount) {
		if (withdrawAmount < 0) {
			System.out.println("Eroare suma introdusa.");
		} else if (this.balance < withdrawAmount) {
			System.out.println("Sold insuficient.");
		} else {
			this.balance -= withdrawAmount;
			System.out.println("Suma retrasa cu succes.");
		}

	}
}
