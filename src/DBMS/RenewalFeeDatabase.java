package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import Core.RenewalFee;

public class RenewalFeeDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM TRANS T, RENEWAL_FEE R WHERE T.NUMBER = R.TNUMBER");
			ResultSet rs = sql.executeQuery();
			ArrayList<RenewalFee> renewalFees = new ArrayList<RenewalFee>();
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
				String renewalType = rs.getString("FEE_TYPE");
				RenewalFee renewalFee = new RenewalFee(number, date, time, amount, type, description, bankAccountNo,
						chequeNo, entityName, renewalType);
				renewalFees.add(renewalFee);
				AppLogic.Transactions.add(renewalFee);
			}
			AppLogic.RenewalFees.setList(renewalFees);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (TNUMBER INT, FEE_TYPE VARCHAR(20))");
			sql.executeUpdate();
			ArrayList<RenewalFee> renewalFees = AppLogic.RenewalFees.getList();
			for (int i = 0; i < renewalFees.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
				sql.setInt(1, renewalFees.get(i).getNumber());
				sql.setString(2, renewalFees.get(i).getRenewalType());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE RENEWAL_FEE R USING TEMP T ON R.TNUMBER = T.TNUMBER "
					+ "WHEN MATCHED THEN " + "UPDATE SET R.FEE_TYPE = T.FEE_TYPE " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (TNUMBER, FEE_TYPE) " + "VALUES (T.TNUMBER, T.FEE_TYPE) "
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
