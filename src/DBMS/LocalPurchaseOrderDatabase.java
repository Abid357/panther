package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import Core.Item;
import Core.LocalPurchaseOrder;

public class LocalPurchaseOrderDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM LPO L");
			ResultSet rs = sql.executeQuery();
			ArrayList<LocalPurchaseOrder> localPurchaseOrders = new ArrayList<LocalPurchaseOrder>();
			while (rs.next()) {
				int number = rs.getInt("NUMBER");
				Date issueDate = rs.getDate("ISSUE_DATE");
				String invoiceNo = rs.getString("INVOICE");
				String sendToName = rs.getString("SEND_TO_ENAME");
				PreparedStatement sql2 = _DBMSGlobals.connection
						.prepareStatement("SELECT * FROM LPO_CONTAINS_PRI WHERE LPONUMBER = ?");
				sql2.setInt(1, number);
				ResultSet rs2 = sql2.executeQuery();
				ArrayList<Item> items = new ArrayList<Item>();
				while (rs2.next()) {
					int priceTagID = rs2.getInt("PID");
					int quantity = rs2.getInt("QUANTITY");
					if (rs2.wasNull())
						quantity = -1;
					Item item = new Item(priceTagID, quantity);
					items.add(item);
				}
				LocalPurchaseOrder proformaLocalPurchaseOrder = new LocalPurchaseOrder(number, issueDate, invoiceNo,
						sendToName, items);
				localPurchaseOrders.add(proformaLocalPurchaseOrder);
			}
			AppLogic.LocalPurchaseOrders.setList(localPurchaseOrders);
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
					"CREATE TABLE TEMP (NUMBER SMALLINT, ISSUE_DATE DATE, INVOICE VARCHAR(10), SEND_TO_ENAME VARCHAR(40))");
			sql.executeUpdate();
			ArrayList<LocalPurchaseOrder> localPurchaseOrders = AppLogic.LocalPurchaseOrders.getList();
			for (int i = 0; i < localPurchaseOrders.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?)");
				sql.setInt(1, localPurchaseOrders.get(i).getNumber());
				sql.setDate(2, localPurchaseOrders.get(i).getIssueDate());
				sql.setString(3, localPurchaseOrders.get(i).getinvoiceNo());
				sql.setString(4, localPurchaseOrders.get(i).getSendToName());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE LPO L USING TEMP T ON L.NUMBER = T.NUMBER "
					+ "WHEN MATCHED THEN " + "UPDATE SET L.ISSUE_DATE = T.ISSUE_DATE, " + "L.INVOICE = T.INVOICE, "
					+ "L.SEND_TO_ENAME = T.SEND_TO_ENAME " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (NUMBER, ISSUE_DATE, INVOICE, SEND_TO_ENAME) "
					+ "VALUES (T.NUMBER, T.ISSUE_DATE, T.INVOICE, T.SEND_TO_ENAME) "
					+ "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("DROP TABLE TEMP");
			sql.execute();

			sql = _DBMSGlobals.connection
					.prepareStatement("CREATE TABLE TEMP (LPONUMBER SMALLINT, PID INT, QUANTITY INT)");
			sql.executeUpdate();
			for (int i = 0; i < localPurchaseOrders.size(); i++) {
				ArrayList<Item> items = localPurchaseOrders.get(i).getItems();
				if (items != null)
					for (int j = 0; j < items.size(); j++) {
						sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?)");
						sql.setInt(1, localPurchaseOrders.get(i).getNumber());
						sql.setInt(2, items.get(j).getPriceTagID());
						if (items.get(j).getQuantity() != -1)
							sql.setInt(3, items.get(j).getQuantity());
						else
							sql.setNull(3, Types.INTEGER);
						sql.executeUpdate();
					}
			}
			sql = _DBMSGlobals.connection.prepareStatement(
					"MERGE LPO_CONTAINS_PRI L USING TEMP T ON L.LPONUMBER = T.LPONUMBER AND L.PID = T.PID "
							+ "WHEN MATCHED THEN " + "UPDATE SET L.QUANTITY = T.QUANTITY "
							+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (LPONUMBER, PID, QUANTITY) "
							+ "VALUES (T.LPONUMBER, T.PID, T.QUANTITY) " + "WHEN NOT MATCHED BY SOURCE THEN "
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
