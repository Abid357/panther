package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Core.Beverage;

public class BeverageDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM PRODUCT P, BEVERAGE B WHERE P.CODE = B.PCODE");
			ResultSet rs = sql.executeQuery();
			ArrayList<Beverage> beverages = new ArrayList<Beverage>();
			while (rs.next()) {
				int code = rs.getInt("CODE");
				String name = rs.getString("NAME");
				double size = rs.getDouble("SIZE");
				if (rs.wasNull())
					size = -1;
				String unit = rs.getString("UNIT");
				int piecesPerCarton = rs.getInt("PCS_PER_CARTON");
				if (rs.wasNull())
					piecesPerCarton = -1;
				String barcode = rs.getString("BARCODE");
				String beverageType = rs.getString("B_TYPE");
				Beverage beverage = new Beverage(code, name, size, unit, piecesPerCarton, barcode, beverageType);
				AppLogic.Products.add(beverage);
			}
			AppLogic.Beverages.setList(beverages);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (PCODE INT, B_TYPE VARCHAR(20))");
			sql.executeUpdate();
			ArrayList<Beverage> beverages = AppLogic.Beverages.getList();
			for (int i = 0; i < beverages.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
				sql.setInt(1, beverages.get(i).getCode());
				sql.setString(2, beverages.get(i).getBeverageType());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE BEVERAGE B USING TEMP T ON B.PCODE = T.PCODE "
					+ "WHEN MATCHED THEN " + "UPDATE SET B.B_TYPE = T.B_TYPE " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (PCODE, B_TYPE) " + "VALUES (T.PCODE, T.B_TYPE) " + "WHEN NOT MATCHED BY SOURCE THEN "
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
