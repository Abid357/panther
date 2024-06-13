package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import Core.PaymentVoucher;

public class PaymentVoucherDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM PAYMENT_VOUCHER P");
			ResultSet rs = sql.executeQuery();
			ArrayList<PaymentVoucher> paymentVouchers = new ArrayList<PaymentVoucher>();
			while (rs.next()) {
				int number = rs.getInt("NUMBER");
				Date date = rs.getDate("PAYMENT_DATE");
				String being = rs.getString("BEING");
				double amount = rs.getDouble("AMOUNT");
				String paymentMethod = rs.getString("PAYMENT_METHOD");
				String paymentTo = rs.getString("PAYMENT_TO");
				String invoiceNo = rs.getString("INVOICE");
				int lpoNo = rs.getInt("LPONUMBER");
				if (rs.wasNull())
					lpoNo = -1;
				int transactionNo = rs.getInt("TNUMBER");
				if (rs.wasNull())
					transactionNo = -1;
				PaymentVoucher paymentVoucher = new PaymentVoucher(number, date, being, amount, paymentMethod,
						paymentTo, invoiceNo, lpoNo, transactionNo);
				paymentVouchers.add(paymentVoucher);
			}
			AppLogic.PaymentVouchers.setList(paymentVouchers);
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
					"CREATE TABLE TEMP (NUMBER SMALLINT, PAYMENT_DATE DATE, BEING VARCHAR(MAX), AMOUNT MONEY, PAYMENT_METHOD VARCHAR(10), PAYMENT_TO VARCHAR(40), INVOICE VARCHAR(10), LPONUMBER SMALLINT, TNUMBER INT)");
			sql.executeUpdate();
			ArrayList<PaymentVoucher> paymentVouchers = AppLogic.PaymentVouchers.getList();
			for (int i = 0; i < paymentVouchers.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				sql.setInt(1, paymentVouchers.get(i).getNumber());
				sql.setDate(2, paymentVouchers.get(i).getDate());
				sql.setString(3, paymentVouchers.get(i).getBeing());
				sql.setDouble(4, paymentVouchers.get(i).getAmount());
				sql.setString(5, paymentVouchers.get(i).getPaymentMethod());
				sql.setString(6, paymentVouchers.get(i).getPaymentTo());
				sql.setString(7, paymentVouchers.get(i).getInvoiceNo());
				if (paymentVouchers.get(i).getLpoNo() != -1)
					sql.setInt(8, paymentVouchers.get(i).getLpoNo());
				else
					sql.setNull(8, Types.SMALLINT);
				if (paymentVouchers.get(i).getTransactionNo() != -1)
					sql.setInt(9, paymentVouchers.get(i).getTransactionNo());
				else
					sql.setNull(9, Types.INTEGER);
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection
					.prepareStatement("MERGE PAYMENT_VOUCHER P USING TEMP T ON P.NUMBER = T.NUMBER "
							+ "WHEN MATCHED THEN " + "UPDATE SET P.PAYMENT_DATE = T.PAYMENT_DATE, "
							+ "P.BEING = T.BEING, " + "P.AMOUNT = T.AMOUNT, " + "P.PAYMENT_METHOD = T.PAYMENT_METHOD, "
							+ "P.PAYMENT_TO = T.PAYMENT_TO, " + "P.INVOICE = T.INVOICE, "
							+ "P.LPONUMBER = T.LPONUMBER, " + "P.TNUMBER = T.TNUMBER "
							+ "WHEN NOT MATCHED BY TARGET THEN "
							+ "INSERT (NUMBER, PAYMENT_DATE, BEING, AMOUNT, PAYMENT_METHOD, PAYMENT_TO, INVOICE, LPONUMBER, TNUMBER) "
							+ "VALUES (T.NUMBER, T.PAYMENT_DATE, T.BEING, T.AMOUNT, T.PAYMENT_METHOD, T.PAYMENT_TO, T.INVOICE, T.LPONUMBER, T.TNUMBER) "
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
