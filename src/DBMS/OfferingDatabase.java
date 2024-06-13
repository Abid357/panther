package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Core.Offering;

public class OfferingDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM OFFERING O");
			ResultSet rs = sql.executeQuery();
			ArrayList<Offering> offerings = new ArrayList<Offering>();
			while (rs.next()) {
				String offeringName = rs.getString("ONAME");
				char type = rs.getString("TYPE").charAt(0);
				String supplierName = rs.getString("SNAME");
				Offering offering = new Offering(offeringName, type, supplierName);
				offerings.add(offering);
			}
			AppLogic.Offerings.setList(offerings);
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
					.prepareStatement("CREATE TABLE TEMP (ONAME VARCHAR(40), TYPE CHAR(1), SNAME VARCHAR(40))");
			sql.executeUpdate();
			ArrayList<Offering> offerings = AppLogic.Offerings.getList();
			for (int i = 0; i < offerings.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?)");
				sql.setString(1, offerings.get(i).getOfferingName());
				sql.setString(2, Character.toString(offerings.get(i).getType()));
				sql.setString(3, offerings.get(i).getSupplierName());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection
					.prepareStatement("MERGE OFFERING O USING TEMP T ON O.ONAME = T.ONAME AND O.SNAME = T.SNAME "
							+ "WHEN MATCHED THEN " + "UPDATE SET O.TYPE = T.TYPE " + "WHEN NOT MATCHED BY TARGET THEN "
							+ "INSERT (ONAME, TYPE, SNAME) " + "VALUES (T.ONAME, T.TYPE, T.SNAME) "
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
