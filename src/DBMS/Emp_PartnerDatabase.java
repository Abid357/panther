package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Core.Emp_Partner;

public class Emp_PartnerDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM ENTITY E, EMP_PARTNER EP WHERE E.NAME = EP.ENAME");
			ResultSet rs = sql.executeQuery();
			ArrayList<Emp_Partner> emp_partners = new ArrayList<Emp_Partner>();
			while (rs.next()) {
				String name = rs.getString("NAME");
				String contactPerson = rs.getString("CONTACT_PERSON");
				String contactNumber = rs.getString("CONTACT_NUMBER");
				String location = rs.getString("LOCATION");
				Date visaExpiry = rs.getDate("VISA_EXPIRY");
				Date cardExpiry = rs.getDate("CARD_EXPIRY");
				String licenseNumber = rs.getString("LNUMBER");
				Emp_Partner emp_partner = new Emp_Partner(name, contactPerson, contactNumber, location, visaExpiry,
						cardExpiry, licenseNumber);
				emp_partners.add(emp_partner);
				AppLogic.Entities.add(emp_partner);
			}
			AppLogic.Emp_Partners.setList(emp_partners);
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
					"CREATE TABLE TEMP (ENAME VARCHAR(40), VISA_EXPIRY DATE, CARD_EXPIRY DATE, LNUMBER VARCHAR(15))");
			sql.executeUpdate();
			ArrayList<Emp_Partner> emp_partners = AppLogic.Emp_Partners.getList();
			for (int i = 0; i < emp_partners.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?)");
				sql.setString(1, emp_partners.get(i).getName());
				sql.setDate(2, emp_partners.get(i).getVisaExpiry());
				sql.setDate(3, emp_partners.get(i).getCardExpiry());
				sql.setString(4, emp_partners.get(i).getLicenseNumber());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE EMP_PARTNER EP USING TEMP T ON EP.ENAME = T.ENAME "
					+ "WHEN MATCHED THEN " + "UPDATE SET EP.VISA_EXPIRY = T.VISA_EXPIRY, "
					+ "EP.CARD_EXPIRY = T.CARD_EXPIRY, " + "EP.LNUMBER = T.LNUMBER "
					+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (ENAME, VISA_EXPIRY, CARD_EXPIRY, LNUMBER) "
					+ "VALUES (T.ENAME, T.VISA_EXPIRY, T.CARD_EXPIRY, T.LNUMBER) "
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
