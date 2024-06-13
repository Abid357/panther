package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;

import Core.Item;
import Core.Promotion;

public class PromotionDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM TRANS T, PROMOTION P WHERE T.NUMBER = P.TNUMBER");
			ResultSet rs = sql.executeQuery();
			ArrayList<Promotion> promotions = new ArrayList<Promotion>();
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
				boolean isSample = rs.getBoolean("IS_SAMPLE");
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
				Promotion promotion = new Promotion(number, date, time, amount, type, description, bankAccountNo,
						chequeNo, entityName, items, inventoryNo, isSample);
				promotions.add(promotion);
				AppLogic.Transactions.add(promotion);
			}
			AppLogic.Promotions.setList(promotions);
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
			sql = _DBMSGlobals.connection
					.prepareStatement("CREATE TABLE TEMP (TNUMBER INT, INUMBER INT, IS_SAMPLE BIT)");
			sql.executeUpdate();
			ArrayList<Promotion> promotions = AppLogic.Promotions.getList();
			for (int i = 0; i < promotions.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?)");
				sql.setInt(1, promotions.get(i).getNumber());
				if (promotions.get(i).getInventoryNo() != -1)
					sql.setInt(2, promotions.get(i).getInventoryNo());
				else
					sql.setNull(2, Types.INTEGER);
				sql.setBoolean(3, promotions.get(i).isSample());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE PROMOTION P USING TEMP T ON P.TNUMBER = T.TNUMBER "
					+ "WHEN MATCHED THEN " + "UPDATE SET P.INUMBER = T.INUMBER, " + "P.IS_SAMPLE = T.IS_SAMPLE "
					+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (TNUMBER, INUMBER, IS_SAMPLE) "
					+ "VALUES (T.TNUMBER, T.INUMBER, T.IS_SAMPLE) " + "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
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
