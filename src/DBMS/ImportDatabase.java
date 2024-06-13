package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import Core.Import;
import Core.Item;

import java.sql.Types;

public class ImportDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM TRANS T, IMPORT I WHERE T.NUMBER = I.TNUMBER");
			ResultSet rs = sql.executeQuery();
			ArrayList<Import> imports = new ArrayList<Import>();
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
				String toLocation = rs.getString("TO_LOC");
				String fromLocation = rs.getString("FROM_LOC");
				double customDuty = rs.getDouble("CUSTOM_DUTY");
				if (rs.wasNull())
					customDuty = -1;
				double deliveryOrder = rs.getDouble("DELIVERY_ORDER");
				if (rs.wasNull())
					deliveryOrder = -1;
				double clearingAgent = rs.getDouble("CLEARING_AGENT");
				if (rs.wasNull())
					clearingAgent = -1;
				double token = rs.getDouble("TOKEN");
				if (rs.wasNull())
					token = -1;
				double THC = rs.getDouble("THC");
				if (rs.wasNull())
					THC = -1;
				int transportTransactionNo = rs.getInt("TTNUMBER");
				if (rs.wasNull())
					transportTransactionNo = -1;
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
				Import imp = new Import(number, date, time, amount, type, description, bankAccountNo, chequeNo,
						entityName, items, toLocation, fromLocation, customDuty, deliveryOrder, clearingAgent, token,
						THC, transportTransactionNo);
				imports.add(imp);
				AppLogic.Transactions.add(imp);
			}
			AppLogic.Imports.setList(imports);
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
					"CREATE TABLE TEMP (TNUMBER INT, TO_LOC VARCHAR(40), FROM_LOC VARCHAR(40), CUSTOM_DUTY MONEY, DELIVERY_ORDER MONEY, CLEARING_AGENT MONEY, TOKEN MONEY, THC MONEY, TTNUMBER INT)");
			sql.executeUpdate();
			ArrayList<Import> imports = AppLogic.Imports.getList();
			for (int i = 0; i < imports.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				sql.setInt(1, imports.get(i).getNumber());
				sql.setString(2, imports.get(i).getToLocation());
				sql.setString(3, imports.get(i).getFromLocation());
				if (imports.get(i).getCustomDuty() != -1)
					sql.setDouble(4, imports.get(i).getCustomDuty());
				else
					sql.setNull(4, Types.DOUBLE);
				if (imports.get(i).getDeliveryOrder() != -1)
					sql.setDouble(5, imports.get(i).getDeliveryOrder());
				else
					sql.setNull(5, Types.DOUBLE);
				if (imports.get(i).getClearingAgent() != -1)
					sql.setDouble(6, imports.get(i).getClearingAgent());
				else
					sql.setNull(6, Types.DOUBLE);
				if (imports.get(i).getToken() != -1)
					sql.setDouble(7, imports.get(i).getToken());
				else
					sql.setNull(7, Types.DOUBLE);
				if (imports.get(i).getTHC() != -1)
					sql.setDouble(8, imports.get(i).getTHC());
				else
					sql.setNull(8, Types.DOUBLE);
				if (imports.get(i).getTransportTransactionNo() != -1)
					sql.setInt(9, imports.get(i).getTransportTransactionNo());
				else
					sql.setNull(9, Types.INTEGER);
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE IMPORT I USING TEMP T ON I.TNUMBER = T.TNUMBER "
					+ "WHEN MATCHED THEN " + "UPDATE SET I.TO_LOC = T.TO_LOC, " + "I.FROM_LOC = T.FROM_LOC, "
					+ "I.CUSTOM_DUTY = T.CUSTOM_DUTY, " + "I.DELIVERY_ORDER = T.DELIVERY_ORDER, "
					+ "I.CLEARING_AGENT = T.CLEARING_AGENT, " + "I.TOKEN = T.TOKEN, " + "I.THC = T.THC, "
					+ "I.TTNUMBER = T.TTNUMBER " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (TNUMBER, TO_LOC, FROM_LOC, CUSTOM_DUTY, DELIVERY_ORDER, CLEARING_AGENT, TOKEN, THC, TTNUMBER) "
					+ "VALUES (T.TNUMBER, T.TO_LOC, T.FROM_LOC, T.CUSTOM_DUTY, T.DELIVERY_ORDER, T.CLEARING_AGENT, T.TOKEN, T.THC, T.TTNUMBER) "
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
