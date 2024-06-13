package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Core.Entity;

public class EntityDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT E.* FROM ENTITY E "
					+ "LEFT JOIN DRIVER D ON E.NAME = D.ENAME " + "LEFT JOIN EMP_PARTNER EP ON E.NAME = EP.ENAME "
					+ "LEFT JOIN INVESTOR I ON E.NAME = I.ENAME " + "LEFT JOIN LOANER L ON E.NAME = L.ENAME "
					+ "LEFT JOIN SUPPLIER S ON E.NAME = S.ENAME " + "LEFT JOIN CUSTOMER C ON E.NAME = C.ENAME "
					+ "WHERE D.ENAME IS NULL " + "AND EP.ENAME IS NULL " + "AND I.ENAME IS NULL "
					+ "AND L.ENAME IS NULL " + "AND S.ENAME IS NULL " + "AND C.ENAME IS NULL");
			ResultSet rs = sql.executeQuery();
			ArrayList<Entity> entities = new ArrayList<Entity>();
			while (rs.next()) {
				String name = rs.getString("NAME");
				String contactPerson = rs.getString("CONTACT_PERSON");
				String contactNumber = rs.getString("CONTACT_NUMBER");
				String location = rs.getString("LOCATION");
				Entity entity = new Entity(name, contactPerson, contactNumber, location);
				entities.add(entity);
			}
			AppLogic.Entities.setList(entities);
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
					"CREATE TABLE TEMP (NAME VARCHAR(40), CONTACT_PERSON VARCHAR(40), CONTACT_NUMBER VARCHAR(15), LOCATION VARCHAR(40))");
			sql.executeUpdate();
			ArrayList<Entity> entities = AppLogic.Entities.getList();
			for (int i = 0; i < entities.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?)");
				sql.setString(1, entities.get(i).getName());
				sql.setString(2, entities.get(i).getContactPerson());
				sql.setString(3, entities.get(i).getContactNumber());
				sql.setString(4, entities.get(i).getLocation());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE ENTITY E USING TEMP T ON E.NAME = T.NAME "
					+ "WHEN MATCHED THEN " + "UPDATE SET E.CONTACT_PERSON = T.CONTACT_PERSON, "
					+ "E.CONTACT_NUMBER = T.CONTACT_NUMBER, " + "E.LOCATION = T.LOCATION "
					+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (NAME, CONTACT_PERSON, CONTACT_NUMBER, LOCATION) "
					+ "VALUES (T.NAME, T.CONTACT_PERSON, T.CONTACT_NUMBER, T.LOCATION) "
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
