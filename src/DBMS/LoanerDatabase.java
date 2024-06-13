package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import Core.Loaner;

public class LoanerDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM ENTITY E, LOANER L WHERE E.NAME = L.ENAME");
			ResultSet rs = sql.executeQuery();
			ArrayList<Loaner> loaners = new ArrayList<Loaner>();
			while (rs.next()) {
				String name = rs.getString("NAME");
				String contactPerson = rs.getString("CONTACT_PERSON");
				String contactNumber = rs.getString("CONTACT_NUMBER");
				String location = rs.getString("LOCATION");
				double balance = rs.getDouble("BALANCE");
				Loaner loaner = new Loaner(name, contactPerson, contactNumber, location, balance);
				loaners.add(loaner);
				AppLogic.Entities.add(loaner);
			}
			AppLogic.Loaners.setList(loaners);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean save() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("IF OBJECT_ID('dbo.TEMP', 'U') IS NOT NULL " + "DROP TABLE dbo.TEMP");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (ENAME VARCHAR(40), BALANCE MONEY)");
			sql.executeUpdate();
			ArrayList<Loaner> loaners = AppLogic.Loaners.getList();
			for (int i = 0; i < loaners.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
				sql.setString(1, loaners.get(i).getName());
				if (loaners.get(i).getBalance() != -1)
					sql.setDouble(2, loaners.get(i).getBalance());
				else
					sql.setNull(2, Types.DOUBLE);
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE LOANER L USING TEMP T ON L.ENAME = T.ENAME "
					+ "WHEN MATCHED THEN " + "UPDATE SET L.BALANCE = T.BALANCE " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (ENAME, BALANCE) " + "VALUES (T.ENAME, T.BALANCE) " + "WHEN NOT MATCHED BY SOURCE THEN "
					+ "DELETE;");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("DROP TABLE TEMP");
			sql.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
