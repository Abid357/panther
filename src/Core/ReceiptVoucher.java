package Core;

import java.sql.Date;

public class ReceiptVoucher {
	private int number;
	private Date date;
	private String being;
	private double amount;
	private String receiptMethod;
	private String receiptTo;
	private int invoiceNo;
	private int transactionNo;

	public ReceiptVoucher(int number, Date date, String being, double amount, String receiptMethod, String receiptTo,
			int invoiceNo, int transactionNo) {
		super();
		this.number = number;
		this.date = date;
		this.being = being;
		this.amount = amount;
		this.receiptMethod = receiptMethod;
		this.receiptTo = receiptTo;
		this.invoiceNo = invoiceNo;
		this.transactionNo = transactionNo;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBeing() {
		return being;
	}

	public void setBeing(String being) {
		this.being = being;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReceiptMethod() {
		return receiptMethod;
	}

	public void setReceiptMethod(String receiptMethod) {
		this.receiptMethod = receiptMethod;
	}

	public String getReceiptTo() {
		return receiptTo;
	}

	public void setReceiptTo(String receiptTo) {
		this.receiptTo = receiptTo;
	}

	public int getinvoiceNo() {
		return invoiceNo;
	}

	public void setinvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public int getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(int transactionNo) {
		this.transactionNo = transactionNo;
	}

	@Override
	public String toString() {
		return "ReceiptVoucher [number: " + number + ", date: " + date + ", being: " + being + ", amount: " + amount
				+ ", receiptMethod: " + receiptMethod + ", receiptTo: " + receiptTo + ", invoiceNo: "
				+ invoiceNo + ", transactionNo: " + transactionNo + "]";
	}
}
