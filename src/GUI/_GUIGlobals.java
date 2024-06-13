/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Core.Bank;
import Core.Inventory;
import Core.Item;
import Core.License;
import Core.Loan;
import Core.Offering;
import Core.PaymentVoucher;
import Core.PriceTag;
import Core.Product;
import Core.ProductTag;
import Core.ReceiptVoucher;
import Core.Vehicle;
import Core.Warehouse;
import Frame.Notification;

/**
 *
 * @author Abid-Temp
 */
public class _GUIGlobals {
	public static String directory = Main.appDirectory;
	public static Color fontColor = new Color(166, 130, 52);
	public static int activeFrames = 0;

	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static int screenWidth = (int) toolkit.getScreenSize().getWidth();
	public static int screenHeight = (int) toolkit.getScreenSize().getHeight();
	public static int frameWidth = (int) (screenWidth / 2.953846154);
	public static int frameHeight = (int) (screenHeight / 1.5);
	public static int panelWidth = (int) (screenWidth / 2.962962963);
	public static int panelHeight = (int) (screenHeight / 11.25);

	public static int getFontSize_15() {
		double wfactor = 128;
		double hfactor = 72;
		double fontWidth = screenWidth / wfactor;
		double fontHeight = screenHeight / hfactor;
		double average = (fontWidth + fontHeight) / 2;
		return (int) Math.rint(average);
	}

	public static int getFontSize_18() {
		double wfactor = 106.6666667;
		double hfactor = 60;
		double fontWidth = screenWidth / wfactor;
		double fontHeight = screenHeight / hfactor;
		double average = (fontWidth + fontHeight) / 2;
		return (int) Math.rint(average);
	}

	public static int getFontSize_13() {
		double wfactor = 147.6923077;
		double hfactor = 83.07692308;
		double fontWidth = screenWidth / wfactor;
		double fontHeight = screenHeight / hfactor;
		double average = (fontWidth + fontHeight) / 2;
		return (int) Math.rint(average);
	}

	public static int getFontSize_14() {
		double wfactor = 137.1428571;
		double hfactor = 77.14285714;
		double fontWidth = screenWidth / wfactor;
		double fontHeight = screenHeight / hfactor;
		double average = (fontWidth + fontHeight) / 2;
		return (int) Math.rint(average);
	}

	public static int getFontSize_24() {
		double wfactor = 80;
		double hfactor = 45;
		double fontWidth = screenWidth / wfactor;
		double fontHeight = screenHeight / hfactor;
		double average = (fontWidth + fontHeight) / 2;
		return (int) Math.rint(average);
	}

	public static int getFontSize_Forms() {
		double wfactor = 50;
		double hfactor = 28.125;
		double fontWidth = screenWidth / wfactor;
		double fontHeight = screenHeight / hfactor;
		double average = (fontWidth + fontHeight) / 2;
		return (int) Math.rint(average);
	}

	public static String formatBank(Bank bank) {
		String string = "";
		if (bank.getName() != null)
			for (int j = 0; j < bank.getName().length(); j++)
				if (Character.isUpperCase(bank.getName().charAt(j)))
					string += bank.getName().charAt(j);
		string += " [-";
		string += bank.getAccountNo().substring(bank.getAccountNo().length() - 4);
		string += "]";
		return string;
	}

	public static Bank parseBank(String bankString) {
		if (bankString.contains("[-")) {
			bankString = bankString.substring(bankString.indexOf("[-") + 2);
			bankString = bankString.substring(0, bankString.indexOf("]"));
			ArrayList<Bank> banks = AppLogic.Banks.getList();
			for (Bank b : banks) {
				if (b.getAccountNo().contains(bankString))
					return b;
			}
			return null;
		} else
			return null;
	}

	public static String formatVehicle(Vehicle vehicle) {
		String string = "";
		String plateNo = vehicle.getPlateNo();
		String type = vehicle.getType();
		string += plateNo + " [" + type + "]";
		return string;
	}

	public static Vehicle parseVehicle(String vehicleString) {
		vehicleString = vehicleString.substring(0, vehicleString.indexOf(" ["));
		return AppLogic.Vehicles.get(AppLogic.Vehicles.indexOf(vehicleString));
	}

	public static String formatLoan(Loan loan, double remainder) {
		String string = "";
		String date = new SimpleDateFormat("dd MMM yyyy").format(loan.getDate());
		string += formatMoney(loan.getAmount()) + " AED [" + date + "], " + formatMoney(remainder) + " left";
		return string;
	}

	public static Loan parseLoan(String loanString, String entityName) {
		String amountString = loanString.substring(0, loanString.indexOf(" AED"));
		double amount = Double.parseDouble(amountString);
		String dateString = loanString.substring(loanString.indexOf("[") + 1);
		dateString = dateString.substring(0, dateString.indexOf("]"));
		Date date = null;
		try {
			date = new Date(new SimpleDateFormat("dd MMM yyyy").parse(dateString).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Loan> loans = AppLogic.Loans.getList();
		for (int i = 0; i < loans.size(); i++) {
			if (loans.get(i).getAmount() == amount && loans.get(i).getDate().equals(date))
				if (loans.get(i).getEntityName() != null) {
					if (entityName != null)
						if (loans.get(i).getEntityName().equals(entityName))
							return loans.get(i);
						else
							return null;
				} else
					return loans.get(i);
		}
		return null;
	}

	public static String formatLicense(License license) {
		String string = "";
		if (license != null) {
			for (int j = 0; j < license.getName().length(); j++)
				if (Character.isUpperCase(license.getName().charAt(j)))
					string += license.getName().charAt(j);
			string += " [-";
			string += license.getNumber().substring(license.getNumber().length() - 4);
			string += "]";
			return string;
		}
		return null;
	}

	public static License parseLicense(String licenseString) {
		licenseString = licenseString.substring(licenseString.indexOf("[-") + 2);
		licenseString = licenseString.substring(0, licenseString.indexOf("]"));
		ArrayList<License> licenses = AppLogic.Licenses.getList();
		for (License l : licenses) {
			if (l.getNumber().contains(licenseString))
				return l;
		}
		return null;
	}

	public static String formatWarehouse(Warehouse warehouse) {
		String string = "W" + warehouse.getNumber() + ":" + warehouse.getLocation();
		return string;
	}

	public static Warehouse parseWarehouse(String warehouseString) {
		String warehouseNo = warehouseString.substring(warehouseString.indexOf("W") + 1);
		warehouseNo = warehouseNo.substring(0, warehouseNo.indexOf(":"));
		String warehouseLoc = warehouseString.substring(warehouseString.indexOf(":") + 1);
		warehouseLoc = warehouseLoc.substring(0, 3);
		return AppLogic.Warehouses.get(AppLogic.Warehouses.indexOf(Integer.parseInt(warehouseNo), warehouseLoc));
	}

	public static String formatInventory(Inventory inventory) {
		return "[InvNo=" + inventory.getNumber() + "]";
	}

	public static Inventory parseInventory(String inventoryString) {
		inventoryString = inventoryString.substring(inventoryString.indexOf("InvNo"));
		inventoryString = inventoryString.substring(inventoryString.indexOf("=") + 1);
		inventoryString = inventoryString.substring(0, inventoryString.indexOf("]"));
		int inventoryNo = Integer.parseInt(inventoryString);
		return AppLogic.Inventories.get(AppLogic.Inventories.indexOf(inventoryNo));
	}

	public static String formatMoney(double money) {
		return new DecimalFormat("###,###,###,##0.00##").format(money);
	}

	public static double parseMoney(String moneyString) {
		try {
			return Double.parseDouble("" + new DecimalFormat("###,###,###,##0.00##").parse(moneyString));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			new Notification("Format error: Please enter numbers, commas and fullstops only.");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			new Notification("Parse error: Please enter numeric data only.");
		}
		return -1;
	}

	public static String formatProduct(Product product) {
		return product.getCode() + ": " + product.getName();
	}

	public static Product parseProduct(String productString) {
		String code = productString.substring(0, productString.indexOf(":"));
		String name = productString.substring(productString.indexOf(": ") + 2);
		ArrayList<Product> products = AppLogic.Products.getList();
		for (int i = 0; i < products.size(); i++)
			if (products.get(i).getCode() == Integer.parseInt(code) && products.get(i).getName().equals(name))
				return products.get(i);
		return null;
	}

	public static String formatOffering(Offering offering) {
		return offering.getOfferingName() + " [" + offering.getSupplierName() + "]";
	}

	public static Offering parseOffering(String offeringString) {
		String offering = offeringString.substring(0, offeringString.indexOf(" ["));
		String supplier = offeringString.substring(offeringString.indexOf("[") + 1);
		supplier = supplier.substring(0, supplier.indexOf("]"));
		ArrayList<Offering> offerings = AppLogic.Offerings.getList();
		for (int i = 0; i < offerings.size(); i++)
			if (offerings.get(i).getOfferingName().equals(offering)
					&& offerings.get(i).getSupplierName().equals(supplier))
				return offerings.get(i);
		return null;
	}

	public static String formatPriceTag(PriceTag priceTag) {
		return priceTag.getTag() + " [" + formatMoney(priceTag.getPrice()) + " AED]";
	}

	public static Item parseItem(String priceTagString, int inventoryNo) {
		String tag = priceTagString.substring(0, priceTagString.indexOf("[") - 1);
		String priceString = priceTagString.substring(priceTagString.indexOf("[") + 1);
		priceString = priceString.substring(0, priceString.indexOf("AED") - 1);
		Inventory inventory = AppLogic.Inventories.get(AppLogic.Inventories.indexOf(inventoryNo));
		Item item = null;
		if (inventory != null) {
			ProductTag productTag = null;
			ArrayList<ProductTag> productTags = AppLogic.ProductTags.getList();
			for (int i = 0; i < productTags.size(); i++)
				if (productTags.get(i).getTag().equals(tag))
					productTag = productTags.get(i);
			if (productTag == null)
				item = inventory.getItem(tag, Double.parseDouble(priceString));
			else
				item = inventory.getItem(tag, productTag.getPrice());
		}
		return item;
	}

	public static String formatReceiptVoucher(ReceiptVoucher receiptVoucher) {
		return new DecimalFormat("00000").format(receiptVoucher.getNumber());
	}

	public static ReceiptVoucher parseReceiptVoucher(String receiptVoucherString) {
		int number = -1;
		try {
			number = (int) new DecimalFormat("00000").parse(receiptVoucherString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return AppLogic.ReceiptVouchers.get(AppLogic.ReceiptVouchers.indexOf(number));
	}

	public static String formatPaymentVoucher(PaymentVoucher paymentVoucher) {
		return new DecimalFormat("00000").format(paymentVoucher.getNumber());
	}

	public static PaymentVoucher parsePaymentVoucher(String paymentVoucherString) {
		int number = -1;
		try {
			number = (int) new DecimalFormat("00000").parse(paymentVoucherString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return AppLogic.PaymentVouchers.get(AppLogic.PaymentVouchers.indexOf(number));
	}

	public static String formatDate(Date date) {
		if (date != null)
			return new SimpleDateFormat("dd MMM yyyy").format(date);
		else
			return null;
	}

	public static Date parseDate(String dateString) {
		try {
			return (Date) new SimpleDateFormat("dd MMM yyyy").parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
