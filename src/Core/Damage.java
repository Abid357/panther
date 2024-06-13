package Core;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Damage extends Transaction {
	private int inventoryNo;

	public Damage(int number, Date date, Time time, double amount, char type, String description, String bankAccountNo,
			String chequeNo, String entityName, ArrayList<Item> items, int inventoryNo) {
		super(number, date, time, amount, type, description, bankAccountNo, chequeNo, entityName, items);
		this.inventoryNo = inventoryNo;
	}

	public int getInventoryNo() {
		return inventoryNo;
	}

	public void setInventoryNo(int inventoryNo) {
		this.inventoryNo = inventoryNo;
	}

	@Override
	public String toString() {
		return "Damage [inventoryNo: " + inventoryNo + "]";
	}
}
