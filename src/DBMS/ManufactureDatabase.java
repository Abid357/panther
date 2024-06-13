package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;

import Core.Item;
import Core.Manufacture;

public class ManufactureDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM TRANS T, MANUFACTURE M WHERE T.NUMBER = M.TNUMBER");
			ResultSet rs = sql.executeQuery();
			ArrayList<Manufacture> manufactures = new ArrayList<Manufacture>();
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
				int inventoryNo = rs.getInt("INUMBER");
				if (rs.wasNull())
					inventoryNo = -1;
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
				Manufacture manufacture = new Manufacture(number, date, time, amount, type, description, bankAccountNo,
						chequeNo, entityName, items, inventoryNo);
				manufactures.add(manufacture);
				AppLogic.Transactions.add(manufacture);
			}
			AppLogic.Manufactures.setList(manufactures);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (TNUMBER INT, INUMBER INT)");
			sql.executeUpdate();
			ArrayList<Manufacture> manufactures = AppLogic.Manufactures.getList();
			for (int i = 0; i < manufactures.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
				sql.setInt(1, manufactures.get(i).getNumber());
				if (manufactures.get(i).getInventoryNo() != -1)
					sql.setInt(2, manufactures.get(i).getInventoryNo());
				else
					sql.setNull(2, Types.INTEGER);
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE MANUFACTURE M USING TEMP T ON M.TNUMBER = T.TNUMBER "
					+ "WHEN MATCHED THEN " + "UPDATE SET M.INUMBER = T.INUMBER "
					+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (TNUMBER, INUMBER) "
					+ "VALUES (T.TNUMBER, T.INUMBER) " + "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
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
