package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import Core.Rent;

public class RentDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM TRANS T, RENT R WHERE T.NUMBER = R.TNUMBER");
			ResultSet rs = sql.executeQuery();
			ArrayList<Rent> rents = new ArrayList<Rent>();
			while (rs.next()) {
				int number = rs.getInt("TNUMBER");
				Date date = rs.getDate("TRANS_DATE");
				Time time = rs.getTime("TRANS_TIME");
				double amount = rs.getDouble("AMOUNT");
				if (rs.wasNull())
					amount = -1;
				char type = rs.getString("TRANS_TYPE").charAt(0);
				String description = rs.getString("DESCRIPTION");
				String bankAccountNo = rs.getString("BACC_NO");
				String chequeNo = rs.getString("CNUMBER");
				String entityName = rs.getString("ENAME");
				String rentType = rs.getString("RENT_TYPE");
				Rent rent = new Rent(number, date, time, amount, type, description, bankAccountNo,
						chequeNo, entityName, rentType);
				rents.add(rent);
				AppLogic.Transactions.add(rent);
			}
			AppLogic.Rents.setList(rents);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (TNUMBER INT, RENT_TYPE VARCHAR(20))");
			sql.executeUpdate();
			ArrayList<Rent> rents = AppLogic.Rents.getList();
			for (int i = 0; i < rents.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
				sql.setInt(1, rents.get(i).getNumber());
				sql.setString(2, rents.get(i).getRentType());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE RENT R USING TEMP T ON R.TNUMBER = T.TNUMBER "
					+ "WHEN MATCHED THEN " + "UPDATE SET R.RENT_TYPE = T.RENT_TYPE " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (TNUMBER, RENT_TYPE) " + "VALUES (T.TNUMBER, T.RENT_TYPE) "
					+ "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
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
