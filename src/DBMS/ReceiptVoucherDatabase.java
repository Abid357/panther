package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import Core.ReceiptVoucher;

public class ReceiptVoucherDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM RECEIPT_VOUCHER R");
			ResultSet rs = sql.executeQuery();
			ArrayList<ReceiptVoucher> receiptVouchers = new ArrayList<ReceiptVoucher>();
			while (rs.next()) {
				int number = rs.getInt("NUMBER");
				Date date = rs.getDate("RECEIPT_DATE");
				String being = rs.getString("BEING");
				double amount = rs.getDouble("AMOUNT");
				String receiptMethod = rs.getString("RECEIPT_METHOD");
				String receiptTo = rs.getString("RECEIPT_TO");
				int invoiceNo = rs.getInt("INUMBER");
				if (rs.wasNull())
					invoiceNo = -1;
				int transactionNo = rs.getInt("TNUMBER");
				if (rs.wasNull())
					transactionNo = -1;
				ReceiptVoucher receiptVoucher = new ReceiptVoucher(number, date, being, amount, receiptMethod,
						receiptTo, invoiceNo, transactionNo);
				receiptVouchers.add(receiptVoucher);
			}
			AppLogic.ReceiptVouchers.setList(receiptVouchers);
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
					"CREATE TABLE TEMP (NUMBER SMALLINT, RECEIPT_DATE DATE, BEING VARCHAR(MAX), AMOUNT MONEY, RECEIPT_METHOD VARCHAR(10), RECEIPT_TO VARCHAR(40), INUMBER SMALLINT, TNUMBER INT)");
			sql.executeUpdate();
			ArrayList<ReceiptVoucher> receiptVouchers = AppLogic.ReceiptVouchers.getList();
			for (int i = 0; i < receiptVouchers.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				sql.setInt(1, receiptVouchers.get(i).getNumber());
				sql.setDate(2, receiptVouchers.get(i).getDate());
				sql.setString(3, receiptVouchers.get(i).getBeing());
				sql.setDouble(4, receiptVouchers.get(i).getAmount());
				sql.setString(5, receiptVouchers.get(i).getReceiptMethod());
				sql.setString(6, receiptVouchers.get(i).getReceiptTo());
				if (receiptVouchers.get(i).getinvoiceNo() != -1)
					sql.setInt(7, receiptVouchers.get(i).getinvoiceNo());
				else
					sql.setNull(7, Types.SMALLINT);
				if (receiptVouchers.get(i).getTransactionNo() != -1)
					sql.setInt(8, receiptVouchers.get(i).getTransactionNo());
				else
					sql.setNull(8, Types.INTEGER);
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection
					.prepareStatement("MERGE RECEIPT_VOUCHER R USING TEMP T ON R.NUMBER = T.NUMBER "
							+ "WHEN MATCHED THEN " + "UPDATE SET R.RECEIPT_DATE = T.RECEIPT_DATE, "
							+ "R.BEING = T.BEING, " + "R.AMOUNT = T.AMOUNT, " + "R.RECEIPT_METHOD = T.RECEIPT_METHOD, "
							+ "R.RECEIPT_TO = T.RECEIPT_TO, " + "R.INUMBER = T.INUMBER, " + "R.TNUMBER = T.TNUMBER "
							+ "WHEN NOT MATCHED BY TARGET THEN "
							+ "INSERT (NUMBER, RECEIPT_DATE, BEING, AMOUNT, RECEIPT_METHOD, RECEIPT_TO, INUMBER, TNUMBER) "
							+ "VALUES (T.NUMBER, T.RECEIPT_DATE, T.BEING, T.AMOUNT, T.RECEIPT_METHOD, T.RECEIPT_TO, T.INUMBER, T.TNUMBER) "
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
