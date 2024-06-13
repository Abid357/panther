package Core;

public class Investor extends Entity {
	private double invested;
	private double profited;
	private float profitRate;

	public Investor(String name, String contactPerson, String contactNumber, String location, double invested,
			double profited, float profitRate) {
		super(name, contactPerson, contactNumber, location);
		this.invested = invested;
		this.profited = profited;
		this.profitRate = profitRate;
	}

	public double getInvested() {
		return invested;
	}

	public void setInvested(double invested) {
		this.invested = invested;
	}

	public double getProfited() {
		return profited;
	}

	public void setProfited(double profited) {
		this.profited = profited;
	}

	public float getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(float profitRate) {
		this.profitRate = profitRate;
	}

	@Override
	public String toString() {
		return "Investor [invested: " + invested + ", profited: " + profited + ", profitRate: " + profitRate + "]";
	}
}
