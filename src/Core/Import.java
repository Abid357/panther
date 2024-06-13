package Core;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Import extends Transaction {
	private String toLocation;
	private String fromLocation;
	private double customDuty;
	private double deliveryOrder;
	private double clearingAgent;
	private double token;
	private double THC;
	private int transportTransactionNo;

	public Import(int number, Date date, Time time, double amount, char type, String description, String bankAccountNo,
			String chequeNo, String entityName, ArrayList<Item> items, String toLocation, String fromLocation,
			double customDuty, double deliveryOrder, double clearingAgent, double token, double tHC,
			int transportTransactionNo) {
		super(number, date, time, amount, type, description, bankAccountNo, chequeNo, entityName, items);
		this.toLocation = toLocation;
		this.fromLocation = fromLocation;
		this.customDuty = customDuty;
		this.deliveryOrder = deliveryOrder;
		this.clearingAgent = clearingAgent;
		this.token = token;
		this.THC = tHC;
		this.transportTransactionNo = transportTransactionNo;
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

	public double getCustomDuty() {
		return customDuty;
	}

	public void setCustomDuty(double customDuty) {
		this.customDuty = customDuty;
	}

	public double getDeliveryOrder() {
		return deliveryOrder;
	}

	public void setDeliveryOrder(double deliveryOrder) {
		this.deliveryOrder = deliveryOrder;
	}

	public double getClearingAgent() {
		return clearingAgent;
	}

	public void setClearingAgent(double clearingAgent) {
		this.clearingAgent = clearingAgent;
	}

	public double getToken() {
		return token;
	}

	public void setToken(double token) {
		this.token = token;
	}

	public double getTHC() {
		return THC;
	}

	public void setTHC(double tHC) {
		THC = tHC;
	}

	public int getTransportTransactionNo() {
		return transportTransactionNo;
	}

	public void setTransportTransactionNo(int transportTransactionNo) {
		this.transportTransactionNo = transportTransactionNo;
	}

	@Override
	public String toString() {
		return "Import [toLocation: " + toLocation + ", fromLocation: " + fromLocation + ", customDuty: " + customDuty
				+ ", deliveryOrder: " + deliveryOrder + ", clearingAgent: " + clearingAgent + ", token: " + token
				+ ", THC: " + THC + ", transportTransactionNo: " + transportTransactionNo + ", inventoryNo: " + "]";
	}
}
