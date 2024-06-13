package Facade;

import java.util.ArrayList;

import Core.Damage;
import Core.Domestic;
import Core.Import;
import Core.Inventory;
import Core.Investment;
import Core.Investor;
import Core.Item;
import Core.License;
import Core.Loan;
import Core.Loaner;
import Core.Maintenance;
import Core.Pocket;
import Core.Profit;
import Core.Promotion;
import Core.Purchase;
import Core.RenewalFee;
import Core.Rent;
import Core.Salary;
import Core.Sale;
import Core.Transport;
import GUI.Log;
import GUI.Logger;
import GUI._GUIGlobals;

public class Transactions {

	public static boolean addSale(Sale sale) {
		if (AppLogic.Sales.add(sale)) {
			Logger.add(new Log("Sale", "TransNo", sale.getNumber(), "InventoryNo", sale.getInventoryNo(), Log.ADD));
			ArrayList<Item> saleItems = sale.getItems();
			if (saleItems != null)
				for (Item item : saleItems)
					Inventories.removeStock(
							AppLogic.Inventories.get(AppLogic.Inventories.indexOf(sale.getInventoryNo())), item);
			Pocket pocket = AppLogic.Pockets.get(AppLogic.Pockets.indexOf(_FacadeGlobals.getPocket("Sale")));
			AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() + sale.getAmount());
			QuickViewer.update();
			TableViewer.update();
			AppLogic.Transactions.setDirty(true);
			AppLogic.Sales.setDirty(true);
			AppLogic.Pockets.setDirty(true);
			return true;
		} else
			return false;
	}

	public static boolean addImport(Import imp) {
		if (AppLogic.Imports.add(imp)) {
			Logger.add(new Log("Import", "TransNo", imp.getNumber(), "From", imp.getFromLocation(), Log.ADD));
			ArrayList<Item> importItems = imp.getItems();
			if (importItems != null)
				for (Item item : importItems)
					Inventories
							.addStock(
									AppLogic.Inventories.get(AppLogic.Inventories.indexOf(AppLogic.Suppliers
											.get(AppLogic.Suppliers.indexOf(imp.getEntityName())).getInventoryNo())),
									item);
			Pocket pocket = AppLogic.Pockets.get(AppLogic.Pockets.indexOf(_FacadeGlobals.getPocket("Import")));
			AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() - imp.getAmount());
			QuickViewer.update();
			TableViewer.update();
			AppLogic.Transactions.setDirty(true);
			AppLogic.Imports.setDirty(true);
			AppLogic.PriceTags.setDirty(true);
			AppLogic.ProductTags.setDirty(true);
			AppLogic.Pockets.setDirty(true);
			return true;
		} else
			return false;
	}

	public static boolean addPurchase(Purchase purchase) {
		if (AppLogic.Purchases.add(purchase)) {
			Logger.add(
					new Log("Purchase", "TransNo", purchase.getNumber(), "Entity", purchase.getEntityName(), Log.ADD));
			ArrayList<Item> purchaseItems = purchase.getItems();
			if (purchaseItems != null)
				for (Item item : purchaseItems)
					Inventories
							.addStock(
									AppLogic.Inventories.get(AppLogic.Inventories.indexOf(AppLogic.Suppliers
											.get(AppLogic.Suppliers.indexOf(purchase.getEntityName())).getInventoryNo())),
									item);
			Pocket pocket = AppLogic.Pockets.get(AppLogic.Pockets.indexOf(_FacadeGlobals.getPocket("Local Purchase")));
			AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() - purchase.getAmount());
			QuickViewer.update();
			TableViewer.update();
			AppLogic.Transactions.setDirty(true);
			AppLogic.Purchases.setDirty(true);
			AppLogic.PriceTags.setDirty(true);
			AppLogic.ProductTags.setDirty(true);
			AppLogic.Pockets.setDirty(true);
			return true;
		} else
			return false;
	}

	public static boolean addTransport(Transport transport) {
		if (AppLogic.Transports.add(transport)) {
			Logger.add(new Log("Transport", "TransNo", transport.getNumber(), "InvNo", transport.getInventoryNo(),
					Log.ADD));
			Inventory inventory = AppLogic.Inventories.get(AppLogic.Inventories.indexOf(transport.getInventoryNo()));
			if (inventory != null) {
				ArrayList<Item> transportItems = transport.getItems();
				for (Item ti : transportItems) {
					for (Item i : inventory.getItems()) {
						if (ti.getPriceTagID() == i.getPriceTagID()) {
							i.setQuantity(i.getQuantity() + ti.getQuantity());
							break;
						}
					}
				}
			}
			AppLogic.Inventories.update(inventory.getNumber(), inventory.getItems());
			Pocket pocket = AppLogic.Pockets.get(AppLogic.Pockets.indexOf(_FacadeGlobals.getPocket("Transport")));
			AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() - transport.getAmount());
			QuickViewer.update();
			TableViewer.update();
			AppLogic.Transactions.setDirty(true);
			AppLogic.Transports.setDirty(true);
			AppLogic.Pockets.setDirty(true);
			return true;
		} else
			return false;
	}

	public static boolean addInvestment(Investment investment) {
		if (AppLogic.Investments.add(investment)) {
			Logger.add(new Log("Investment", "TransNo", investment.getNumber(), "Entity", investment.getEntityName(),
					Log.ADD));
			Investor investor = AppLogic.Investors.get(AppLogic.Investors.indexOf(investment.getEntityName()));
			double invested = investor.getInvested() + investment.getAmount();
			AppLogic.Investors.update(investor.getName(), null, null, null, invested, -1, -1);
			Pocket pocket = AppLogic.Pockets.get(AppLogic.Pockets.indexOf(_FacadeGlobals.getPocket("Investment")));
			AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() + investment.getAmount());
			QuickViewer.update();
			TableViewer.update();
			AppLogic.Transactions.setDirty(true);
			AppLogic.Investments.setDirty(true);
			AppLogic.Investors.setDirty(true);
			AppLogic.Pockets.setDirty(true);
			return true;
		} else
			return false;
	}

	public static boolean addProfit(Profit profit) {
		if (AppLogic.Profits.add(profit)) {
			Logger.add(new Log("Profit", "TransNo", profit.getNumber(), "Entity", profit.getEntityName(), Log.ADD));
			Investor investor = AppLogic.Investors.get(AppLogic.Investors.indexOf(profit.getEntityName()));
			double profited = investor.getProfited() + profit.getAmount();
			AppLogic.Investors.update(investor.getName(), null, null, null, -1, profited, -1);
			Pocket pocket = AppLogic.Pockets.get(AppLogic.Pockets.indexOf(_FacadeGlobals.getPocket("Profit")));
			AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() - profit.getAmount());
			QuickViewer.update();
			TableViewer.update();
			AppLogic.Transactions.setDirty(true);
			AppLogic.Profits.setDirty(true);
			AppLogic.Investors.setDirty(true);
			AppLogic.Pockets.setDirty(true);
			return true;
		} else
			return false;
	}

	public static boolean addSalary(Salary salary) {
		if (AppLogic.Salaries.add(salary)) {
			Logger.add(new Log("Salary", "TransNo", salary.getNumber(), "Entity", salary.getEntityName(), Log.ADD));
			Pocket pocket = AppLogic.Pockets.get(AppLogic.Pockets.indexOf(_FacadeGlobals.getPocket("Salary")));
			AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() - salary.getAmount());
			QuickViewer.update();
			TableViewer.update();
			AppLogic.Transactions.setDirty(true);
			AppLogic.Salaries.setDirty(true);
			AppLogic.Pockets.setDirty(true);
			return true;
		} else
			return false;
	}

	public static boolean addDomestic(Domestic domestic) {
		if (AppLogic.Domestics.add(domestic)) {
			Logger.add(
					new Log("Domestic", "TransNo", domestic.getNumber(), "Entity", domestic.getEntityName(), Log.ADD));
			Pocket pocket = AppLogic.Pockets.get(AppLogic.Pockets.indexOf(_FacadeGlobals.getPocket("Domestic")));
			AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() - domestic.getAmount());
			QuickViewer.update();
			TableViewer.update();
			AppLogic.Transactions.setDirty(true);
			AppLogic.Domestics.setDirty(true);
			AppLogic.Pockets.setDirty(true);
			return true;
		} else
			return false;
	}

	public static boolean addLoan(Loan loan) {
		if (AppLogic.Loans.add(loan)) {
			Logger.add(new Log("Loan", "TransNo", loan.getNumber(), "Entity", loan.getEntityName(), Log.ADD));
			Pocket pocket = AppLogic.Pockets.get(AppLogic.Pockets.indexOf(_FacadeGlobals.getPocket("Loan")));
			if (loan.getType() == 'C' && loan.isActive()) {
				AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() + loan.getAmount());
				if (loan.getEntityName() != null) {
					Loaner loaner = AppLogic.Loaners.get(AppLogic.Loaners.indexOf(loan.getEntityName()));
					if (loaner == null)
						loaner = AppLogic.Loaners
								.convert(AppLogic.Entities.get(AppLogic.Entities.indexOf(loan.getEntityName())), 0);
					loaner.setBalance(loaner.getBalance() - loan.getAmount());
					AppLogic.Loaners.update(loaner.getName(), null, null, null, loaner.getBalance());
				}
			} else {
				AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() - loan.getAmount());
				if (loan.getEntityName() != null) {
					Loaner loaner = AppLogic.Loaners.get(AppLogic.Loaners.indexOf(loan.getEntityName()));
					if (loaner == null)
						loaner = AppLogic.Loaners
								.convert(AppLogic.Entities.get(AppLogic.Entities.indexOf(loan.getEntityName())), 0);
					loaner.setBalance(loaner.getBalance() + loan.getAmount());
					AppLogic.Loaners.update(loaner.getName(), null, null, null, loaner.getBalance());
				}
			}
			QuickViewer.update();
			TableViewer.update();
			AppLogic.Transactions.setDirty(true);
			AppLogic.Loans.setDirty(true);
			AppLogic.Entities.setDirty(true);
			AppLogic.Loaners.setDirty(true);
			AppLogic.Pockets.setDirty(true);
			return true;
		} else
			return false;
	}

	public static boolean addRent(Rent rent, String licenseNo) {
		if (AppLogic.Rents.add(rent)) {
			Logger.add(new Log("Rent", "TransNo", rent.getNumber(), "RentType", rent.getRentType(), Log.ADD));
			Pocket pocket = AppLogic.Pockets.get(AppLogic.Pockets.indexOf(_FacadeGlobals.getPocket("Rent")));
			if (rent.getType() == 'C')
				AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() + rent.getAmount());
			else {
				AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() - rent.getAmount());
				License license = _GUIGlobals.parseLicense(licenseNo);
				ArrayList<Integer> rentTransactionNos = license.getRentTransactionNos();
				if (rentTransactionNos == null)
					rentTransactionNos = new ArrayList<Integer>();
				rentTransactionNos.add(rent.getNumber());
				AppLogic.Licenses.update(license.getNumber(), null, null, null, null, rentTransactionNos, null);
			}
			QuickViewer.update();
			TableViewer.update();
			AppLogic.Transactions.setDirty(true);
			AppLogic.Rents.setDirty(true);
			AppLogic.Licenses.setDirty(true);
			AppLogic.Pockets.setDirty(true);
			return true;
		} else
			return false;
	}

	public static boolean addRenewalFee(RenewalFee renewalFee, String licenseNo) {
		if (AppLogic.RenewalFees.add(renewalFee)) {
			Logger.add(new Log("RenewalFee", "TransNo", renewalFee.getNumber(), "RenewalType",
					renewalFee.getRenewalType(), Log.ADD));
			Pocket pocket = AppLogic.Pockets.get(AppLogic.Pockets.indexOf(_FacadeGlobals.getPocket("Renewal Fee")));
			if (renewalFee.getType() == 'C')
				AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() + renewalFee.getAmount());
			else {
				AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() - renewalFee.getAmount());
				License license = AppLogic.Licenses.get(AppLogic.Licenses.indexOf(licenseNo));
				ArrayList<Integer> renewalFeeTransactionNos = license.getRenewalFeeTransactionNos();
				if (renewalFeeTransactionNos == null)
					renewalFeeTransactionNos = new ArrayList<Integer>();
				renewalFeeTransactionNos.add(renewalFee.getNumber());
				AppLogic.Licenses.update(license.getNumber(), null, null, null, null, null, renewalFeeTransactionNos);
			}
			QuickViewer.update();
			TableViewer.update();
			AppLogic.Transactions.setDirty(true);
			AppLogic.RenewalFees.setDirty(true);
			AppLogic.Licenses.setDirty(true);
			AppLogic.Pockets.setDirty(true);
			return true;
		} else
			return false;
	}

	public static boolean addDamage(Damage damage) {
		if (AppLogic.Damages.add(damage)) {
			Logger.add(
					new Log("Damage", "TransNo", damage.getNumber(), "InventoryNo", damage.getInventoryNo(), Log.ADD));
			ArrayList<Item> damageItems = damage.getItems();
			Inventory inventory = AppLogic.Inventories.get(AppLogic.Inventories.indexOf(damage.getInventoryNo()));
			if (inventory != null)
				if (damageItems != null)
					for (int i = 0; i < damageItems.size(); i++) {
						for (Item item : inventory.getItems()) {
							if (damageItems.get(i).getPriceTagID() == item.getPriceTagID()) {
								item.setQuantity(item.getQuantity() - damageItems.get(i).getQuantity());
								break;
							}
						}
					}
			AppLogic.Inventories.update(inventory.getNumber(), inventory.getItems());
			QuickViewer.update();
			TableViewer.update();
			AppLogic.Transactions.setDirty(true);
			AppLogic.Damages.setDirty(true);
			AppLogic.Pockets.setDirty(true);
			return true;
		} else
			return false;
	}

	public static boolean addPromotion(Promotion promotion) {
		if (AppLogic.Promotions.add(promotion)) {
			Logger.add(new Log("Promotion", "TransNo", promotion.getNumber(), "InventoryNo", promotion.getInventoryNo(),
					Log.ADD));
			ArrayList<Item> promotionItems = promotion.getItems();
			Inventory inventory = AppLogic.Inventories.get(AppLogic.Inventories.indexOf(promotion.getInventoryNo()));
			if (inventory != null)
				if (promotionItems != null)
					for (Item di : promotionItems) {
						for (Item i : inventory.getItems()) {
							if (di.getPriceTagID() == i.getPriceTagID()) {
								i.setQuantity(i.getQuantity() - di.getQuantity());
								break;
							}
						}
					}
			AppLogic.Inventories.update(inventory.getNumber(), inventory.getItems());
			QuickViewer.update();
			TableViewer.update();
			AppLogic.Transactions.setDirty(true);
			AppLogic.Promotions.setDirty(true);
			AppLogic.Pockets.setDirty(true);
			return true;
		} else
			return false;
	}

	public static boolean addMaintenance(Maintenance maintenance) {
		if (AppLogic.Maintenances.add(maintenance)) {
			Logger.add(new Log("Maintenance", "TransNo", maintenance.getNumber(), "Vehicle",
					maintenance.getVehiclePlateNo(), Log.ADD));
			Pocket pocket = AppLogic.Pockets.get(AppLogic.Pockets.indexOf(_FacadeGlobals.getPocket("Maintenance")));
			AppLogic.Pockets.update(pocket.getName(), pocket.getBalance() - maintenance.getAmount());
			QuickViewer.update();
			TableViewer.update();
			AppLogic.Transactions.setDirty(true);
			AppLogic.Maintenances.setDirty(true);
			AppLogic.Pockets.setDirty(true);
			return true;
		} else
			return false;
	}

}
