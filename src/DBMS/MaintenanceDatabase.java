package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import Core.Maintenance;

public class MaintenanceDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM TRANS T, MAINTENANCE M WHERE T.NUMBER = M.TNUMBER");
			ResultSet rs = sql.executeQuery();
			ArrayList<Maintenance> maintenances = new ArrayList<Maintenance>();
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
				String vehiclePlateNo = rs.getString("VPLATE_NO");
				Maintenance maintenance = new Maintenance(number, date, time, amount, type, description, bankAccountNo,
						chequeNo, vehiclePlateNo);
				maintenances.add(maintenance);
				AppLogic.Transactions.add(maintenance);
			}
			AppLogic.Maintenances.setList(maintenances);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (TNUMBER INT, VPLATE_NO VARCHAR(7))");
			sql.executeUpdate();
			ArrayList<Maintenance> maintenances = AppLogic.Maintenances.getList();
			for (int i = 0; i < maintenances.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
				sql.setInt(1, maintenances.get(i).getNumber());
				sql.setString(2, maintenances.get(i).getVehiclePlateNo());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE MAINTENANCE M USING TEMP T ON M.TNUMBER = T.TNUMBER "
					+ "WHEN MATCHED THEN " + "UPDATE SET M.VPLATE_NO = T.VPLATE_NO "
					+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (TNUMBER, VPLATE_NO) "
					+ "VALUES (T.TNUMBER, T.VPLATE_NO) " + "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
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
