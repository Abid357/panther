package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Core.Pocket;

public class PocketDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM POCKET P");
			ResultSet rs = sql.executeQuery();
			ArrayList<Pocket> pockets = new ArrayList<Pocket>();
			while (rs.next()) {
				String name = rs.getString("NAME");
				double balance = rs.getDouble("BALANCE");
				Pocket pocket = new Pocket(name, balance);
				pockets.add(pocket);
			}
			AppLogic.Pockets.setList(pockets);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (NAME VARCHAR(15), BALANCE MONEY)");
			sql.executeUpdate();
			ArrayList<Pocket> pockets = AppLogic.Pockets.getList();
			for (int i = 0; i < pockets.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
				sql.setString(1, pockets.get(i).getName());
				sql.setDouble(2, pockets.get(i).getBalance());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE POCKET P USING TEMP T ON P.NAME = T.NAME "
					+ "WHEN MATCHED THEN " + "UPDATE SET P.BALANCE = T.BALANCE " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (NAME, BALANCE) " + "VALUES (T.NAME, T.BALANCE) " + "WHEN NOT MATCHED BY SOURCE THEN "
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
