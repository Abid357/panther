package Core;

public class Pocket {
	private String name;
	private double balance;

	public Pocket(String name, double balance) {
		super();
		this.name = name;
		this.balance = balance;
	}

	public Pocket(Pocket pocket) {
		this.name = pocket.name;
		this.balance = pocket.balance;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Pocket [name: " + name + ", balance: " + balance + "]";
	}
}
