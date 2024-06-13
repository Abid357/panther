package DBMS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Core.Cheque;

public class ChequeDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM CHEQUE C");
			ResultSet rs = sql.executeQuery();
			ArrayList<Cheque> cheques = new ArrayList<Cheque>();
			while (rs.next()) {
				String number = rs.getString("NUMBER");
				Date dueDate = rs.getDate("DUE_DATE");
				String bankAccountNo = rs.getString("BACC_NO");
				Cheque cheque = new Cheque(number, dueDate, bankAccountNo);
				cheques.add(cheque);
			}
			AppLogic.Cheques.setList(cheques);
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
					.prepareStatement("CREATE TABLE TEMP (NUMBER VARCHAR(6), DUE_DATE DATE, BACC_NO VARCHAR(23))");
			sql.executeUpdate();
			ArrayList<Cheque> cheques = AppLogic.Cheques.getList();
			for (int i = 0; i < cheques.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?)");
				sql.setString(1, cheques.get(i).getNumber());
				sql.setDate(2, cheques.get(i).getDueDate());
				sql.setString(3, cheques.get(i).getBankAccountNo());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE CHEQUE C USING TEMP T ON C.NUMBER = T.NUMBER AND C.BACC_NO = T.BACC_NO "
					+ "WHEN MATCHED THEN " + "UPDATE SET C.DUE_DATE = T.DUE_DATE "
					+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (NUMBER, DUE_DATE, BACC_NO) "
					+ "VALUES (T.NUMBER, T.DUE_DATE, T.BACC_NO) " + "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
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
