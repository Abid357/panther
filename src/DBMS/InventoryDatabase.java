package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import Core.Inventory;
import Core.Item;

public class InventoryDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM INVENTORY I");
			ResultSet rs = sql.executeQuery();
			ArrayList<Inventory> inventories = new ArrayList<Inventory>();
			while (rs.next()) {
				int number = rs.getInt("NUMBER");
				PreparedStatement sql2 = _DBMSGlobals.connection
						.prepareStatement("SELECT * FROM INV_HAS_PRI WHERE INUMBER = ?");
				sql2.setInt(1, number);
				ResultSet rs2 = sql2.executeQuery();
				ArrayList<Item> items = new ArrayList<Item>();
				while (rs2.next()) {
					int priceTagID = rs2.getInt("PID");
					int stock = rs2.getInt("STOCK");
					if (rs2.wasNull())
						stock = -1;
					Item item = new Item(priceTagID, stock);
					items.add(item);
				}
				Inventory inventory = new Inventory(number, items);
				inventories.add(inventory);
			}
			AppLogic.Inventories.setList(inventories);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (NUMBER INT)");
			sql.executeUpdate();
			ArrayList<Inventory> inventories = AppLogic.Inventories.getList();
			for (int i = 0; i < inventories.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?)");
				sql.setInt(1, inventories.get(i).getNumber());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE INVENTORY I USING TEMP T ON I.NUMBER = T.NUMBER "
					+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (NUMBER) " + "VALUES (T.NUMBER) "
					+ "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("DROP TABLE TEMP");
			sql.execute();

			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (INUMBER INT, PID INT, STOCK INT)");
			sql.executeUpdate();
			for (int i = 0; i < inventories.size(); i++) {
				ArrayList<Item> items = inventories.get(i).getItems();
				if (items != null)
					for (int j = 0; j < items.size(); j++) {
						sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?)");
						sql.setInt(1, inventories.get(i).getNumber());
						sql.setInt(2, items.get(j).getPriceTagID());
						if (items.get(j).getQuantity() != -1)
							sql.setInt(3, items.get(j).getQuantity());
						else
							sql.setNull(3, Types.INTEGER);
						sql.executeUpdate();
					}
			}
			sql = _DBMSGlobals.connection
					.prepareStatement("MERGE INV_HAS_PRI I USING TEMP T ON I.INUMBER = T.INUMBER AND I.PID = T.PID "
							+ "WHEN MATCHED THEN " + "UPDATE SET I.STOCK = T.STOCK "
							+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (INUMBER, PID, STOCK) "
							+ "VALUES (T.INUMBER, T.PID, T.STOCK) " + "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
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
