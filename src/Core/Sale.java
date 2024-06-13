package Core;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Sale extends Transaction {
	private int inventoryNo;
	private int invoiceNo;
	private int transportTransactionNo;

	public Sale(int number, Date date, Time time, double amount, char type, String description, String bankAccountNo,
			String chequeNo, String entityName, ArrayList<Item> items, int inventoryNo,
			int invoiceNo, int transportTransactionNo) {
		super(number, date, time, amount, type, description, bankAccountNo, chequeNo, entityName, items);
		this.transportTransactionNo = transportTransactionNo;
		this.inventoryNo = inventoryNo;
		this.invoiceNo = invoiceNo;
	}

	public int getTransportTransactionNo() {
		return transportTransactionNo;
	}

	public void setTransportTransactionNo(int transportTransactionNo) {
		this.transportTransactionNo = transportTransactionNo;
	}

	public int getInventoryNo() {
		return inventoryNo;
	}

	public void setInventoryNo(int inventoryNo) {
		this.inventoryNo = inventoryNo;
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Override
	public String toString() {
		return "Sale [transportTransactionNo: " + transportTransactionNo + ", inventoryNo: " + inventoryNo + ", invoiceNo: " + invoiceNo + "]";
	}
}
