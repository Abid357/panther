package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import Core.Item;
import Core.Invoice;

public class InvoiceDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM INVOICE I");
			ResultSet rs = sql.executeQuery();
			ArrayList<Invoice> invoices = new ArrayList<Invoice>();
			while (rs.next()) {
				int number = rs.getInt("NUMBER");
				Date date = rs.getDate("DATE");
				String name = rs.getString("CNAME");
				String paymentTerms = rs.getString("PAYMENT_TERMS");
				int piNo = rs.getInt("PINUMBER");
				if (rs.wasNull())
					piNo = -1;
				PreparedStatement sql2 = _DBMSGlobals.connection
						.prepareStatement("SELECT * FROM NVO_CONTAINS_PRI WHERE INUMBER = ?");
				sql2.setInt(1, number);
				ResultSet rs2 = sql2.executeQuery();
				ArrayList<Item> items = new ArrayList<Item>();
				ArrayList<Double> rates = new ArrayList<Double>();
				while (rs2.next()) {
					int priceTagID = rs2.getInt("PID");
					int quantity = rs2.getInt("QUANTITY");
					if (rs2.wasNull())
						quantity = -1;
					Item item = new Item(priceTagID, quantity);
					items.add(item);
					double rate = rs2.getDouble("RATE");
					rates.add(rate);
				}
				Invoice invoice = new Invoice(number, date, name, paymentTerms, piNo, items, rates);
				invoices.add(invoice);
			}
			AppLogic.Invoices.setList(invoices);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (NUMBER SMALLINT, " + "DATE DATE, "
					+ "CNAME VARCHAR(40), " + "PAYMENT_TERMS VARCHAR(20), " + "PINUMBER SMALLINT)");
			sql.executeUpdate();
			ArrayList<Invoice> invoices = AppLogic.Invoices.getList();
			for (int i = 0; i < invoices.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?, ?)");
				sql.setInt(1, invoices.get(i).getNumber());
				sql.setDate(2, invoices.get(i).getDate());
				sql.setString(3, invoices.get(i).getName());
				sql.setString(4, invoices.get(i).getPaymentTerms());
				if (invoices.get(i).getPiNo() != -1)
					sql.setInt(5, invoices.get(i).getPiNo());
				else
					sql.setNull(5, Types.SMALLINT);
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE INVOICE I USING TEMP T ON I.NUMBER = T.NUMBER "
					+ "WHEN MATCHED THEN " + "UPDATE SET I.DATE = T.DATE, " + "I.CNAME = T.CNAME, "
					+ "I.PAYMENT_TERMS = T.PAYMENT_TERMS, " + "I.PINUMBER = T.PINUMBER "
					+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (NUMBER, DATE, CNAME, PAYMENT_TERMS, PINUMBER) "
					+ "VALUES (T.NUMBER, T.DATE, T.CNAME, T.PAYMENT_TERMS, T.PINUMBER) "
					+ "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("DROP TABLE TEMP");
			sql.execute();

			sql = _DBMSGlobals.connection.prepareStatement(
					"CREATE TABLE TEMP (INUMBER SMALLINT, PID INT, QUANTITY INT, RATE DECIMAL(7, 4))");
			sql.executeUpdate();
			for (int i = 0; i < invoices.size(); i++) {
				ArrayList<Item> items = invoices.get(i).getItems();
				ArrayList<Double> rates = invoices.get(i).getRates();
				if (items != null)
					for (int j = 0; j < items.size(); j++) {
						sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?)");
						sql.setInt(1, invoices.get(i).getNumber());
						sql.setInt(2, items.get(j).getPriceTagID());
						if (items.get(j).getQuantity() != -1)
							sql.setInt(3, items.get(j).getQuantity());
						else
							sql.setNull(3, Types.INTEGER);
						if (rates.get(j) != -1)
							sql.setDouble(4, rates.get(j));
						else
							sql.setNull(4, Types.DECIMAL);
						sql.executeUpdate();
					}
			}
			sql = _DBMSGlobals.connection.prepareStatement(
					"MERGE NVO_CONTAINS_PRI N USING TEMP T ON N.INUMBER = T.INUMBER AND N.PID = T.PID "
							+ "WHEN MATCHED THEN " + "UPDATE SET N.QUANTITY = T.QUANTITY, " + "N.RATE = T.RATE "
							+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (INUMBER, PID, QUANTITY, RATE) "
							+ "VALUES (T.INUMBER, T.PID, T.QUANTITY, T.RATE) " + "WHEN NOT MATCHED BY SOURCE THEN "
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
