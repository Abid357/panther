package Core;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Transaction {
	private int number;
	private Date date;
	private Time time;
	private double amount;
	private char type;
	private String description;
	private String bankAccountNo;
	private String chequeNo;
	private String entityName;
	private ArrayList<Item> items;

	public Transaction(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items) {
		super();
		this.number = number;
		this.date = date;
		this.time = time;
		this.amount = amount;
		this.type = type;
		this.description = description;
		this.bankAccountNo = bankAccountNo;
		this.chequeNo = chequeNo;
		this.entityName = entityName;
		this.items = items;
	}

	public Transaction(Transaction transaction) {
		super();
		this.number = transaction.number;
		this.date = transaction.date;
		this.time = transaction.time;
		this.amount = transaction.amount;
		this.type = transaction.type;
		this.description = transaction.description;
		this.bankAccountNo = transaction.bankAccountNo;
		this.chequeNo = transaction.chequeNo;
		this.entityName = transaction.entityName;
		if (transaction.items != null) {
			ArrayList<Item> copy = new ArrayList<Item>();
			for (Item i : transaction.items)
				copy.add(new Item(i));
			this.items = copy;
		} else
			this.items = null;
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

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Transaction [number: " + number + ", date: " + date + ", time: " + time + ", amount: " + amount
				+ ", type: " + type + ", description: " + description + ", bankAccountNo: " + bankAccountNo
				+ ", chequeNo: " + chequeNo + ", entityName: " + entityName + ", items: " + items + "]";
	}
}
