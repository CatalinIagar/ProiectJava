package projectATM;

public class Person {
	private String firstName;
	private String lastName;
	private String dateOfBith;
	private String CNP;
	private String phoneNumber;

	public Person(String firstName, String lastName, String dateOfBirth, String CNP, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBith = dateOfBirth;
		this.CNP = CNP;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBith() {
		return dateOfBith;
	}

	public void setDateOfBith(String dateOfBith) {
		this.dateOfBith = dateOfBith;
	}

	public String getCNP() {
		return CNP;
	}

	public void setCNP(String cNP) {
		CNP = cNP;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
