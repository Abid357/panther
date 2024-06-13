package Core;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Purchase extends Transaction {
	private int transportTransactionNo;

	public Purchase(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items,
			int transportTransactionNo) {
		super(number, date, time, amount, type, description, bankAccountNo, chequeNo, entityName, items);
		this.transportTransactionNo = transportTransactionNo;
	}

	public int getTransportTransactionNo() {
		return transportTransactionNo;
	}

	public void setTransportTransactionNo(int transportTransactionNo) {
		this.transportTransactionNo = transportTransactionNo;
	}

	@Override
	public String toString() {
		return "Purchase [transportTransactionNo: " + transportTransactionNo + ", inventoryNo: " + "]";
	}
}
