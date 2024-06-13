package Core;

import java.util.ArrayList;

public class Supplier extends Entity {
	int inventoryNo;
	private ArrayList<Offering> offerings;

	public Supplier(String name, String contactPerson, String contactNumber, String location,
			int inventoryNo, ArrayList<Offering> offerings) {
		super(name, contactPerson, contactNumber, location);
		this.inventoryNo = inventoryNo;
		this.offerings = offerings;
	}

	public ArrayList<Offering> getOfferings() {
		return offerings;
	}

	public void setOfferings(ArrayList<Offering> offerings) {
		this.offerings = offerings;
	}

	public int getInventoryNo() {
		return inventoryNo;
	}

	public void setInventoryNo(int inventoryNo) {
		this.inventoryNo = inventoryNo;
	}

	@Override
	public String toString() {
		return "Supplier [inventoryNo: " + inventoryNo + ", offerings: " + offerings + "]";
	}
}
