package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;

import Core.Item;
import Core.Transaction;

public class TransactionDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT T.* FROM TRANS T "
					+ "LEFT JOIN IMPORT I ON T.NUMBER = I.TNUMBER " + "LEFT JOIN TRANSPORT TR ON T.NUMBER = TR.TNUMBER "
					+ "LEFT JOIN DOMESTIC D ON T.NUMBER = D.TNUMBER "
					+ "LEFT JOIN RENEWAL_FEE RF ON T.NUMBER = RF.TNUMBER "
					+ "LEFT JOIN MANUFACTURE M ON T.NUMBER = M.TNUMBER "
					+ "LEFT JOIN SALARY SAL ON T.NUMBER = SAL.TNUMBER "
					+ "LEFT JOIN DAMAGE DMG ON T.NUMBER = DMG.TNUMBER "
					+ "LEFT JOIN MAINTENANCE MNT ON T.NUMBER = MNT.TNUMBER "
					+ "LEFT JOIN RENT R ON T.NUMBER = R.TNUMBER "
					+ "LEFT JOIN INVESTMENT INV ON T.NUMBER = INV.TNUMBER "
					+ "LEFT JOIN PROFIT P ON T.NUMBER = P.TNUMBER " + "LEFT JOIN SALE S ON T.NUMBER = S.TNUMBER "
					+ "LEFT JOIN LOAN L ON T.NUMBER = L.TNUMBER " + "WHERE I.TNUMBER IS NULL "
					+ "AND TR.TNUMBER IS NULL " + "AND D.TNUMBER IS NULL " + "AND RF.TNUMBER IS NULL "
					+ "AND M.TNUMBER IS NULL " + "AND SAL.TNUMBER IS NULL " + "AND DMG.TNUMBER IS NULL "
					+ "AND MNT.TNUMBER IS NULL " + "AND R.TNUMBER IS NULL " + "AND INV.TNUMBER IS NULL "
					+ "AND P.TNUMBER IS NULL " + "AND S.TNUMBER IS NULL " + "AND L.TNUMBER IS NULL");
			ResultSet rs = sql.executeQuery();
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			while (rs.next()) {
				int number = rs.getInt("NUMBER");
				Date date = rs.getDate("TRANS_DATE");
				Time time = rs.getTime("TRANS_TIME");
				double amount = rs.getDouble("AMOUNT");
				char type = rs.getString("TRANS_TYPE").charAt(0);
				String description = rs.getString("DESCRIPTION");
				String bankAccountNo = rs.getString("BACC_NO");
				String chequeNo = rs.getString("CNUMBER");
				String entityName = rs.getString("ENAME");
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
				Transaction transaction = new Transaction(number, date, time, amount, type, description, bankAccountNo,
						chequeNo, entityName, items);
				transactions.add(transaction);
			}
			AppLogic.Transactions.setList(transactions);
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
					"CREATE TABLE TEMP (NUMBER INT, TRANS_DATE DATE, TRANS_TIME TIME(7), AMOUNT MONEY, TRANS_TYPE CHAR(1), DESCRIPTION VARCHAR(MAX), BACC_NO VARCHAR(23), CNUMBER VARCHAR(6), ENAME VARCHAR(40))");
			sql.executeUpdate();
			ArrayList<Transaction> transactions = AppLogic.Transactions.getList();
			for (int i = 0; i < transactions.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				sql.setInt(1, transactions.get(i).getNumber());
				sql.setDate(2, transactions.get(i).getDate());
				if (transactions.get(i).getTime() == null)
					sql.setNull(3, Types.TIME);
				else if (transactions.get(i).getTime().toString().equals("00:00:00"))
					sql.setNull(3, Types.TIME);
				else
					sql.setTime(3, transactions.get(i).getTime());
				sql.setDouble(4, transactions.get(i).getAmount());
				sql.setString(5, Character.toString(transactions.get(i).getType()));
				if (transactions.get(i).getDescription() == null)
					sql.setNull(6, Types.VARCHAR);
				else if (transactions.get(i).getDescription().equals(""))
					sql.setNull(6, Types.VARCHAR);
				else
					sql.setString(6, transactions.get(i).getDescription());
				if (transactions.get(i).getBankAccountNo() == null)
					sql.setNull(7, Types.VARCHAR);
				else if (transactions.get(i).getBankAccountNo().equals(""))
					sql.setNull(7, Types.VARCHAR);
				else
					sql.setString(7, transactions.get(i).getBankAccountNo());
				if (transactions.get(i).getChequeNo() == null)
					sql.setNull(8, Types.VARCHAR);
				else if (transactions.get(i).getChequeNo().equals(""))
					sql.setNull(8, Types.VARCHAR);
				else
					sql.setString(8, transactions.get(i).getChequeNo());
				sql.setString(9, transactions.get(i).getEntityName());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE TRANS R USING TEMP T ON R.NUMBER = T.NUMBER "
					+ "WHEN MATCHED THEN " + "UPDATE SET R.TRANS_DATE = T.TRANS_DATE, "
					+ "R.TRANS_TIME = T.TRANS_TIME, " + "R.AMOUNT = T.AMOUNT, " + "R.TRANS_TYPE = T.TRANS_TYPE, "
					+ "R.DESCRIPTION = T.DESCRIPTION, " + "R.BACC_NO = T.BACC_NO, " + "R.CNUMBER = T.CNUMBER, "
					+ "R.ENAME = T.ENAME " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (NUMBER, TRANS_DATE, TRANS_TIME, AMOUNT, TRANS_TYPE, DESCRIPTION, BACC_NO, CNUMBER, ENAME) "
					+ "VALUES (T.NUMBER, T.TRANS_DATE, T.TRANS_TIME, T.AMOUNT, T.TRANS_TYPE, T.DESCRIPTION, T.BACC_NO, T.CNUMBER, T.ENAME) "
					+ "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("DROP TABLE TEMP");
			sql.execute();

			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (TNUMBER INT, PID INT, STOCK INT)");
			sql.executeUpdate();
			for (int i = 0; i < transactions.size(); i++) {
				ArrayList<Item> items = transactions.get(i).getItems();
				if (items != null)
					for (int j = 0; j < items.size(); j++) {
						sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?)");
						sql.setInt(1, transactions.get(i).getNumber());
						sql.setInt(2, items.get(j).getPriceTagID());
						sql.setInt(3, items.get(j).getQuantity());
						sql.executeUpdate();
					}
			}
			sql = _DBMSGlobals.connection
					.prepareStatement("MERGE TRANS_HAS_PRI R USING TEMP T ON R.TNUMBER = T.TNUMBER AND R.PID = T.PID "
							+ "WHEN MATCHED THEN " + "UPDATE SET R.STOCK = T.STOCK "
							+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (TNUMBER, PID, STOCK) "
							+ "VALUES (T.TNUMBER, T.PID, T.STOCK) " + "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
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
