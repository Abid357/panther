package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Core.Offering;
import Core.Supplier;

public class SupplierDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM ENTITY E, SUPPLIER S WHERE E.NAME = S.ENAME");
			ResultSet rs = sql.executeQuery();
			ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
			while (rs.next()) {
				String name = rs.getString("NAME");
				String contactPerson = rs.getString("CONTACT_PERSON");
				String contactNumber = rs.getString("CONTACT_NUMBER");
				String location = rs.getString("LOCATION");
				int inventoryNo = rs.getInt("INUMBER");
				PreparedStatement sql2 = _DBMSGlobals.connection
						.prepareStatement("SELECT * FROM OFFERING WHERE SNAME = ?");
				sql2.setString(1, name);
				ResultSet rs2 = sql2.executeQuery();
				ArrayList<Offering> offerings = new ArrayList<Offering>();
				while (rs2.next()) {
					String offeringName = rs2.getString("ONAME");
					String type = rs2.getString("TYPE");
					String supplierName = rs2.getString("SNAME");
					Offering offering = new Offering(offeringName, type.charAt(0), supplierName);
					offerings.add(offering);
				}
				Supplier supplier = new Supplier(name, contactPerson, contactNumber, location, inventoryNo, offerings);
				suppliers.add(supplier);
				AppLogic.Entities.add(supplier);
			}
			AppLogic.Suppliers.setList(suppliers);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (ENAME VARCHAR(40), INUMBER INT)");
			sql.executeUpdate();
			ArrayList<Supplier> suppliers = AppLogic.Suppliers.getList();
			for (int i = 0; i < suppliers.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
				sql.setString(1, suppliers.get(i).getName());
				sql.setInt(2, suppliers.get(i).getInventoryNo());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE SUPPLIER S USING TEMP T ON S.ENAME = T.ENAME "
					+ "WHEN MATCHED THEN " + "UPDATE SET S.INUMBER = T.INUMBER " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (ENAME, INUMBER) " + "VALUES (T.ENAME, T.INUMBER) " + "WHEN NOT MATCHED BY SOURCE THEN "
					+ "DELETE;");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("DROP TABLE TEMP");
			sql.execute();

			sql = _DBMSGlobals.connection
					.prepareStatement("CREATE TABLE TEMP (ONAME VARCHAR(40), TYPE CHAR(1), SNAME VARCHAR(40))");
			sql.executeUpdate();
			for (int k = 0; k < suppliers.size(); k++) {
				ArrayList<Offering> offerings = suppliers.get(k).getOfferings();
				if (offerings != null)
					for (int l = 0; l < offerings.size(); l++) {
						sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?)");
						sql.setString(1, offerings.get(l).getOfferingName());
						sql.setString(2, Character.toString(offerings.get(l).getType()));
						sql.setString(3, offerings.get(l).getSupplierName());
						sql.executeUpdate();
					}
			}
			sql = _DBMSGlobals.connection
					.prepareStatement("MERGE OFFERING O USING TEMP T ON O.SNAME = T.SNAME AND O.ONAME = T.ONAME "
							+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (ONAME, TYPE, SNAME) "
							+ "VALUES (T.ONAME, T.TYPE, T.SNAME) " + "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
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
