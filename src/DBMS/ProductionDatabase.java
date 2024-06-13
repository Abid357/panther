package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import Core.Item;
import Core.Production;

public class ProductionDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM PRODUCTION P ");
			ResultSet rs = sql.executeQuery();
			ArrayList<Production> productions = new ArrayList<Production>();
			while (rs.next()) {
				int ID = rs.getInt("ID");
				Date startDate = rs.getDate("START_DATE");
				Date endDate = rs.getDate("END_DATE");
				int cartons = rs.getInt("CARTONS");
				if (rs.wasNull())
					cartons = -1;
				String notes = rs.getString("NOTES");
				int productTag = rs.getInt("PID");
				if (rs.wasNull())
					productTag = -1;
				int transportTransactionNo = rs.getInt("TNUMBER");
				if (rs.wasNull())
					transportTransactionNo = -1;
				PreparedStatement sql2 = _DBMSGlobals.connection
						.prepareStatement("SELECT DISTINCT INUMBER FROM PRO_USES_INV WHERE PRODID = ?");
				sql2.setInt(1, ID);
				ResultSet rs2 = sql2.executeQuery();
				HashMap<Integer, ArrayList<Item>> items = new HashMap<Integer, ArrayList<Item>>();
				while (rs2.next()) {
					int inventoryNo = rs2.getInt("INUMBER");
					PreparedStatement sql3 = _DBMSGlobals.connection
							.prepareStatement("SELECT * FROM PRO_USES_INV WHERE PRODID = ? AND INUMBER = ?");
					sql3.setInt(1, ID);
					sql3.setInt(2, inventoryNo);
					ResultSet rs3 = sql3.executeQuery();
					ArrayList<Item> itemsPerInventory = new ArrayList<Item>();
					while (rs3.next()) {
						int priceTagID = rs3.getInt("PID");
						int quantity = rs3.getInt("QUANTITY");
						Item item = new Item(priceTagID, quantity);
						itemsPerInventory.add(item);
					}
					items.put(inventoryNo, itemsPerInventory);
				}
				Production production = new Production(ID, startDate, endDate, cartons, notes, productTag,
						transportTransactionNo, items);
				productions.add(production);
			}
			AppLogic.Productions.setList(productions);
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
					"CREATE TABLE TEMP (ID SMALLINT, START_DATE DATE, END_DATE DATE, CARTONS INT, NOTES VARCHAR(MAX), PID INT, TNUMBER INT)");
			sql.executeUpdate();
			ArrayList<Production> productions = AppLogic.Productions.getList();
			for (int i = 0; i < productions.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?, ?, ?, ?)");
				sql.setInt(1, productions.get(i).getID());
				sql.setDate(2, productions.get(i).getStartDate());
				sql.setDate(3, productions.get(i).getEndDate());
				if (productions.get(i).getCartons() != -1)
					sql.setInt(4, productions.get(i).getCartons());
				else
					sql.setNull(4, Types.INTEGER);
				sql.setString(5, productions.get(i).getNotes());
				if (productions.get(i).getProductTag() != -1)
					sql.setInt(6, productions.get(i).getProductTag());
				else
					sql.setNull(6, Types.SMALLINT);
				if (productions.get(i).getTransportTransactionNo() != -1)
					sql.setInt(7, productions.get(i).getTransportTransactionNo());
				else
					sql.setNull(7, Types.INTEGER);
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE PRODUCTION P USING TEMP T ON P.ID = T.ID "
					+ "WHEN MATCHED THEN " + "UPDATE SET P.START_DATE = T.START_DATE, " + "P.END_DATE = T.END_DATE, "
					+ "P.CARTONS = T.CARTONS, " + "P.NOTES = T.NOTES, " + "P.PID = T.PID, "
					+ "P.TNUMBER = T.TNUMBER " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (ID, START_DATE, END_DATE, CARTONS, NOTES, PID, TNUMBER) "
					+ "VALUES (T.ID, T.START_DATE, T.END_DATE, T.CARTONS, T.NOTES, T.PID, T.TNUMBER) "
					+ "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("DROP TABLE TEMP");
			sql.execute();

			sql = _DBMSGlobals.connection
					.prepareStatement("IF OBJECT_ID('dbo.TEMP', 'U') IS NOT NULL " + "DROP TABLE dbo.TEMP");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection
					.prepareStatement("CREATE TABLE TEMP (PRODID SMALLINT, INUMBER INT, PID INT, QUANTITY INT)");
			sql.executeUpdate();
			for (int i = 0; i < productions.size(); i++) {
				HashMap<Integer, ArrayList<Item>> items = productions.get(i).getItems();
				if (items != null) {
					Iterator<Entry<Integer, ArrayList<Item>>> iterator = items.entrySet().iterator();
					while (iterator.hasNext()) {
						int inventoryNo = iterator.next().getKey();
						ArrayList<Item> itemsPerInventory = items.get(inventoryNo);
						if (itemsPerInventory != null) {
							for (int j = 0; j < itemsPerInventory.size(); j++) {
								sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?)");
								sql.setInt(1, productions.get(i).getID());
								sql.setInt(2, inventoryNo);
								sql.setInt(3, itemsPerInventory.get(j).getPriceTagID());
								sql.setInt(4, itemsPerInventory.get(j).getQuantity());
								sql.executeUpdate();
							}
						}
					}
				}
			}
			sql = _DBMSGlobals.connection.prepareStatement(
					"MERGE PRO_USES_INV P USING TEMP T ON P.PRODID = T.PRODID AND P.INUMBER = T.INUMBER AND P.PID = T.PID "
							+ "WHEN MATCHED THEN " + "UPDATE SET P.QUANTITY = T.QUANTITY " + "WHEN NOT MATCHED BY TARGET THEN "
							+ "INSERT (PRODID, INUMBER, PID, QUANTITY) "
							+ "VALUES (T.PRODID, T.INUMBER, T.PID, T.QUANTITY) " + "WHEN NOT MATCHED BY SOURCE THEN "
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
