package Core;

import java.sql.Date;

public class PaymentVoucher {
	private int number;
	private Date date;
	private String being;
	private double amount;
	private String paymentMethod;
	private String paymentTo;
	private String invoiceNo;
	private int lpoNo;
	private int transactionNo;

	public PaymentVoucher(int number, Date date, String being, double amount, String paymentMethod, String paymentTo,
			String invoiceNo, int lpoNo, int transactionNo) {
		super();
		this.number = number;
		this.date = date;
		this.being = being;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.paymentTo = paymentTo;
		this.invoiceNo = invoiceNo;
		this.lpoNo = lpoNo;
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

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentTo() {
		return paymentTo;
	}

	public void setPaymentTo(String paymentTo) {
		this.paymentTo = paymentTo;
	}

	public int getLpoNo() {
		return lpoNo;
	}

	public void setLpoNo(int lpoNo) {
		this.lpoNo = lpoNo;
	}

	public int getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(int transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Override
	public String toString() {
		return "PaymentVoucher [number: " + number + ", date: " + date + ", being: " + being + ", amount: " + amount
				+ ", paymentMethod: " + paymentMethod + ", paymentTo: " + paymentTo + ", invoiceNo: " + invoiceNo
				+ ", lpoNo: " + lpoNo + ", transactionNo: " + transactionNo + "]";
	}
}