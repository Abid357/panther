package Facade;

import java.io.Serializable;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import Core.Inventory;
import Core.Item;
import GUI.Main;

public class QuickViewer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static HashMap<String, Boolean> quickViewerOptions;
	private static int month;

	public QuickViewer() {
		month = 0;
		quickViewerOptions = new HashMap<String, Boolean>();
		quickViewerOptions.put("Pocket Cash", true);
		quickViewerOptions.put("Total Sales", true);
		quickViewerOptions.put("Total Purchases", true);
		quickViewerOptions.put("Imported Stocks", true);
		quickViewerOptions.put("Local Stocks", true);
		quickViewerOptions.put("Manufactured Stocks", false);
		quickViewerOptions.put("Bank Balance", false);
		quickViewerOptions.put("Total Staff", false);
		quickViewerOptions.put("Total Loan", false);
		update();
	}

	public QuickViewer(int month, HashMap<String, Boolean> quickViewerOptions) {
		QuickViewer.month = month;
		QuickViewer.quickViewerOptions = quickViewerOptions;
	}

	public static String toString(Object obj) {
		if (obj == null)
			return null;
		if (obj.getClass().equals(Integer.class))
			return (new DecimalFormat("###,###,###,##0").format((int) obj));
		else if (obj.getClass().equals(Double.class))
			return (new DecimalFormat("###,###,###,##0.00").format((double) obj));
		else
			return null;
	}

	public static boolean save(int month, HashMap<String, Boolean> quickViewerOptions) {
		if (month != -1)
			QuickViewer.month = month;
		if (quickViewerOptions != null)
			QuickViewer.quickViewerOptions = quickViewerOptions;
		return update();
	}

	public static void set(int index, String option) {
		String optionValue = null;
		double totalAmount = 0;
		int totalQuantity = 0;
		Calendar cal = Calendar.getInstance();
		if (month > cal.get(Calendar.MONTH)) {
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
			cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) - month) + 12);
		} else
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - month);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date timePeriod = new Date(cal.getTimeInMillis());

		switch (option) {
		case "Pocket Cash": {
			optionValue = toString(AppLogic.Pockets.get(AppLogic.Pockets.indexOf("Company")).getBalance());
			break;
		}
		case "Total Sales": {
			ArrayList<Core.Sale> sales = AppLogic.Sales.getList();
			if (sales != null)
				for (int i = 0; i < sales.size(); i++)
					if (sales.get(i).getDate() != null)
						if (sales.get(i).getDate().after(timePeriod) || sales.get(i).getDate().equals(timePeriod))
							totalAmount += sales.get(i).getAmount();
			optionValue = toString(totalAmount);
			break;
		}
		case "Total Purchases": {
			ArrayList<Core.Import> imports = AppLogic.Imports.getList();
			if (imports != null)
				for (int i = 0; i < imports.size(); i++) {
					if (imports.get(i).getDate() != null)
						if (imports.get(i).getDate().after(timePeriod) || imports.get(i).getDate().equals(timePeriod))
							totalAmount += imports.get(i).getAmount();
					Core.Transport transport = AppLogic.Transports
							.get(AppLogic.Transports.indexOf(imports.get(i).getTransportTransactionNo()));
					if (transport != null)
						if (transport.getDate() != null)
							if (transport.getDate().after(timePeriod) || transport.getDate().equals(timePeriod))
								totalAmount += transport.getAmount();
				}
			ArrayList<Core.Manufacture> manufactures = AppLogic.Manufactures.getList();
			if (manufactures != null)
				for (int j = 0; j < manufactures.size(); j++) {
					if (manufactures.get(j).getDate() != null)
						if (manufactures.get(j).getDate().after(timePeriod)
								|| manufactures.get(j).getDate().equals(timePeriod))
							totalAmount += manufactures.get(j).getAmount();
				}
			ArrayList<Core.Production> productions = AppLogic.Productions.getList();
			if (productions != null)
				for (int j = 0; j < productions.size(); j++) {
					Core.Transport transport = AppLogic.Transports.get(productions.get(j).getTransportTransactionNo());
					if (transport != null)

						if (transport.getDate() != null)
							if (transport.getDate().after(timePeriod) || transport.getDate().equals(timePeriod))
								totalAmount += transport.getAmount();
				}
			optionValue = toString(totalAmount);
			break;
		}
		case "Imported Stocks": {
			ArrayList<Core.Item> warehouseItems = new ArrayList<Core.Item>();
			ArrayList<Core.Warehouse> warehouses = AppLogic.Warehouses.getList();
			if (warehouses != null)
				for (int i = 0; i < warehouses.size(); i++) {
					Inventory inventory = AppLogic.Inventories
							.get(AppLogic.Inventories.indexOf(warehouses.get(i).getInventoryNo()));
					if (inventory != null) {
						ArrayList<Core.Item> inventoryItems = inventory.getItems();
						if (inventoryItems != null)
							for (int j = 0; j < inventoryItems.size(); j++)
								if (AppLogic.ProductTags.indexOf(inventoryItems.get(j).getPriceTagID()) != -1)
									warehouseItems.add(inventoryItems.get(j));
					}
				}
			ArrayList<Core.Transport> transports = new ArrayList<Core.Transport>();
			ArrayList<Core.Import> imports = AppLogic.Imports.getList();
			if (imports != null) {
				for (int i = 0; i < imports.size(); i++) {
					Core.Transport transport = AppLogic.Transports
							.get(AppLogic.Transports.indexOf(imports.get(i).getTransportTransactionNo()));
					if (transport != null && transport.getToLocation().contains("W:"))
						transports.add(transport);
				}
			}
			for (int i = 0; i < transports.size(); i++) {
				ArrayList<Item> items = transports.get(i).getItems();
				for (int j = 0; j < items.size(); j++) {
					for (int k = 0; k < warehouseItems.size(); k++) {
						if (items.get(j).getPriceTagID() == warehouseItems.get(k).getPriceTagID())
							if (warehouseItems.get(k).getQuantity() != -1)
								totalQuantity += warehouseItems.get(k).getQuantity();
					}
				}
			}
			optionValue = toString(totalQuantity);
			break;
		}
		case "Local Stocks": {
			ArrayList<Core.Item> warehouseItems = new ArrayList<Core.Item>();
			ArrayList<Core.Warehouse> warehouses = AppLogic.Warehouses.getList();
			if (warehouses != null)
				for (int i = 0; i < warehouses.size(); i++) {
					Inventory inventory = AppLogic.Inventories
							.get(AppLogic.Inventories.indexOf(warehouses.get(i).getInventoryNo()));
					if (inventory != null) {
						ArrayList<Core.Item> inventoryItems = inventory.getItems();
						if (inventoryItems != null)
							for (int j = 0; j < inventoryItems.size(); j++)
								if (AppLogic.ProductTags.indexOf(inventoryItems.get(j).getPriceTagID()) != -1)
									warehouseItems.add(inventoryItems.get(j));
					}
				}
			ArrayList<Core.Transaction> localPurchases = new ArrayList<Core.Transaction>();
			ArrayList<Core.Transaction> allTransactions = AppLogic.Transactions.getList();
			if (allTransactions != null) {
				ArrayList<Core.Transaction> pureTransactions = new ArrayList<Core.Transaction>();
				for (int i = 0; i < allTransactions.size(); i++)
					if (allTransactions.get(i).getClass().equals(Core.Transaction.class))
						pureTransactions.add(allTransactions.get(i));
				ArrayList<Core.Transport> transports = AppLogic.Transports.getList();
				if (transports != null)
					for (int i = 0; i < pureTransactions.size(); i++)
						for (int j = 0; j < transports.size(); j++)
							if (pureTransactions.get(i).getItems() != null && transports.get(j).getItems() != null)
							if (pureTransactions.get(i).getItems().equals(transports.get(j).getItems())) {
								localPurchases.add(pureTransactions.get(i));
								break;
							}
			}
			for (int i = 0; i < localPurchases.size(); i++) {
				ArrayList<Item> items = localPurchases.get(i).getItems();
				for (int j = 0; j < items.size(); j++) {
					for (int k = 0; k < warehouseItems.size(); k++) {
						if (items.get(j).getPriceTagID() == warehouseItems.get(k).getPriceTagID())
							if (warehouseItems.get(k).getQuantity() != -1)
								totalQuantity += warehouseItems.get(k).getQuantity();
					}
				}
			}
			optionValue = toString(totalQuantity);
			break;
		}
		case "Manufactured Stocks": {
			ArrayList<Core.Item> warehouseItems = new ArrayList<Core.Item>();
			ArrayList<Core.Warehouse> warehouses = AppLogic.Warehouses.getList();
			if (warehouses != null)
				for (int i = 0; i < warehouses.size(); i++) {
					Inventory inventory = AppLogic.Inventories
							.get(AppLogic.Inventories.indexOf(warehouses.get(i).getInventoryNo()));
					if (inventory != null) {
						ArrayList<Core.Item> inventoryItems = inventory.getItems();
						if (inventoryItems != null)
							for (int j = 0; j < inventoryItems.size(); j++)
								if (AppLogic.ProductTags.indexOf(inventoryItems.get(j).getPriceTagID()) != -1)
									warehouseItems.add(inventoryItems.get(j));
					}
				}
			ArrayList<Core.ProductTag> productTags = new ArrayList<Core.ProductTag>();
			ArrayList<Core.Production> productions = AppLogic.Productions.getList();
			if (productions != null)
				for (int i = 0; i < productions.size(); i++)
					productTags.add(
							AppLogic.ProductTags.get(AppLogic.ProductTags.indexOf(productions.get(i).getProductTag())));
			for (int j = 0; j < productTags.size(); j++) {
				for (int k = 0; k < warehouseItems.size(); k++)
					if (productTags.get(j).getID() == warehouseItems.get(k).getPriceTagID())
						if (warehouseItems.get(k).getQuantity() != -1)
							totalQuantity += warehouseItems.get(k).getQuantity();
			}
			optionValue = toString(totalQuantity);
			break;
		}
		case "Bank Balance": {
			ArrayList<Core.Bank> banks = AppLogic.Banks.getList();
			if (banks != null)
				for (int i = 0; i < banks.size(); i++)
					if (banks.get(i).getBalance() != -1)
						totalAmount += banks.get(i).getBalance();
			optionValue = toString(totalAmount);
			break;
		}
		case "Total Staff": {
			optionValue = toString(AppLogic.Emp_Partners.getList().size());
			break;
		}
		case "Total Loan": {
			double loaned = 0;
			ArrayList<Core.Loan> allLoans = AppLogic.Loans.getList();
			for (int i = 0; i < allLoans.size(); i++) {
				if (allLoans.get(i).getType() == 'C' && allLoans.get(i).isActive())
					loaned += allLoans.get(i).getAmount();
				for (int j = 0; j < allLoans.size(); j++)
					if (allLoans.get(j).getType() == 'D'
							&& allLoans.get(j).getLoanTransactionNo() == allLoans.get(i).getNumber())
						loaned -= allLoans.get(j).getAmount();
			}
			optionValue = toString(loaned);
			break;
		}
		}
		if (optionValue == null)
			optionValue = "ERROR";
		Main.setQV(index, option.toUpperCase(), optionValue);
	}

	public static boolean update() {
		int index = 0;
		Iterator<Entry<String, Boolean>> iterator = quickViewerOptions.entrySet().iterator();
		while (iterator.hasNext() && index < 5) {
			Entry<String, Boolean> entrySet = (Entry<String, Boolean>) iterator.next();
			if (entrySet.getValue()) {
				set(index, entrySet.getKey());
				index++;
			}
		}
		return true;
	}
}
