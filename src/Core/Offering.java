package Core;

public class Offering {
	private String offeringName;
	private char type;
	private String supplierName;

	public Offering(String offeringName, char type, String supplierName) {
		super();
		this.offeringName = offeringName;
		this.type = type;
		this.supplierName = supplierName;
	}

	public String getOfferingName() {
		return offeringName;
	}

	public void setOfferingName(String offeringName) {
		this.offeringName = offeringName;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Override
	public String toString() {
		return "Offering [offeringName: " + offeringName + ", type: " + type + ", supplierName: " + supplierName + "]";
	}
}