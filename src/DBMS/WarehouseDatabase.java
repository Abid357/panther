package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import Core.Warehouse;

public class WarehouseDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM WAREHOUSE W");
			ResultSet rs = sql.executeQuery();
			ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
			while (rs.next()) {
				int number = rs.getInt("NUMBER");
				String location = rs.getString("LOCATION");
				int inventoryNo = rs.getInt("INUMBER");
				if (rs.wasNull())
					inventoryNo = -1;
				Warehouse warehouse = new Warehouse(number, location, inventoryNo);
				warehouses.add(warehouse);
			}
			AppLogic.Warehouses.setList(warehouses);
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
					.prepareStatement("CREATE TABLE TEMP (NUMBER SMALLINT, LOCATION VARCHAR(20), INUMBER INT)");
			sql.executeUpdate();
			ArrayList<Warehouse> warehouses = AppLogic.Warehouses.getList();
			for (int i = 0; i < warehouses.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?)");
				sql.setInt(1, warehouses.get(i).getNumber());
				sql.setString(2, warehouses.get(i).getLocation());
				if (warehouses.get(i).getInventoryNo() != -1)
					sql.setInt(3, warehouses.get(i).getInventoryNo());
				else
					sql.setNull(3, Types.INTEGER);
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement(
					"MERGE WAREHOUSE W USING TEMP T ON W.NUMBER = T.NUMBER AND W.LOCATION = T.LOCATION "
							+ "WHEN MATCHED THEN " + "UPDATE SET W.INUMBER = T.INUMBER "
							+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (NUMBER, LOCATION, INUMBER) "
							+ "VALUES (T.NUMBER, T.LOCATION, T.INUMBER) " + "WHEN NOT MATCHED BY SOURCE THEN "
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
