package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Core.Vehicle;

public class VehicleDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM VEHICLE V");
			ResultSet rs = sql.executeQuery();
			ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
			while (rs.next()) {
				String plateNo = rs.getString("PLATE_NO");
				String location = rs.getString("LOCATION");
				String type = rs.getString("TYPE");
				Vehicle vehicle = new Vehicle(plateNo, location, type);
				vehicles.add(vehicle);
			}
			AppLogic.Vehicles.setList(vehicles);
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
					.prepareStatement("CREATE TABLE TEMP (PLATE_NO VARCHAR(7), LOCATION CHAR(3), TYPE VARCHAR(20))");
			sql.executeUpdate();
			ArrayList<Vehicle> vehicles = AppLogic.Vehicles.getList();
			for (int i = 0; i < vehicles.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?)");
				sql.setString(1, vehicles.get(i).getPlateNo());
				sql.setString(2, vehicles.get(i).getLocation());
				sql.setString(3, vehicles.get(i).getType());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE VEHICLE V USING TEMP T ON V.PLATE_NO = T.PLATE_NO "
					+ "WHEN MATCHED THEN " + "UPDATE SET V.LOCATION = T.LOCATION, " + "V.TYPE = T.TYPE "
					+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (PLATE_NO, LOCATION, TYPE) "
					+ "VALUES (T.PLATE_NO, T.LOCATION, T.TYPE) " + "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
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
