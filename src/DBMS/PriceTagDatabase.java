package DBMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Core.OfferingTag;
import Core.PriceTag;
import Core.ProductTag;

public class PriceTagDatabase {
	public static boolean load() {
		try {
			PreparedStatement sql = _DBMSGlobals.connection.prepareStatement(
					"SELECT P.* FROM PRICETAG P " + "LEFT JOIN PRO_ASSOCIATES_PRI PAP ON P.ID = PAP.PID "
							+ "LEFT JOIN OFF_ASSOCIATES_PRI OAP ON P.ID = OAP.PID " + "WHERE PAP.PID IS NULL "
							+ "AND OAP.PID IS NULL");
			ResultSet rs = sql.executeQuery();
			ArrayList<PriceTag> priceTags = new ArrayList<PriceTag>();
			while (rs.next()) {
				int ID = rs.getInt("ID");
				double price = rs.getDouble("PRICE");
				String tag = rs.getString("TAG");
				PriceTag priceTag = new PriceTag(ID, price, tag);
				priceTags.add(priceTag);
			}
			AppLogic.PriceTags.setList(priceTags);

			sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM PRICETAG P, PRO_ASSOCIATES_PRI PAP WHERE P.ID = PAP.PID");
			rs = sql.executeQuery();
			ArrayList<ProductTag> productTags = new ArrayList<ProductTag>();
			while (rs.next()) {
				int ID = rs.getInt("ID");
				double price = rs.getDouble("PRICE");
				String tag = rs.getString("TAG");
				int productCode = rs.getInt("PCODE");
				ProductTag productTag = new ProductTag(ID, price, tag, productCode);
				productTags.add(productTag);
				AppLogic.PriceTags.add(productTag);
			}
			AppLogic.ProductTags.setList(productTags);

			sql = _DBMSGlobals.connection
					.prepareStatement("SELECT * FROM PRICETAG P, OFF_ASSOCIATES_PRI OAP WHERE P.ID = OAP.PID");
			rs = sql.executeQuery();
			ArrayList<OfferingTag> offeringTags = new ArrayList<OfferingTag>();
			while (rs.next()) {
				int ID = rs.getInt("ID");
				double price = rs.getDouble("PRICE");
				String tag = rs.getString("TAG");
				String offeringName = rs.getString("ONAME");
				String supplierName = rs.getString("SNAME");
				OfferingTag offeringTag = new OfferingTag(ID, price, tag, offeringName, supplierName);
				offeringTags.add(offeringTag);
				AppLogic.PriceTags.add(offeringTag);
			}
			AppLogic.OfferingTags.setList(offeringTags);
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
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (ID INT, PRICE MONEY, TAG VARCHAR(30))");
			sql.executeUpdate();
			ArrayList<PriceTag> priceTags = AppLogic.PriceTags.getList();
			for (int i = 0; i < priceTags.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?)");
				sql.setInt(1, priceTags.get(i).getID());
				sql.setDouble(2, priceTags.get(i).getPrice());
				sql.setString(3, priceTags.get(i).getTag());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement("MERGE PRICETAG P USING TEMP T ON P.ID = T.ID "
					+ "WHEN MATCHED THEN " + "UPDATE SET P.PRICE = T.PRICE, " + "P.TAG = T.TAG "
					+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (ID, PRICE, TAG) " + "VALUES (T.ID, T.PRICE, T.TAG) "
					+ "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("DROP TABLE TEMP");
			sql.execute();

			sql = _DBMSGlobals.connection
					.prepareStatement("IF OBJECT_ID('dbo.TEMP', 'U') IS NOT NULL " + "DROP TABLE dbo.TEMP");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("CREATE TABLE TEMP (PCODE INT, PID INT)");
			sql.executeUpdate();
			ArrayList<ProductTag> productTags = AppLogic.ProductTags.getList();
			for (int i = 0; i < productTags.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?)");
				sql.setInt(1, productTags.get(i).getProductCode());
				sql.setInt(2, productTags.get(i).getID());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection
					.prepareStatement("MERGE PRO_ASSOCIATES_PRI P USING TEMP T ON P.PCODE = T.PCODE AND P.PID = T.PID "
							+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (PCODE, PID) " + "VALUES (T.PCODE, T.PID) "
							+ "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection.prepareStatement("DROP TABLE TEMP");
			sql.execute();

			sql = _DBMSGlobals.connection
					.prepareStatement("IF OBJECT_ID('dbo.TEMP', 'U') IS NOT NULL " + "DROP TABLE dbo.TEMP");
			sql.executeUpdate();
			sql = _DBMSGlobals.connection
					.prepareStatement("CREATE TABLE TEMP (ONAME VARCHAR(40), SNAME VARCHAR(40), PID INT)");
			sql.executeUpdate();
			ArrayList<OfferingTag> offeringTags = AppLogic.OfferingTags.getList();
			for (int i = 0; i < offeringTags.size(); i++) {
				sql = _DBMSGlobals.connection.prepareStatement("INSERT TEMP VALUES (?, ?, ?)");
				sql.setString(1, offeringTags.get(i).getOfferingName());
				sql.setString(2, offeringTags.get(i).getSupplierName());
				sql.setInt(3, offeringTags.get(i).getID());
				sql.executeUpdate();
			}
			sql = _DBMSGlobals.connection.prepareStatement(
					"MERGE OFF_ASSOCIATES_PRI O USING TEMP T ON O.ONAME = T.ONAME AND O.SNAME = T.SNAME AND O.PID = T.PID "
							+ "WHEN NOT MATCHED BY TARGET THEN " + "INSERT (ONAME, SNAME, PID) "
							+ "VALUES (T.ONAME, T.SNAME, T.PID) " + "WHEN NOT MATCHED BY SOURCE THEN " + "DELETE;");
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
