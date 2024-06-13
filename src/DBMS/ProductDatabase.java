package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import Core.Product;

public class ProductDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement("SELECT * FROM PRODUCT P");
			ResultSet rs = sql.executeQuery();
			ArrayList<Product> products = new ArrayList<Product>();
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
				Product product = new Product(code, name, size, unit, piecesPerCarton, barcode);
				products.add(product);
			}
			AppLogic.Products.setList(products);
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
					"CREATE TABLE TEMP (CODE SMALLINT, NAME VARCHAR(30), SIZE DECIMAL(18, 0), UNIT CHAR(2), PCS_PER_CARTON INT, BARCODE VARCHAR(14))");
			sql.executeUpdate();
			ArrayList<Product> products = AppLogic.Products.getList();
			for (int i = 0; i < products.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?, ?, ?, ?)");
				sql.setInt(1, products.get(i).getCode());
				sql.setString(2, products.get(i).getName());
				if (products.get(i).getSize() != -1)
					sql.setDouble(3, products.get(i).getSize());
				else
					sql.setNull(3, Types.DECIMAL);
				sql.setString(4, products.get(i).getUnit());
				if (products.get(i).getPiecesPerCarton() != -1)
					sql.setInt(5, products.get(i).getPiecesPerCarton());
				else
					sql.setNull(5, Types.INTEGER);
				sql.setString(6, products.get(i).getBarcode());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE PRODUCT P USING TEMP T ON P.CODE = T.CODE "
					+ "WHEN MATCHED THEN " + "UPDATE SET P.NAME = T.NAME, " + "P.SIZE = T.SIZE, " + "P.UNIT = T.UNIT, "
					+ "P.PCS_PER_CARTON = T.PCS_PER_CARTON, " + "P.BARCODE = T.BARCODE "
					+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (CODE, NAME, SIZE, UNIT, PCS_PER_CARTON, BARCODE) "
					+ "VALUES (T.CODE, T.NAME, T.SIZE, T.UNIT, T.PCS_PER_CARTON, T.BARCODE) "
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
