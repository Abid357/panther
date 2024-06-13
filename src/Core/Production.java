package Core;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class Production {
	private int ID;
	private Date startDate;
	private Date endDate;
	private int cartons;
	private String notes;
	private int productTag;
	private int transportTransactionNo;
	private HashMap<Integer, ArrayList<Item>> items;


	public Production(int iD, Date startDate, Date endDate, int cartons, String notes, int productTag,
			int transportTransactionNo, HashMap<Integer, ArrayList<Item>> items) {
		super();
		ID = iD;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cartons = cartons;
		this.notes = notes;
		this.productTag = productTag;
		this.transportTransactionNo = transportTransactionNo;
		this.items = items;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getCartons() {
		return cartons;
	}

	public void setCartons(int cartons) {
		this.cartons = cartons;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getProductTag() {
		return productTag;
	}

	public void setProductTag(int productTag) {
		this.productTag = productTag;
	}

	public int getTransportTransactionNo() {
		return transportTransactionNo;
	}

	public void setTransportTransactionNo(int transportTransactionNo) {
		this.transportTransactionNo = transportTransactionNo;
	}

	public HashMap<Integer, ArrayList<Item>> getItems() {
		return items;
	}

	public void setItems(HashMap<Integer, ArrayList<Item>> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Production [ID: " + ID + ", startDate: " + startDate + ", endDate: " + endDate + ", cartons: " + cartons
				+ ", notes: " + notes + ", productTag: " + productTag + ", transportTransactionNo: "
				+ transportTransactionNo + ", items: " + items + "]";
	}
}
