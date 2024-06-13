package DBMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class _DBMSGlobals {
	private static String URL = "jdbc:sqlserver://192.168.1.105:49172;";
	private static String database = "database=FarukDatabase;";
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String username = "sa";
	private static String password = "farh2024";

	public static Connection connection = null;
	
	public static boolean loadDatabases() {
		boolean areLoaded = false;
		try {
			Class.forName(_DBMSGlobals.driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		try {
			connection = DriverManager.getConnection(_DBMSGlobals.URL + _DBMSGlobals.database, _DBMSGlobals.username,
					_DBMSGlobals.password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		areLoaded = (AppLogic.Entities.loadList() && AppLogic.Suppliers.loadList() && AppLogic.Emp_Partners.loadList()
				&& AppLogic.Investors.loadList() && AppLogic.Drivers.loadList() && AppLogic.Loaners.loadList()
				&& AppLogic.Customers.loadList() && AppLogic.Banks.loadList() && AppLogic.Cheques.loadList()
				&& AppLogic.ProformaInvoices.loadList() && AppLogic.Invoices.loadList()
				&& AppLogic.LocalPurchaseOrders.loadList() && AppLogic.PaymentVouchers.loadList()
				&& AppLogic.ReceiptVouchers.loadList() && AppLogic.Inventories.loadList()
				&& AppLogic.Products.loadList() && AppLogic.Offerings.loadList() && AppLogic.PriceTags.loadList()
				&& AppLogic.Beverages.loadList() && AppLogic.DryFoods.loadList() && AppLogic.Transactions.loadList()
				&& AppLogic.Imports.loadList() && AppLogic.Transports.loadList() && AppLogic.Domestics.loadList()
				&& AppLogic.RenewalFees.loadList() && AppLogic.Manufactures.loadList() && AppLogic.Salaries.loadList()
				&& AppLogic.Damages.loadList() && AppLogic.Maintenances.loadList() && AppLogic.Rents.loadList()
				&& AppLogic.Investments.loadList() && AppLogic.Profits.loadList() && AppLogic.Sales.loadList()
				&& AppLogic.Promotions.loadList() && AppLogic.Productions.loadList() && AppLogic.Warehouses.loadList()
				&& AppLogic.Licenses.loadList() && AppLogic.Vehicles.loadList() && AppLogic.Loans.loadList()
				&& AppLogic.Pockets.loadList());
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return areLoaded;
	}

	public static boolean saveDatabases() {
		try {
			Class.forName(_DBMSGlobals.driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		try {
			connection = DriverManager.getConnection(_DBMSGlobals.URL + _DBMSGlobals.database, _DBMSGlobals.username,
					_DBMSGlobals.password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if (AppLogic.Entities.isDirty())
			if(!AppLogic.Entities.saveList())
				return false;
		if (AppLogic.Inventories.isDirty())
			if(!AppLogic.Inventories.saveList())
				return false;
		if (AppLogic.Licenses.isDirty())
			if(!AppLogic.Licenses.saveList())
				return false;
		if (AppLogic.PriceTags.isDirty())
			if(!AppLogic.PriceTags.saveList())
				return false;
		if (AppLogic.Products.isDirty())
			if(!AppLogic.Products.saveList())
				return false;
		if (AppLogic.Vehicles.isDirty())
			if(!AppLogic.Vehicles.saveList())
				return false;
		if (AppLogic.Transactions.isDirty())
			if(!AppLogic.Transactions.saveList())
				return false;
		if (AppLogic.Loans.isDirty())
			if(!AppLogic.Loans.saveList())
				return false;
		if (AppLogic.Transports.isDirty())
			if(!AppLogic.Transports.saveList())
				return false;
		if (AppLogic.Imports.isDirty())
			if(!AppLogic.Imports.saveList())
				return false;
		if (AppLogic.Purchases.isDirty())
			if(!AppLogic.Purchases.saveList())
				return false;
		if (AppLogic.Rents.isDirty())
			if(!AppLogic.Rents.saveList())
				return false;
		if (AppLogic.Suppliers.isDirty())
			if(!AppLogic.Suppliers.saveList())
				return false;
		if (AppLogic.RenewalFees.isDirty())
			if(!AppLogic.RenewalFees.saveList())
				return false;
		if (AppLogic.Productions.isDirty())
			if(!AppLogic.Productions.saveList())
				return false;
		if (AppLogic.LocalPurchaseOrders.isDirty())
			if(!AppLogic.LocalPurchaseOrders.saveList())
				return false;
		if (AppLogic.ProformaInvoices.isDirty())
			if(!AppLogic.ProformaInvoices.saveList())
				return false;
		if (AppLogic.Invoices.isDirty())
			if(!AppLogic.Invoices.saveList())
				return false;
		if (AppLogic.Offerings.isDirty())
			if(!AppLogic.Offerings.saveList())
				return false;
		if (AppLogic.Banks.isDirty())
			if(!AppLogic.Banks.saveList())
				return false;
		if (AppLogic.Cheques.isDirty())
			if(!AppLogic.Cheques.saveList())
				return false;
		if (AppLogic.Warehouses.isDirty())
			if(!AppLogic.Warehouses.saveList())
				return false;
		if (AppLogic.Beverages.isDirty())
			if(!AppLogic.Beverages.saveList())
				return false;
		if (AppLogic.DryFoods.isDirty())
			if(!AppLogic.DryFoods.saveList())
				return false;
		if (AppLogic.Damages.isDirty())
			if(!AppLogic.Damages.saveList())
				return false;
		if (AppLogic.Domestics.isDirty())
			if(!AppLogic.Domestics.saveList())
				return false;
		if (AppLogic.Drivers.isDirty())
			if(!AppLogic.Drivers.saveList())
				return false;
		if (AppLogic.Emp_Partners.isDirty())
			if(!AppLogic.Emp_Partners.saveList())
				return false;
		if (AppLogic.Investments.isDirty())
			if(!AppLogic.Investments.saveList())
				return false;
		if (AppLogic.Investors.isDirty())
			if(!AppLogic.Investors.saveList())
				return false;
		if (AppLogic.Loaners.isDirty())
			if(!AppLogic.Loaners.saveList())
				return false;
		if (AppLogic.Customers.isDirty())
			if(!AppLogic.Customers.saveList())
				return false;
		if (AppLogic.Maintenances.isDirty())
			if(!AppLogic.Maintenances.saveList())
				return false;
		if (AppLogic.Manufactures.isDirty())
			if(!AppLogic.Manufactures.saveList())
				return false;
		if (AppLogic.PaymentVouchers.isDirty())
			if(!AppLogic.PaymentVouchers.saveList())
				return false;
		if (AppLogic.ReceiptVouchers.isDirty())
			if(!AppLogic.ReceiptVouchers.saveList())
				return false;
		if (AppLogic.Profits.isDirty())
			if(!AppLogic.Profits.saveList())
				return false;
		if (AppLogic.Promotions.isDirty())
			if(!AppLogic.Promotions.saveList())
				return false;
		if (AppLogic.Salaries.isDirty())
			if(!AppLogic.Salaries.saveList())
				return false;
		if (AppLogic.Sales.isDirty())
			if(!AppLogic.Sales.saveList())
				return false;
		if (AppLogic.Pockets.isDirty())
			if(!AppLogic.Pockets.saveList())
				return false;
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
