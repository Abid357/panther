package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import Core.Purchase;
import Core.Item;

import java.sql.Types;

public class PurchaseDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM TRANS T, PURCHASE P WHERE T.NUMBER = P.TNUMBER");
			ResultSet rs = sql.executeQuery();
			ArrayList<Purchase> purchases = new ArrayList<Purchase>();
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
				int transportTransactionNo = rs.getInt("TTNUMBER");
				if (rs.wasNull())
					transportTransactionNo = -1;
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
				Purchase purchase = new Purchase(number, date, time, amount, type, description, bankAccountNo, chequeNo,
						entityName, items, transportTransactionNo);
				purchases.add(purchase);
				AppLogic.Transactions.add(purchase);
			}
			AppLogic.Purchases.setList(purchases);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (TNUMBER INT, TTNUMBER INT)");
			sql.executeUpdate();
			ArrayList<Purchase> purchases = AppLogic.Purchases.getList();
			for (int i = 0; i < purchases.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
				sql.setInt(1, purchases.get(i).getNumber());
				if (purchases.get(i).getTransportTransactionNo() != -1)
					sql.setInt(2, purchases.get(i).getTransportTransactionNo());
				else
					sql.setNull(2, Types.INTEGER);
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE PURCHASE P USING TEMP T ON P.TNUMBER = T.TNUMBER "
					+ "WHEN MATCHED THEN " + "UPDATE SET P.TTNUMBER = T.TTNUMBER " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (TNUMBER, TTNUMBER) " + "VALUES (T.TNUMBER, T.TTNUMBER) "
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
