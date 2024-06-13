package Core;

public class Loaner extends Entity {
	private double balance;

	public Loaner(String name, String contactPerson, String contactNumber, String location, double balance) {
		super(name, contactPerson, contactNumber, location);
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Loaner [balance: " + balance + "]";
	}
}
