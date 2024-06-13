package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Core.Customer;

public class CustomerDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM ENTITY E, CUSTOMER C WHERE E.NAME = C.ENAME");
			ResultSet rs = sql.executeQuery();
			ArrayList<Customer> customers = new ArrayList<Customer>();
			while (rs.next()) {
				String name = rs.getString("NAME");
				String contactPerson = rs.getString("CONTACT_PERSON");
				String contactNumber = rs.getString("CONTACT_NUMBER");
				String location = rs.getString("LOCATION");
				Customer customer = new Customer(name, contactPerson, contactNumber, location);
				customers.add(customer);
				AppLogic.Entities.add(customer);
			}
			AppLogic.Customers.setList(customers);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (ENAME VARCHAR(40))");
			sql.executeUpdate();
			ArrayList<Customer> customers = AppLogic.Customers.getList();
			for (int i = 0; i < customers.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?)");
				sql.setString(1, customers.get(i).getName());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement(
					"MERGE CUSTOMER C USING TEMP T ON C.ENAME = T.ENAME " + "WHEN NOT MATCHED BY TARGET THEN "
							+ "INSERT (ENAME) " + "VALUES (T.ENAME) " + "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
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
