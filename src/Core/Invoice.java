package Core;

import java.sql.Date;
import java.util.ArrayList;

public class Invoice {
	private int number;
	private Date date;
	private String paymentTerms;
	private String name;
	private int piNo;
	private ArrayList<Item> items;
	private ArrayList<Double> rates;

	public Invoice(int number, Date date, String name, String paymentTerms, int piNo, ArrayList<Item> items,
			ArrayList<Double> rates) {
		super();
		this.number = number;
		this.date = date;
		this.paymentTerms = paymentTerms;
		this.name = name;
		this.piNo = piNo;
		this.items = items;
		this.rates = rates;
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

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPiNo() {
		return piNo;
	}

	public void setPiNo(int piNo) {
		this.piNo = piNo;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public ArrayList<Double> getRates() {
		return rates;
	}

	public void setRates(ArrayList<Double> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "Invoice [number: " + number + ", date: " + date + ", paymentTerms: " + paymentTerms + ", name: " + name
				+ ", piNo: " + piNo + ", items: " + items + ", rates: " + rates + "]";
	}
}