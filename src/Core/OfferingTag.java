package Core;

public class OfferingTag extends PriceTag {
	private String offeringName;
	private String supplierName;

	public OfferingTag(int iD, double price, String tag, String offeringName, String supplierName) {
		super(iD, price, tag);
		this.offeringName = offeringName;
		this.supplierName = supplierName;
	}

	public String getOfferingName() {
		return offeringName;
	}

	public void setOfferingName(String offeringName) {
		this.offeringName = offeringName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Override
	public String toString() {
		return "OfferingTag [offeringName: " + offeringName + ", supplierName: " + supplierName + "]";
	}
}
