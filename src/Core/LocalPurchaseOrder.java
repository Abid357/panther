package Core;

import java.sql.Date;
import java.util.ArrayList;

public class LocalPurchaseOrder {
	private int number;
	private Date issueDate;
	private String invoiceNo;
	private String sendToName;
	private ArrayList<Item> items;

	public LocalPurchaseOrder(int number, Date issueDate, String invoiceNo, String sendToName,
			ArrayList<Item> items) {
		super();
		this.number = number;
		this.issueDate = issueDate;
		this.invoiceNo = invoiceNo;
		this.sendToName = sendToName;
		this.items = items;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getinvoiceNo() {
		return invoiceNo;
	}

	public void setinvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getSendToName() {
		return sendToName;
	}

	public void setSendToName(String sendToName) {
		this.sendToName = sendToName;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "LocalPurchaseOrder [number: " + number + ", issueDate: " + issueDate + ", invoiceNo: " + invoiceNo
				+ ", sendToName: " + sendToName + ", items: " + items + "]";
	}
}
