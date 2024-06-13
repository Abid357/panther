package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;

import Core.Item;
import Core.Sale;

public class SaleDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM TRANS T, SALE S WHERE T.NUMBER = S.TNUMBER");
			ResultSet rs = sql.executeQuery();
			ArrayList<Sale> sales = new ArrayList<Sale>();
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
				int transportTransactionNo = rs.getInt("TRNUMBER");
				if (rs.wasNull())
					transportTransactionNo = -1;
				int inventoryNo = rs.getInt("INUMBER");
				if (rs.wasNull())
					inventoryNo = -1;
				int invoiceNo = rs.getInt("NVONUMBER");
				if (rs.wasNull())
					invoiceNo = -1;
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
				Sale sale = new Sale(number, date, time, amount, type, description, bankAccountNo, chequeNo, entityName,
						items, inventoryNo, invoiceNo, transportTransactionNo);
				sales.add(sale);
				AppLogic.Transactions.add(sale);
			}
			AppLogic.Sales.setList(sales);
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
			sql = _DBMSGlobals.connection
					.prepareStatement("CREATE TABLE TEMP (TNUMBER INT, TRNUMBER INT, INUMBER INT, NVONUMBER INT)");
			sql.executeUpdate();
			ArrayList<Sale> sales = AppLogic.Sales.getList();
			for (int i = 0; i < sales.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?)");
				sql.setInt(1, sales.get(i).getNumber());
				if (sales.get(i).getTransportTransactionNo() != -1)
					sql.setInt(2, sales.get(i).getTransportTransactionNo());
				else
					sql.setNull(2, Types.INTEGER);
				if (sales.get(i).getInventoryNo() != -1)
					sql.setInt(3, sales.get(i).getInventoryNo());
				else
					sql.setNull(3, Types.INTEGER);
				if (sales.get(i).getInvoiceNo() != -1)
					sql.setInt(4, sales.get(i).getInvoiceNo());
				else
					sql.setNull(4, Types.INTEGER);
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection
					.prepareStatement("MERGE SALE S USING TEMP T ON S.TNUMBER = T.TNUMBER " + "WHEN MATCHED THEN "
							+ "UPDATE SET S.TRNUMBER = T.TRNUMBER, " + "S.INUMBER = T.INUMBER, " + "S.NVONUMBER = T.NVONUMBER "
							+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (TNUMBER, TRNUMBER, INUMBER, NVONUMBER) "
							+ "VALUES (T.TNUMBER, T.TRNUMBER, T.INUMBER, T.NVONUMBER) " + "WHEN NOT MATCHED BY SOURCE THEN "
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
