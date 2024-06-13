package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import Core.Profit;

public class ProfitDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM TRANS T, PROFIT P WHERE T.NUMBER = P.TNUMBER");
			ResultSet rs = sql.executeQuery();
			ArrayList<Profit> profits = new ArrayList<Profit>();
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
				String entityName = rs.getString("ENAME");
				Profit profit = new Profit(number, date, time, amount, type, description, bankAccountNo, chequeNo,
						entityName);
				profits.add(profit);
				AppLogic.Transactions.add(profit);
			}
			AppLogic.Profits.setList(profits);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (TNUMBER INT)");
			sql.executeUpdate();
			ArrayList<Profit> profits = AppLogic.Profits.getList();
			for (int i = 0; i < profits.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?)");
				sql.setInt(1, profits.get(i).getNumber());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE PROFIT P USING TEMP T ON P.TNUMBER = T.TNUMBER "
					+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (TNUMBER) " + "VALUES (T.TNUMBER) "
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
