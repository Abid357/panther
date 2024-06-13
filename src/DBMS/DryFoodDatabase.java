package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Core.DryFood;

public class DryFoodDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM PRODUCT P, DRY_FOOD D WHERE P.CODE = D.PCODE");
			ResultSet rs = sql.executeQuery();
			ArrayList<DryFood> dryFoods = new ArrayList<DryFood>();
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
				String dryFoodType = rs.getString("DF_TYPE");
				DryFood dryFood = new DryFood(code, name, size, unit, piecesPerCarton, barcode, dryFoodType);
				AppLogic.Products.add(dryFood);
			}
			AppLogic.DryFoods.setList(dryFoods);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (PCODE INT, DF_TYPE VARCHAR(20))");
			sql.executeUpdate();
			ArrayList<DryFood> dryFoods = AppLogic.DryFoods.getList();
			for (int i = 0; i < dryFoods.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
				sql.setInt(1, dryFoods.get(i).getCode());
				sql.setString(2, dryFoods.get(i).getDryFoodType());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE DRY_FOOD D USING TEMP T ON D.PCODE = T.PCODE "
					+ "WHEN MATCHED THEN " + "UPDATE SET D.DF_TYPE = T.DF_TYPE " + "WHEN NOT MATCHED BY TARGET THEN "
					+ "INSERT (PCODE, DF_TYPE) " + "VALUES (T.PCODE, T.DF_TYPE) " + "WHEN NOT MATCHED BY SOURCE THEN "
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
