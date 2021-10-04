package carDealership;

public class Car {
	private String make;
	private String model;
	private int year;
	private int priceToRent;
	private boolean rented = false;

	public Car(String make, String model, int year, int priceToRent) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.priceToRent = priceToRent;
	}

	@Override
	public String toString() {
		return "[Make]: " + this.make + " [Model]: " + this.model + " [Year]: " + this.year + " [Price to rent]: "
				+ this.priceToRent + " [isRented]: " + isRented();
	}

	public String toStringRented() {
		return "[Make]: " + this.make + " [Model]: " + this.model + " [Year]: " + this.year;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public int getPriceToRent() {
		return priceToRent;
	}
}
