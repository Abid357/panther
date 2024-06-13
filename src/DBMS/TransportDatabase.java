package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import Core.Item;
import Core.Transport;

public class TransportDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM TRANS T, TRANSPORT R WHERE T.NUMBER = R.TNUMBER");
			ResultSet rs = sql.executeQuery();
			ArrayList<Transport> transports = new ArrayList<Transport>();
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
				String toLocation = rs.getString("TO_LOC");
				String fromLocation = rs.getString("FROM_LOC");
				int inventoryNo = rs.getInt("INUMBER");
				PreparedStatement sql2 = _DBMSGlobals.connection
						.prepareStatement("SELECT * FROM TRANS_HAS_PRI WHERE TNUMBER = ?");
				sql2.setInt(1, number);
				ResultSet rs2 = sql2.executeQuery();
				ArrayList<Item> items = new ArrayList<Item>();
				while (rs2.next()) {
					int priceTagID = rs2.getInt("PID");
					int quantity = rs2.getInt("STOCK");
					Item item = new Item(priceTagID, quantity);
					items.add(item);
				}
				Transport transport = new Transport(number, date, time, amount, type, description, bankAccountNo,
						chequeNo, entityName, items, toLocation, fromLocation, inventoryNo);
				transports.add(transport);
				AppLogic.Transactions.add(transport);
			}
			AppLogic.Transports.setList(transports);
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
			sql = _DBMSGlobals.connection.prepareStatement(
					"CREATE TABLE TEMP (TNUMBER INT, TO_LOC VARCHAR(40), FROM_LOC VARCHAR(40), INUMBER INT)");
			sql.executeUpdate();
			ArrayList<Transport> transports = AppLogic.Transports.getList();
			for (int i = 0; i < transports.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?)");
				sql.setInt(1, transports.get(i).getNumber());
				sql.setString(2, transports.get(i).getToLocation());
				sql.setString(3, transports.get(i).getFromLocation());
				sql.setInt(4, transports.get(i).getInventoryNo());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE TRANSPORT R USING TEMP T ON R.TNUMBER = T.TNUMBER "
					+ "WHEN MATCHED THEN " + "UPDATE SET R.TO_LOC = T.TO_LOC, " + "R.FROM_LOC = T.FROM_LOC, "
					+ "R.INUMBER = T.INUMBER " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (TNUMBER, TO_LOC, FROM_LOC, INUMBER) "
					+ "VALUES (T.TNUMBER, T.TO_LOC, T.FROM_LOC, T.INUMBER) " + "WHEN NOT MATCHED BY SOURCE THEN "
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
