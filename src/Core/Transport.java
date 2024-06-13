package Core;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Transport extends Transaction {
	private String toLocation;
	private String fromLocation;
	private int inventoryNo;

	public Transport(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items, String toLocation,
			String fromLocation, int inventoryNo) {
		super(number, date, time, amount, type, description, bankAccountNo, chequeNo, entityName, items);
		this.toLocation = toLocation;
		this.fromLocation = fromLocation;
		this.inventoryNo = inventoryNo;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public int getInventoryNo() {
		return inventoryNo;
	}

	public void setInventoryNo(int inventoryNo) {
		this.inventoryNo = inventoryNo;
	}

	@Override
	public String toString() {
		return "Transport [toLocation: " + toLocation + ", fromLocation: " + fromLocation + "]";
	}
}
