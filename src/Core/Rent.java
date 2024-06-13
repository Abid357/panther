package Core;

import java.sql.Date;
import java.sql.Time;

public class Rent extends Transaction {
	private String rentType;

	public Rent(int number, Date date, Time time, double amount, char type, String description, String bankAccountNo,
			String chequeNo, String entityName, String rentType) {
		super(number, date, time, amount, type, description, bankAccountNo, chequeNo, entityName, null);
		this.rentType = rentType;
	}

	public String getRentType() {
		return rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	@Override
	public String toString() {
		return "Rent [rentType: " + rentType + "]";
	}
}
