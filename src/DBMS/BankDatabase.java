package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Core.Bank;

public class BankDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM BANK B");
			ResultSet rs = sql.executeQuery();
			ArrayList<Bank> banks = new ArrayList<Bank>();
			while (rs.next()) {
				String accountNo = rs.getString("ACCOUNT_NO");
				String name = rs.getString("NAME");
				double balance = rs.getDouble("BALANCE");
				String entityName = rs.getString("ENAME");
				PreparedStatement sql2 = _DBMSGlobals.connection
						.prepareStatement("SELECT * FROM BANK_PROVIDES_LOAN WHERE BACC_NO = ?");
				sql2.setString(1, accountNo);
				ResultSet rs2 = sql2.executeQuery();
				ArrayList<Integer> loanTransactionNos = new ArrayList<Integer>();
				while (rs2.next()) {
					int number = rs2.getInt("LNUMBER");
					loanTransactionNos.add(number);
				}
				Bank bank = new Bank(accountNo, name, balance, loanTransactionNos, entityName);
				banks.add(bank);
			}
			AppLogic.Banks.setList(banks);
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
					"CREATE TABLE TEMP (ACCOUNT_NO VARCHAR(23), NAME VARCHAR(40), BALANCE MONEY, ENAME VARCHAR(40))");
			sql.executeUpdate();
			ArrayList<Bank> banks = AppLogic.Banks.getList();
			for (int i = 0; i < banks.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?)");
				sql.setString(1, banks.get(i).getAccountNo());
				sql.setString(2, banks.get(i).getName());
				sql.setDouble(3, banks.get(i).getBalance());
				sql.setString(4, banks.get(i).getEntityName());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection
					.prepareStatement("MERGE BANK B USING TEMP T ON B.ACCOUNT_NO = T.ACCOUNT_NO " + "WHEN MATCHED THEN "
							+ "UPDATE SET B.NAME = T.NAME, " + "B.BALANCE = T.BALANCE, " + "B.ENAME = T.ENAME "
							+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (ACCOUNT_NO, NAME, BALANCE, ENAME) "
							+ "VALUES (T.ACCOUNT_NO, T.NAME, T.BALANCE, T.ENAME) " + "WHEN NOT MATCHED BY SOURCE THEN "
							+ "DELETE;");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("DROP TABLE TEMP");
			sql.execute();

			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (BACC_NO VARCHAR(23), LNUMBER INT)");
			sql.executeUpdate();
			for (int i = 0; i < banks.size(); i++) {
				ArrayList<Integer> loanTransactionNos = banks.get(i).getLoanTransactionNos();
				if (loanTransactionNos != null)
					for (int j = 0; j < loanTransactionNos.size(); j++) {
						sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
						sql.setString(1, banks.get(i).getAccountNo());
						sql.setInt(2, loanTransactionNos.get(j));
						sql.executeUpdate();
					}
			}
			sql = _DBMSGlobals.connection.prepareStatement(
					"MERGE BANK_PROVIDES_LOAN B USING TEMP T ON B.BACC_NO = T.BACC_NO AND B.LNUMBER = T.LNUMBER "
							+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (BACC_NO, LNUMBER) "
							+ "VALUES (T.BACC_NO, T.LNUMBER) " + "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
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
