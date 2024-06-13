package Core;

import java.sql.Date;

public class Cheque {
	private String number;
	private Date dueDate;
	private String bankAccountNo;

	public Cheque(String number, Date dueDate, String bankAccountNo) {
		super();
		this.number = number;
		this.dueDate = dueDate;
		this.bankAccountNo = bankAccountNo;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	@Override
	public String toString() {
		return "Cheque [number: " + number + ", dueDate: " + dueDate + ", bankAccountNo: " + bankAccountNo + "]";
	}
}
