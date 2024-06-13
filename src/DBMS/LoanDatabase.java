package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;

import Core.Loan;

public class LoanDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM TRANS T, LOAN L WHERE T.NUMBER = L.TNUMBER");
			ResultSet rs = sql.executeQuery();
			ArrayList<Loan> loans = new ArrayList<Loan>();
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
				boolean isActive = rs.getBoolean("IS_ACTIVE");
				int loanTransactionNo = rs.getInt("LNUMBER");
				if (rs.wasNull())
					loanTransactionNo = -1;
				Loan loan = new Loan(number, date, time, amount, type, description, bankAccountNo, chequeNo, entityName,
						isActive, loanTransactionNo);
				loans.add(loan);
				AppLogic.Transactions.add(loan);
			}
			AppLogic.Loans.setList(loans);
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
					.prepareStatement("CREATE TABLE TEMP (TNUMBER INT, IS_ACTIVE BIT, LNUMBER INT)");
			sql.executeUpdate();
			ArrayList<Loan> loans = AppLogic.Loans.getList();
			for (int i = 0; i < loans.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?)");
				sql.setInt(1, loans.get(i).getNumber());
				sql.setBoolean(2, loans.get(i).isActive());
				if (loans.get(i).getLoanTransactionNo() != -1)
					sql.setInt(3, loans.get(i).getLoanTransactionNo());
				else
					sql.setNull(3, Types.INTEGER);
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE LOAN L USING TEMP T ON L.TNUMBER = T.TNUMBER "
					+ "WHEN MATCHED THEN " + "UPDATE SET L.IS_ACTIVE = T.IS_ACTIVE, " + "L.LNUMBER = T.LNUMBER "
					+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (TNUMBER, IS_ACTIVE, LNUMBER) "
					+ "VALUES (T.TNUMBER, T.IS_ACTIVE, T.LNUMBER) " + "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
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
