package Core;

import java.sql.Date;
import java.sql.Time;

public class Domestic extends Transaction {

	public Domestic(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName) {
		super(number, date, time, amount, type, description, bankAccountNo, chequeNo, entityName, null);
		// TODO Auto-generated constructor stub
	}
}
