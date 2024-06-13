package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import Core.Item;
import Core.ProformaInvoice;

public class ProformaInvoiceDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM PROFORMA_INVOICE P");
			ResultSet rs = sql.executeQuery();
			ArrayList<ProformaInvoice> proformaInvoices = new ArrayList<ProformaInvoice>();
			while (rs.next()) {
				int number = rs.getInt("NUMBER");
				Date issueDate = rs.getDate("ISSUE_DATE");
				Date validity = rs.getDate("VALIDITY");
				String shippingLocation = rs.getString("SHIPPING_LOCATION");
				String shippingMethod = rs.getString("SHIPPING_METHOD");
				String shippingTerms = rs.getString("SHIPPING_TERMS");
				String paymentTerms = rs.getString("PAYMENT_TERMS");
				String notes = rs.getString("NOTES");
				String consignee = rs.getString("CONSIGNEE");
				String lpoNo = rs.getString("LPO");
				PreparedStatement sql2 = _DBMSGlobals.connection
						.prepareStatement("SELECT * FROM PI_CONTAINS_PRI WHERE PINUMBER = ?");
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
				ProformaInvoice proformaInvoice = new ProformaInvoice(number, issueDate, validity, shippingLocation,
						shippingMethod, shippingTerms, paymentTerms, notes, consignee, lpoNo, items);
				proformaInvoices.add(proformaInvoice);
			}
			AppLogic.ProformaInvoices.setList(proformaInvoices);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (NUMBER SMALLINT, " + "ISSUE_DATE DATE, "
					+ "VALIDITY DATE, " + "SHIPPING_LOCATION VARCHAR(40), " + "SHIPPING_METHOD VARCHAR(10), "
					+ "SHIPPING_TERMS VARCHAR(10), " + "PAYMENT_TERMS VARCHAR(10), " + "NOTES VARCHAR(MAX), "
					+ "CONSIGNEE VARCHAR(40), " + "LPO VARCHAR(10))");
			sql.executeUpdate();
			ArrayList<ProformaInvoice> proformaInvoices = AppLogic.ProformaInvoices.getList();
			for (int i = 0; i < proformaInvoices.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				sql.setInt(1, proformaInvoices.get(i).getNumber());
				sql.setDate(2, proformaInvoices.get(i).getIssueDate());
				sql.setDate(3, proformaInvoices.get(i).getValidity());
				sql.setString(4, proformaInvoices.get(i).getShippingLocation());
				sql.setString(5, proformaInvoices.get(i).getShippingMethod());
				sql.setString(6, proformaInvoices.get(i).getShippingTerms());
				sql.setString(7, proformaInvoices.get(i).getPaymentTerms());
				sql.setString(8, proformaInvoices.get(i).getNotes());
				sql.setString(9, proformaInvoices.get(i).getConsignee());
				sql.setString(10, proformaInvoices.get(i).getLpoNo());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection
					.prepareStatement("MERGE PROFORMA_INVOICE P USING TEMP T ON P.NUMBER = T.NUMBER "
							+ "WHEN MATCHED THEN " + "UPDATE SET P.ISSUE_DATE = T.ISSUE_DATE, "
							+ "P.VALIDITY = T.VALIDITY, " + "P.SHIPPING_LOCATION = T.SHIPPING_LOCATION, "
							+ "P.SHIPPING_METHOD = T.SHIPPING_METHOD, " + "P.SHIPPING_TERMS = T.SHIPPING_TERMS, "
							+ "P.PAYMENT_TERMS = T.PAYMENT_TERMS, " + "P.NOTES = T.NOTES, "
							+ "P.CONSIGNEE = T.CONSIGNEE, " + "P.LPO = T.LPO " + "WHEN NOT MATCHED BY TARGET THEN "
							+ "INSERT (NUMBER, ISSUE_DATE, VALIDITY, SHIPPING_LOCATION, SHIPPING_METHOD, SHIPPING_TERMS, PAYMENT_TERMS, NOTES, CONSIGNEE, LPO) "
							+ "VALUES (T.NUMBER, T.ISSUE_DATE, T.VALIDITY, T.SHIPPING_LOCATION, T.SHIPPING_METHOD, T.SHIPPING_TERMS, T.PAYMENT_TERMS, T.NOTES, T.CONSIGNEE, T.LPO) "
							+ "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("DROP TABLE TEMP");
			sql.execute();

			sql = _DBMSGlobals.connection
					.prepareStatement("CREATE TABLE TEMP (PINUMBER SMALLINT, PID INT, QUANTITY INT)");
			sql.executeUpdate();
			for (int i = 0; i < proformaInvoices.size(); i++) {
				ArrayList<Item> items = proformaInvoices.get(i).getItems();
				if (items != null)
					for (int j = 0; j < items.size(); j++) {
						sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?)");
						sql.setInt(1, proformaInvoices.get(i).getNumber());
						sql.setInt(2, items.get(j).getPriceTagID());
						if (items.get(j).getQuantity() != -1)
							sql.setInt(3, items.get(j).getQuantity());
						else
							sql.setNull(3, Types.INTEGER);
						sql.executeUpdate();
					}
			}
			sql = _DBMSGlobals.connection.prepareStatement(
					"MERGE PI_CONTAINS_PRI P USING TEMP T ON P.PINUMBER = T.PINUMBER AND P.PID = T.PID "
							+ "WHEN MATCHED THEN " + "UPDATE SET P.QUANTITY = T.QUANTITY "
							+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (PINUMBER, PID, QUANTITY) "
							+ "VALUES (T.PINUMBER, T.PID, T.QUANTITY) " + "WHEN NOT MATCHED BY SOURCE THEN "
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
