package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;

import Core.Damage;
import Core.Item;

public class DamageDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM TRANS T, DAMAGE D WHERE T.NUMBER = D.TNUMBER");
			ResultSet rs = sql.executeQuery();
			ArrayList<Damage> damages = new ArrayList<Damage>();
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
				int inventoryNo = rs.getInt("INUMBER");
				if (rs.wasNull())
					inventoryNo = -1;
				PreparedStatement sql2 = _DBMSGlobals.connection
						.prepareStatement("SELECT * FROM TRANS_HAS_PRI WHERE TNUMBER = ?");
				sql2.setInt(1, number);
				ResultSet rs2 = sql2.executeQuery();
				ArrayList<Item> items = new ArrayList<Item>();
				while (rs2.next()) {
					int priceTagID = rs2.getInt("PID");
					int quantity = rs2.getInt("STOCK");
					Item item = new Item(priceTagID, quantity);
					items.add(item);
				}
				Damage damage = new Damage(number, date, time, amount, type, description, bankAccountNo, chequeNo,
						entityName, items, inventoryNo);
				damages.add(damage);
				AppLogic.Transactions.add(damage);
			}
			AppLogic.Damages.setList(damages);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (TNUMBER INT, INUMBER INT)");
			sql.executeUpdate();
			ArrayList<Damage> damages = AppLogic.Damages.getList();
			for (int i = 0; i < damages.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
				sql.setInt(1, damages.get(i).getNumber());
				if (damages.get(i).getInventoryNo() != -1)
					sql.setInt(2, damages.get(i).getInventoryNo());
				else
					sql.setNull(2, Types.INTEGER);
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE DAMAGE D USING TEMP T ON D.TNUMBER = T.TNUMBER "
					+ "WHEN MATCHED THEN " + "UPDATE SET D.INUMBER = T.INUMBER " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (TNUMBER, INUMBER) " + "VALUES (T.TNUMBER, T.INUMBER) "
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
