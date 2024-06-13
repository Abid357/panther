package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Core.Offering;
import Core.Supplier;
import DBMS._DBMSGlobals;
import Frame.AddPanel;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// DBMS._DBMSGlobals.loadDatabases();
		// Core.Entity e = new Core.Entity ("ABC", "123", "24", "wdad");
		// System.out.println(AppLogic.Entities.add(e));
		// System.out.println(AppLogic.Suppliers.getList());
		// ArrayList<Integer> i = new ArrayList<Integer>();
		// i.add(1);
		// i.add(2);
		// ArrayList<Offering> o = new ArrayList<Offering>();
		// o.add(new Offering("B", 'M', "bbb"));
		// Supplier s = new Supplier("AAA", "123", "123", "D", i, null);
		// System.out.println(AppLogic.Suppliers.add(s));
		// System.out.println(AppLogic.Entities.add(s));
		// AppLogic.Suppliers.update("AAA", null, null, "Dubai");
		// Core.Emp_Partner ep = new Core.Emp_Partner("CCC", "asd", null, "D", null,
		// null);
		// AppLogic.Emp_Partners.add(ep);
		// AppLogic.Investors.update("III", "asd", "asd", "asd", -1, -1, (float)0.5);
		// AppLogic.Entities.remove(AppLogic.Entities.indexOf("III"));
		// AppLogic.Drivers.update("DDD", "asd", "357", "d", null);
		// Core.Loaner l = new Core.Loaner("LLL", "asd", "wq", "asd", -1);
		// AppLogic.Entities.add(l);
		// AppLogic.Loaners.add(l);

		// ArrayList<Integer> inventoryNos = new ArrayList<Integer>();
		// inventoryNos.add(2);
		// AppLogic.
		// System.out.println(_DBMSGlobals.saveDatabases());

		// AppLogic.Suppliers.remove(AppLogic.Suppliers.indexOf("AAA"));
		// System.out.println(AppLogic.Entities.getList());
		// System.out.println(AppLogic.Suppliers.getList());
		// //
		// System.out.println(AppLogic.Entities.remove(AppLogic.Entities.indexOf("AAA")));
		// System.out.println(AppLogic.Suppliers.getList());
		// System.out.println(AppLogic.Entities.getList());
		//
		// Core.Bank b = new Core.Bank("123", "asd", 5000, null);
		// AppLogic.Banks.add(b);

		// Core.Cheque c = new Core.Cheque("www", null, "123");
		// AppLogic.Cheques.remove(AppLogic.Cheques.indexOf("www", "123"));
		// Core.Invoice pi = new Core.Invoice(10, new Date(0), new Date(0), null, null,
		// null, null, null, null, null, -1,
		// null);
		// AppLogic.Invoices.add(pi);
		// Core.LocalPurchaseOrder lpo = new Core.LocalPurchaseOrder(4, null, null,
		// null, null);
		// AppLogic.LocalPurchaseOrders.add(lpo);
		// System.out.println(AppLogic.Invoices.getList());
		// Core.ReceiptVoucher pv = new Core.ReceiptVoucher(1, new Date(0), null, 10,
		// null, null, -1, -1);
		// System.out.println(AppLogic.ReceiptVouchers.add(pv));
		// System.out.println(AppLogic.ReceiptVouchers.getList());

		// Core.Inventory i = new Core.Inventory(5, null);
		// System.out.println(AppLogic.Inventories.add(i));

		// Core.Product p = new Core.Product(5, "Juice", 30, "mg", 40, "123123");
		// AppLogic.Products.add(p);
		//
		// System.out.println(AppLogic.Products.remove(AppLogic.Products.indexOf(66)));
		//
		// Core.Offering o = new Core.Offering("qwe", 'S', "LLL");
		// AppLogic.Offerings.add(o);

		// Core.ProductTag pt = new Core.ProductTag(9, 70, "kill", 5);
		// Core.ProductTag pt1 = new Core.ProductTag(10, 70, "kill", 5);
		// Core.OfferingTag ot = new Core.OfferingTag(10, 0.35, "jill", "qwe", "LLL");
		// AppLogic.ProductTags.add(pt);
		// AppLogic.ProductTags.add(pt1);
		// AppLogic.OfferingTags.add(ot);
		// System.out.println(AppLogic.PriceTags.getList());

		// Core.Beverage bv = new Core.Beverage(55, null, -1, null, 01, "123", "Juice");
		// Core.DryFood df = new Core.DryFood(66, null, -1, "q", 7, "asd", "Muri");
		// AppLogic.Beverages.add(bv);
		// AppLogic.DryFoods.add(df);
		//
		// Core.Import i = new Core.Import(2, new Date(0), null, 40, 'C', null, null,
		// null, null, null, null, null, 1, 1,
		// 1, 1, 1, -1);
		// System.out.println(AppLogic.Imports.add(i);
		// System.out.println(AppLogic.Imports.getList());

		// Core.Transport t = new Core.Transport(3, new Date(0), null, 40, 'C', null,
		// null, null, null, null, null, null);
		// System.out.println(AppLogic.Transports.add(t));
		// System.out.println(AppLogic.Transports.getList());

		// Core.Domestic t = new Core.Domestic(6, new Date(0), null, 40, 'C', null,
		// null, null, null, null);
		// System.out.println(AppLogic.Domestics.add(t));
		// System.out.println(AppLogic.Domestics.getList());
		// System.out.println(AppLogic.Domestics.getList().get(0).getClass());

		// Core.RenewalFee t = new Core.RenewalFee(9, new Date(0), null, 40, 'C', null,
		// null, null, null, null, "Office");
		// System.out.println(AppLogic.RenewalFees.add(t));
		// System.out.println(AppLogic.RenewalFees.getList());
		// System.out.println(AppLogic.RenewalFees.getList().get(0).getClass());

		// Core.Manufacture t = new Core.Manufacture(11, new Date(0), null, 40, 'C',
		// null, null, null, null, null, 25);
		// System.out.println(AppLogic.Manufactures.add(t));
		// System.out.println(AppLogic.Manufactures.getList());
		// System.out.println(AppLogic.Transactions.getList());
		// System.out.println(AppLogic.Manufactures.getList().get(0).getClass());

		// Core.Salary t = new Core.Salary(13, new Date(0), null, 40, 'C', null, null,
		// null, null, null);
		// System.out.println(AppLogic.Salaries.add(t));
		// System.out.println(AppLogic.Salaries.getList());
		// System.out.println(AppLogic.Transactions.getList());
		// System.out.println(AppLogic.Salaries.getList().get(0).getClass());

		// Core.Damage t = new Core.Damage(15, new Date(0), null, 40, 'C', null, null,
		// null, null, null, 10, -1);
		// System.out.println(AppLogic.Damages.add(t));
		// System.out.println(AppLogic.Damages.getList());
		// System.out.println(AppLogic.Transactions.getList());
		// System.out.println(AppLogic.Damages.getList().get(0).getClass());

		// Core.Maintenance t = new Core.Maintenance(17, new Date(0), null, 40, 'C',
		// null, null, null, null, null, null);
		// System.out.println(AppLogic.Maintenances.add(t));
		// System.out.println(AppLogic.Maintenances.getList());
		// System.out.println(AppLogic.Transactions.getList());
		// System.out.println(AppLogic.Maintenances.getList().get(0).getClass());

		// Core.Rent t = new Core.Rent(19, new Date(0), null, 40, 'C', null, null, null,
		// null, null, null);
		// System.out.println(AppLogic.Rents.add(t));
		// System.out.println(AppLogic.Rents.getList());
		// for (int i = 0; i < AppLogic.Transactions.getList().size(); i++)
		// System.out.println(AppLogic.Transactions.get(i));
		// System.out.println(AppLogic.Rents.getList().get(0).getClass());

		// Core.Investment t = new Core.Investment(21, new Date(0), null, 40, 'C', null,
		// null, null, null, null);
		// System.out.println(AppLogic.Investments.add(t));
		// System.out.println(AppLogic.Investments.getList());
		// for (int i = 0; i < AppLogic.Transactions.getList().size(); i++)
		// System.out.println(AppLogic.Transactions.get(i));
		// System.out.println(AppLogic.Investments.getList().get(0).getClass());

		// Core.Profit t = new Core.Profit(23, new Date(0), null, 40, 'C', null, null,
		// null, null, null);
		// System.out.println(AppLogic.Profits.add(t));
		// System.out.println(AppLogic.Profits.getList());
		// for (int i = 0; i < AppLogic.Transactions.getList().size(); i++)
		// System.out.println(AppLogic.Transactions.get(i));
		// System.out.println(AppLogic.Profits.getList().get(0).getClass());
		//
		// Core.Sale t = new Core.Sale(25, new Date(0), null, 40, 'C', null, null, null,
		// null, null, 5, -1, -1);
		// System.out.println(AppLogic.Sales.add(t));
		// System.out.println(AppLogic.Sales.getList());
		// for (int i = 0; i < AppLogic.Transactions.getList().size(); i++)
		// System.out.println(AppLogic.Transactions.get(i));
		// System.out.println(AppLogic.Sales.getList().get(0).getClass());

		// Core.Promotion t = new Core.Promotion(27, new Date(0), null, 40, 'C', null,
		// null, null, null, null, 5, -1, true);
		// System.out.println(AppLogic.Promotions.add(t));
		// System.out.println(AppLogic.Promotions.getList());
		// for (int i = 0; i < AppLogic.Transactions.getList().size(); i++)
		// System.out.println(AppLogic.Transactions.get(i));
		// System.out.println(AppLogic.Promotions.getList().get(0).getClass());

		// HashMap <Integer, ArrayList<Integer>> priceTagIDs = new HashMap<Integer,
		// ArrayList<Integer>>();
		// ArrayList<Integer> inventory1 = new ArrayList<Integer>();
		// inventory1.add(2);
		// inventory1.add(3);
		// ArrayList<Integer> inventory5 = new ArrayList<Integer>();
		// inventory5.add(1);
		// priceTagIDs.put(1, inventory1);
		// priceTagIDs.put(5, inventory5);

		// HashMap<Integer, ArrayList<Integer>> priceTagIDs = new HashMap<Integer,
		// ArrayList<Integer>>();
		// ArrayList<Integer> tags = new ArrayList<Integer>();
		// tags.add(2);
		// tags.add(1);
		// priceTagIDs.put(3, tags);
		// //AppLogic.Productions.update(1, null, null, 10, null, -1, -1, priceTagIDs);
		// Core.Production p = new Core.Production(2, null, null, 90, null, -1, -1,
		// priceTagIDs);
		// System.out.println(AppLogic.Productions.add(p));
		// System.out.println(AppLogic.Productions.getList());

		// Core.Warehouse w = new Core.Warehouse(1, "Dubai", 1);
		// AppLogic.Warehouses.add(w);
		// System.out.println(AppLogic.Warehouses.getList());

		// ArrayList<Integer> rents = new ArrayList<Integer>();
		// rents.add(18);
		// rents.add(19);
		// ArrayList<Integer> fees = new ArrayList<Integer>();
		// fees.add(8);
		// fees.add(9);
		// Core.License l = new Core.License("123", "k", null, null, null, rents, fees);
		// AppLogic.Licenses.add(l);
		// System.out.println(AppLogic.Licenses.getList());

		// Core.Vehicle v = new Core.Vehicle("12345", "DXB", "Sedan");
		// System.out.println(AppLogic.Vehicles.add(v));
		// System.out.println(AppLogic.Vehicles.getList());

		// Core.Pocket p = new Core.Pocket("Personal", 10000);
		// AppLogic.Pockets.add(p);
		// System.out.println(AppLogic.Pockets.getList());
		
		// System.out.println(_DBMSGlobals.saveDatabases());
	}
}
