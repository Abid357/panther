package Core;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Promotion extends Transaction {
	private int inventoryNo;
	private boolean isSample;

	public Promotion(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items,
			int inventoryNo, boolean isSample) {
		super(number, date, time, amount, type, description, bankAccountNo, chequeNo, entityName, items);
		this.inventoryNo = inventoryNo;
		this.isSample = isSample;
	}

	public int getInventoryNo() {
		return inventoryNo;
	}

	public void setInventoryNo(int inventoryNo) {
		this.inventoryNo = inventoryNo;
	}

	public boolean isSample() {
		return isSample;
	}

	public void setSample(boolean isSample) {
		this.isSample = isSample;
	}

	@Override
	public String toString() {
		return "Promotion [promoted: " + ", inventoryNo: " + inventoryNo + ", isSample: " + isSample + "]";
	}
}
