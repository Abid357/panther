package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Core.Driver;

public class DriverDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM ENTITY E, DRIVER D WHERE E.NAME = D.ENAME");
			ResultSet rs = sql.executeQuery();
			ArrayList<Driver> drivers = new ArrayList<Driver>();
			while (rs.next()) {
				String name = rs.getString("NAME");
				String contactPerson = rs.getString("CONTACT_PERSON");
				String contactNumber = rs.getString("CONTACT_NUMBER");
				String location = rs.getString("LOCATION");
				String plateNo = rs.getString("DRIVES");
				Driver driver = new Driver(name, contactPerson, contactNumber, location, plateNo);
				drivers.add(driver);
				AppLogic.Entities.add(driver);
			}
			AppLogic.Drivers.setList(drivers);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (ENAME VARCHAR(40), DRIVES VARCHAR(7))");
			sql.executeUpdate();
			ArrayList<Driver> drivers = AppLogic.Drivers.getList();
			for (int i = 0; i < drivers.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
				sql.setString(1, drivers.get(i).getName());
				sql.setString(2, drivers.get(i).getPlateNo());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE DRIVER D USING TEMP T ON D.ENAME = T.ENAME "
					+ "WHEN MATCHED THEN " + "UPDATE SET D.DRIVES = T.DRIVES " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (ENAME, DRIVES) " + "VALUES (T.ENAME, T.DRIVES) " + "WHEN NOT MATCHED BY SOURCE THEN "
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
