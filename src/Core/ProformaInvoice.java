package Core;

import java.sql.Date;
import java.util.ArrayList;

public class ProformaInvoice {
	private int number;
	private Date issueDate;
	private Date validity;
	private String shippingLocation;
	private String shippingMethod;
	private String shippingTerms;
	private String paymentTerms;
	private String notes;
	private String consignee;
	private String lpoNo;
	private ArrayList<Item> items;

	public ProformaInvoice(int number, Date issueDate, Date validity, String shippingLocation, String shippingMethod,
			String shippingTerms, String paymentTerms, String notes, String consignee, String lpoNo,
			ArrayList<Item> items) {
		super();
		this.number = number;
		this.issueDate = issueDate;
		this.validity = validity;
		this.shippingLocation = shippingLocation;
		this.shippingMethod = shippingMethod;
		this.shippingTerms = shippingTerms;
		this.paymentTerms = paymentTerms;
		this.notes = notes;
		this.consignee = consignee;
		this.lpoNo = lpoNo;
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

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public String getShippingLocation() {
		return shippingLocation;
	}

	public void setShippingLocation(String shippingLocation) {
		this.shippingLocation = shippingLocation;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public String getShippingTerms() {
		return shippingTerms;
	}

	public void setShippingTerms(String shippingTerms) {
		this.shippingTerms = shippingTerms;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getLpoNo() {
		return lpoNo;
	}

	public void setLpoNo(String lpoNo) {
		this.lpoNo = lpoNo;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ProformaInvoice [number: " + number + ", issueDate: " + issueDate + ", validity: " + validity
				+ ", shippingLocation: " + shippingLocation + ", shippingMethod: " + shippingMethod
				+ ", shippingTerms: " + shippingTerms + ", paymentTerms: " + paymentTerms + ", notes: " + notes
				+ ", consignee: " + consignee + ", lpoNo: " + lpoNo + ", items: " + items + "]";
	}
}
