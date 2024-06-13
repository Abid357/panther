package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Core.License;

public class LicenseDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM LICENSE L");
			ResultSet rs = sql.executeQuery();
			ArrayList<License> licenses = new ArrayList<License>();
			while (rs.next()) {
				String number = rs.getString("NUMBER");
				String name = rs.getString("NAME");
				Date immCardRenewal = rs.getDate("IMMCARD_RDATE");
				Date poboxRenewal = rs.getDate("POBOX_RDATE");
				Date licenseRenewal = rs.getDate("LICENSE_RDATE");
				PreparedStatement sql2 = _DBMSGlobals.connection
						.prepareStatement("SELECT * FROM LIC_PAYS_RENT WHERE LNUMBER = ?");
				sql2.setString(1, number);
				ResultSet rs2 = sql2.executeQuery();
				ArrayList<Integer> rentTransactionNos = new ArrayList<Integer>();
				while (rs2.next()) {
					int rentTransactionNo = rs2.getInt("RNUMBER");
					rentTransactionNos.add(rentTransactionNo);
				}
				sql2 = _DBMSGlobals.connection.prepareStatement("SELECT * FROM LIC_PAYS_RFEE WHERE LNUMBER = ?");
				sql2.setString(1, number);
				rs2 = sql2.executeQuery();
				ArrayList<Integer> renewalFeeTransactionNos = new ArrayList<Integer>();
				while (rs2.next()) {
					int renewalFeeTransactionNo = rs2.getInt("RNUMBER");
					renewalFeeTransactionNos.add(renewalFeeTransactionNo);
				}
				License license = new License(number, name, immCardRenewal, poboxRenewal, licenseRenewal,
						rentTransactionNos, renewalFeeTransactionNos);
				licenses.add(license);
			}
			AppLogic.Licenses.setList(licenses);
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
					"CREATE TABLE TEMP (NUMBER VARCHAR(15), NAME VARCHAR(40), IMMCARD_RDATE DATE, POBOX_RDATE DATE, LICENSE_RDATE DATE)");
			sql.executeUpdate();
			ArrayList<License> licenses = AppLogic.Licenses.getList();
			for (int i = 0; i < licenses.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?, ?)");
				sql.setString(1, licenses.get(i).getNumber());
				sql.setString(2, licenses.get(i).getName());
				sql.setDate(3, licenses.get(i).getImmCardRenewal());
				sql.setDate(4, licenses.get(i).getPoboxRenewal());
				sql.setDate(5, licenses.get(i).getLicenseRenewal());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE LICENSE L USING TEMP T ON L.NUMBER = T.NUMBER "
					+ "WHEN MATCHED THEN " + "UPDATE SET L.NAME = T.NAME, " + "L.IMMCARD_RDATE = T.IMMCARD_RDATE, "
					+ "L.POBOX_RDATE = T.POBOX_RDATE, " + "L.LICENSE_RDATE = T.LICENSE_RDATE "
					+ "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (NUMBER, NAME, IMMCARD_RDATE, POBOX_RDATE, LICENSE_RDATE) "
					+ "VALUES (T.NUMBER, T.NAME, T.IMMCARD_RDATE, T.POBOX_RDATE, T.LICENSE_RDATE) "
					+ "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("DROP TABLE TEMP");
			sql.execute();

			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (LNUMBER VARCHAR(15), RNUMBER INT)");
			sql.executeUpdate();
			for (int i = 0; i < licenses.size(); i++) {
				ArrayList<Integer> rentTransactionNos = licenses.get(i).getRentTransactionNos();
				if (rentTransactionNos != null)
					for (int j = 0; j < rentTransactionNos.size(); j++) {
						sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
						sql.setString(1, licenses.get(i).getNumber());
						sql.setInt(2, rentTransactionNos.get(j));
						sql.executeUpdate();
					}
			}
			sql = _DBMSGlobals.connection.prepareStatement(
					"MERGE LIC_PAYS_RENT L USING TEMP T ON L.LNUMBER = T.LNUMBER AND L.RNUMBER = T.RNUMBER "
							+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (LNUMBER, RNUMBER) "
							+ "VALUES (T.LNUMBER, T.RNUMBER) " + "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("DROP TABLE TEMP");
			sql.execute();
			
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (LNUMBER VARCHAR(15), RNUMBER INT)");
			sql.executeUpdate();
			for (int i = 0; i < licenses.size(); i++) {
				ArrayList<Integer> renewalFeeTransactionNos = licenses.get(i).getRenewalFeeTransactionNos();
				if (renewalFeeTransactionNos != null)
					for (int j = 0; j < renewalFeeTransactionNos.size(); j++) {
						sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
						sql.setString(1, licenses.get(i).getNumber());
						sql.setInt(2, renewalFeeTransactionNos.get(j));
						sql.executeUpdate();
					}
			}
			sql = _DBMSGlobals.connection.prepareStatement(
					"MERGE LIC_PAYS_RFEE L USING TEMP T ON L.LNUMBER = T.LNUMBER AND L.RNUMBER = T.RNUMBER "
							+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (LNUMBER, RNUMBER) "
							+ "VALUES (T.LNUMBER, T.RNUMBER) " + "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
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
