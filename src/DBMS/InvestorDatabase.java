package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import Core.Investor;

public class InvestorDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM ENTITY E, INVESTOR I WHERE E.NAME = I.ENAME");
			ResultSet rs = sql.executeQuery();
			ArrayList<Investor> investors = new ArrayList<Investor>();
			while (rs.next()) {
				String name = rs.getString("NAME");
				String contactPerson = rs.getString("CONTACT_PERSON");
				String contactNumber = rs.getString("CONTACT_NUMBER");
				String location = rs.getString("LOCATION");
				double invested = rs.getDouble("INVESTED");
				double profited = rs.getDouble("PROFITED");
				float profitRate = rs.getFloat("PROFIT_RATE");
				Investor investor = new Investor(name, contactPerson, contactNumber, location, invested, profited,
						profitRate);
				investors.add(investor);
				AppLogic.Entities.add(investor);
			}
			AppLogic.Investors.setList(investors);
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
					"CREATE TABLE TEMP (ENAME VARCHAR(40), INVESTED MONEY, PROFITED MONEY, PROFIT_RATE FLOAT)");
			sql.executeUpdate();
			ArrayList<Investor> investors = AppLogic.Investors.getList();
			for (int i = 0; i < investors.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?)");
				sql.setString(1, investors.get(i).getName());
				if (investors.get(i).getInvested() != -1)
					sql.setDouble(2, investors.get(i).getInvested());
				else
					sql.setNull(2, Types.DOUBLE);
				if (investors.get(i).getProfited() != -1)
					sql.setDouble(3, investors.get(i).getProfited());
				else
					sql.setNull(3, Types.DOUBLE);
				if (investors.get(i).getProfitRate() != -1)
					sql.setFloat(4, investors.get(i).getProfitRate());
				else
					sql.setNull(4, Types.FLOAT);
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE INVESTOR I USING TEMP T ON I.ENAME = T.ENAME "
					+ "WHEN MATCHED THEN " + "UPDATE SET I.INVESTED = T.INVESTED, " + "I.PROFITED = T.PROFITED, "
					+ "I.PROFIT_RATE = T.PROFIT_RATE " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (ENAME, INVESTED, PROFITED, PROFIT_RATE) "
					+ "VALUES (T.ENAME, T.INVESTED, T.PROFITED, T.PROFIT_RATE) " + "WHEN NOT MATCHED BY SOURCE THEN "
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
