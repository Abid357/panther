package Facade;

import java.text.DecimalFormat;
import java.util.ArrayList;

import AppLogic.PriceTags;
import Core.Bank;
import Core.Damage;
import Core.Domestic;
import Core.Driver;
import Core.Emp_Partner;
import Core.Import;
import Core.Inventory;
import Core.Investment;
import Core.Investor;
import Core.Invoice;
import Core.Item;
import Core.Loan;
import Core.Loaner;
import Core.Maintenance;
import Core.Manufacture;
import Core.Offering;
import Core.OfferingTag;
import Core.PriceTag;
import Core.Product;
import Core.ProductTag;
import Core.Production;
import Core.Profit;
import Core.Promotion;
import Core.Purchase;
import Core.RenewalFee;
import Core.Rent;
import Core.Salary;
import Core.Sale;
import Core.Supplier;
import Core.Transaction;
import Core.Transport;
import GUI.Main;
import GUI.MyTableModel;
import GUI._GUIGlobals;

public class TableViewer {

	private static String currentTable;

	public static void populate(String table) {
		MyTableModel tm = new MyTableModel();
		ArrayList<String> headers = new ArrayList<String>();
		if (table != null)
			switch (table) {
			case "SALE": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("AMOUNT");
				headers.add("# OF ITEMS");
				headers.add("ENTITY");
				headers.add("INVENTORY");
				headers.add("INVOICE");
				headers.add("RCPT VOUCHERS");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Sale> sales = AppLogic.Sales.getList();
				for (int i = 0; i < sales.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(sales.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(sales.get(i).getDate()));
					if (sales.get(i).getType() == 'C')
						data.add("+" + _GUIGlobals.formatMoney(sales.get(i).getAmount()));
					else
						data.add("-" + _GUIGlobals.formatMoney(sales.get(i).getAmount()));
					data.add(Integer.toString(sales.get(i).getItems().size()));
					data.add(sales.get(i).getEntityName());
					data.add(Integer.toString(sales.get(i).getInventoryNo()));
					if (sales.get(i).getInvoiceNo() != -1)
						data.add(Integer.toString(sales.get(i).getInvoiceNo()));
					else
						data.add(null);
					ArrayList<Integer> receiptVoucherNos = AppLogic.ReceiptVouchers
							.searchByInvoiceNo(sales.get(i).getInvoiceNo());
					if (!receiptVoucherNos.isEmpty()) {
						String receiptVouchers = _GUIGlobals.formatReceiptVoucher(AppLogic.ReceiptVouchers
								.get(AppLogic.ReceiptVouchers.indexOf(receiptVoucherNos.get(0))));
						for (int j = 1; j < receiptVoucherNos.size(); j++)
							receiptVouchers += ", " + _GUIGlobals.formatReceiptVoucher(AppLogic.ReceiptVouchers
									.get(AppLogic.ReceiptVouchers.indexOf(receiptVoucherNos.get(j))));
						data.add(receiptVouchers);
					} else
						data.add(null);
					tm.addRow(data.toArray());
				}
				break;
			}
			case "PURCHASE": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("AMOUNT");
				headers.add("# OF ITEMS");
				headers.add("ENTITY");
				headers.add("IMPORTED");
				headers.add("FROM");
				headers.add("TO");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Import> imports = AppLogic.Imports.getList();
				for (int i = 0; i < imports.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(imports.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(imports.get(i).getDate()));
					if (imports.get(i).getType() == 'C')
						data.add("+" + _GUIGlobals.formatMoney(imports.get(i).getAmount()));
					else
						data.add("-" + _GUIGlobals.formatMoney(imports.get(i).getAmount()));
					data.add(Integer.toString(imports.get(i).getItems().size()));
					data.add(imports.get(i).getEntityName());
					data.add("Y");
					data.add(imports.get(i).getFromLocation());
					data.add(imports.get(i).getToLocation());
					tm.addRow(data.toArray());
				}

				ArrayList<Purchase> purchases = AppLogic.Purchases.getList();
				for (int i = 0; i < purchases.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(purchases.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(purchases.get(i).getDate()));
					if (purchases.get(i).getType() == 'C')
						data.add("+" + _GUIGlobals.formatMoney(purchases.get(i).getAmount()));
					else
						data.add("-" + _GUIGlobals.formatMoney(purchases.get(i).getAmount()));
					data.add(Integer.toString(purchases.get(i).getItems().size()));
					data.add(purchases.get(i).getEntityName());
					data.add("N");
					data.add(null);
					data.add(null);
					tm.addRow(data.toArray());
				}
				break;
			}
			case "TRANSPORT": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("AMOUNT");
				headers.add("# OF ITEMS");
				headers.add("ENTITY");
				headers.add("INVENTORY");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Transport> transports = AppLogic.Transports.getList();
				for (int i = 0; i < transports.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(transports.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(transports.get(i).getDate()));
					if (transports.get(i).getType() == 'C')
						data.add("+" + _GUIGlobals.formatMoney(transports.get(i).getAmount()));
					else
						data.add("-" + _GUIGlobals.formatMoney(transports.get(i).getAmount()));
					data.add(Integer.toString(transports.get(i).getItems().size()));
					data.add(transports.get(i).getEntityName());
					data.add(Integer.toString(transports.get(i).getInventoryNo()));
					tm.addRow(data.toArray());
				}
				break;
			}
			case "DAMAGE": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("ITEM");
				headers.add("DAMAGED");
				headers.add("VALUE");
				headers.add("INVENTORY");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Damage> damages = AppLogic.Damages.getList();
				for (int i = 0; i < damages.size(); i++) {
					ArrayList<Item> items = damages.get(i).getItems();
					for (int j = 0; j < items.size(); j++) {
						ArrayList<String> data = new ArrayList<String>();
						data.add(Integer.toString(damages.get(i).getNumber()));
						data.add(_GUIGlobals.formatDate(damages.get(i).getDate()));
						PriceTag priceTag = AppLogic.PriceTags
								.get(AppLogic.PriceTags.indexOf(items.get(j).getPriceTagID()));
						data.add(priceTag.getTag());
						data.add(Integer.toString(items.get(j).getQuantity()));
						data.add(_GUIGlobals.formatMoney((priceTag.getPrice() * items.get(j).getQuantity())));
						data.add(Integer.toString(damages.get(i).getInventoryNo()));
						tm.addRow(data.toArray());
					}
				}
				break;
			}
			case "INVESTMENT": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("AMOUNT");
				headers.add("INVESTOR");
				headers.add("TOTAL INVESTED");
				headers.add("PROFIT RATE");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Investment> investments = AppLogic.Investments.getList();
				for (int i = 0; i < investments.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(investments.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(investments.get(i).getDate()));
					if (investments.get(i).getType() == 'C')
						data.add("+" + _GUIGlobals.formatMoney(investments.get(i).getAmount()));
					else
						data.add("-" + _GUIGlobals.formatMoney(investments.get(i).getAmount()));
					data.add(investments.get(i).getEntityName());
					Investor investor = AppLogic.Investors
							.get(AppLogic.Investors.indexOf(investments.get(i).getEntityName()));
					data.add(_GUIGlobals.formatMoney(investor.getInvested()));
					data.add(_GUIGlobals.formatMoney((investor.getProfitRate())));
					tm.addRow(data.toArray());
				}
				break;
			}
			case "PROFIT": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("AMOUNT");
				headers.add("INVESTOR");
				headers.add("TOTAL PROFITED");
				headers.add("PROFIT RATE");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Profit> profits = AppLogic.Profits.getList();
				for (int i = 0; i < profits.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(profits.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(profits.get(i).getDate()));
					if (profits.get(i).getType() == 'C')
						data.add("+" + _GUIGlobals.formatMoney(profits.get(i).getAmount()));
					else
						data.add("-" + _GUIGlobals.formatMoney(profits.get(i).getAmount()));
					data.add(profits.get(i).getEntityName());
					Investor investor = AppLogic.Investors
							.get(AppLogic.Investors.indexOf(profits.get(i).getEntityName()));
					data.add(_GUIGlobals.formatMoney(investor.getProfited()));
					data.add(_GUIGlobals.formatMoney((investor.getProfitRate())));
					tm.addRow(data.toArray());
				}
				break;
			}
			case "PROMOTION": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("ITEM");
				headers.add("PROMOTED");
				headers.add("VALUE");
				headers.add("INVENTORY");
				headers.add("SAMPLE");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Promotion> promotions = AppLogic.Promotions.getList();
				for (int i = 0; i < promotions.size(); i++) {
					ArrayList<Item> items = promotions.get(i).getItems();
					for (int j = 0; j < items.size(); j++) {
						ArrayList<String> data = new ArrayList<String>();
						data.add(Integer.toString(promotions.get(i).getNumber()));
						data.add(promotions.get(i).getDate().toString());
						PriceTag priceTag = AppLogic.PriceTags
								.get(AppLogic.PriceTags.indexOf(items.get(j).getPriceTagID()));
						data.add(priceTag.getTag());
						data.add(Integer.toString(items.get(j).getQuantity()));
						data.add(_GUIGlobals.formatMoney((priceTag.getPrice() * items.get(j).getQuantity())));
						data.add(Integer.toString(promotions.get(i).getInventoryNo()));
						if (promotions.get(i).isSample())
							data.add("Y");
						else
							data.add("N");
						tm.addRow(data.toArray());
					}
				}
				break;
			}
			case "SALARY": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("AMOUNT");
				headers.add("EMPLOYEE");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Salary> salaries = AppLogic.Salaries.getList();
				for (int i = 0; i < salaries.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(salaries.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(salaries.get(i).getDate()));
					if (salaries.get(i).getType() == 'C')
						data.add("+" + _GUIGlobals.formatMoney(salaries.get(i).getAmount()));
					else
						data.add("-" + _GUIGlobals.formatMoney(salaries.get(i).getAmount()));
					data.add(salaries.get(i).getEntityName());
					tm.addRow(data.toArray());
				}
				break;
			}
			case "RENT": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("AMOUNT");
				headers.add("RENT TYPE");
				headers.add("LICENSE");
				headers.add("DRIVER");
				headers.add("VEHICLE");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Rent> rents = AppLogic.Rents.getList();
				for (int i = 0; i < rents.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(rents.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(rents.get(i).getDate()));
					if (rents.get(i).getType() == 'C')
						data.add("+" + _GUIGlobals.formatMoney(rents.get(i).getAmount()));
					else
						data.add("-" + _GUIGlobals.formatMoney(rents.get(i).getAmount()));
					data.add(rents.get(i).getRentType());
					data.add(_GUIGlobals.formatLicense(AppLogic.Licenses.searchByRent(rents.get(i).getNumber())));
					data.add(rents.get(i).getEntityName());
					data.add(AppLogic.Drivers.get(AppLogic.Drivers.indexOf(rents.get(i).getEntityName())).getPlateNo());
					tm.addRow(data.toArray());
				}
				break;
			}
			case "RENEWAL": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("AMOUNT");
				headers.add("RENEWAL TYPE");
				headers.add("LICENSE");
				headers.add("EMP / PARTNER");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<RenewalFee> renewalFees = AppLogic.RenewalFees.getList();
				for (int i = 0; i < renewalFees.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(renewalFees.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(renewalFees.get(i).getDate()));
					if (renewalFees.get(i).getType() == 'C')
						data.add("+" + _GUIGlobals.formatMoney(renewalFees.get(i).getAmount()));
					else
						data.add("-" + _GUIGlobals.formatMoney(renewalFees.get(i).getAmount()));
					data.add(renewalFees.get(i).getRenewalType());
					data.add(_GUIGlobals
							.formatLicense(AppLogic.Licenses.searchByRenewalFee(renewalFees.get(i).getNumber())));
					data.add(renewalFees.get(i).getEntityName());
					tm.addRow(data.toArray());
				}
				break;
			}
			case "MAINTENANCE": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("AMOUNT");
				headers.add("VEHICLE");
				headers.add("DESCRIPTION");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Maintenance> maintenances = AppLogic.Maintenances.getList();
				for (int i = 0; i < maintenances.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(maintenances.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(maintenances.get(i).getDate()));
					if (maintenances.get(i).getType() == 'C')
						data.add("+" + _GUIGlobals.formatMoney(maintenances.get(i).getAmount()));
					else
						data.add("-" + _GUIGlobals.formatMoney(maintenances.get(i).getAmount()));
					data.add(maintenances.get(i).getVehiclePlateNo());
					data.add(maintenances.get(i).getDescription());
					tm.addRow(data.toArray());
				}
				break;
			}
			case "DOMESTIC": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("AMOUNT");
				headers.add("DESCRIPTION");
				headers.add("PERSON");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Domestic> domestics = AppLogic.Domestics.getList();
				for (int i = 0; i < domestics.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(domestics.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(domestics.get(i).getDate()));
					if (domestics.get(i).getType() == 'C')
						data.add("+" + _GUIGlobals.formatMoney(domestics.get(i).getAmount()));
					else
						data.add("-" + _GUIGlobals.formatMoney(domestics.get(i).getAmount()));
					data.add(domestics.get(i).getDescription());
					data.add(domestics.get(i).getEntityName());
					tm.addRow(data.toArray());
				}
				break;
			}
			case "MANUFACTURE": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("ITEM");
				headers.add("QUANTITY");
				headers.add("AMOUNT");
				headers.add("PERSON");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Manufacture> manufactures = AppLogic.Manufactures.getList();
				for (int i = 0; i < manufactures.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					ArrayList<Item> items = manufactures.get(i).getItems();
					OfferingTag offeringTag = AppLogic.OfferingTags
							.get(AppLogic.OfferingTags.indexOf(items.get(0).getPriceTagID()));
					if (items != null)
						if (!items.isEmpty())
							if (offeringTag != null) {
								data.add(Integer.toString(manufactures.get(i).getNumber()));
								data.add(_GUIGlobals.formatDate(manufactures.get(i).getDate()));
								data.add(offeringTag.getTag());
								data.add(Integer.toString(items.get(0).getQuantity()));
								if (manufactures.get(i).getType() == 'C')
									data.add("+" + _GUIGlobals.formatMoney(manufactures.get(i).getAmount()));
								else
									data.add("-" + _GUIGlobals.formatMoney(manufactures.get(i).getAmount()));
								data.add(manufactures.get(i).getEntityName());
								tm.addRow(data.toArray());
							}
				}
				break;
			}
			case "LOAN": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("AMOUNT");
				headers.add("LOANER");
				headers.add("BALANCE");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Loan> loans = AppLogic.Loans.getList();
				for (int i = 0; i < loans.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(loans.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(loans.get(i).getDate()));
					if (loans.get(i).getType() == 'C')
						data.add("+" + _GUIGlobals.formatMoney(loans.get(i).getAmount()));
					else
						data.add("-" + _GUIGlobals.formatMoney(loans.get(i).getAmount()));
					Bank bank = AppLogic.Banks.searchByLoanTransactionNo(loans.get(i).getNumber());
					if (bank != null) {
						data.add(_GUIGlobals.formatBank(bank));
						data.add(null);
					} else {
						data.add(loans.get(i).getEntityName());
						data.add(_GUIGlobals.formatMoney(AppLogic.Loaners
								.get(AppLogic.Loaners.indexOf(loans.get(i).getEntityName())).getBalance()));
					}
					tm.addRow(data.toArray());
				}
				break;
			}
			case "OLD LOANS": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("AMOUNT");
				headers.add("LOANER");
				headers.add("BALANCE");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Loan> loans = AppLogic.Loans.getOldLoans();
				for (int i = 0; i < loans.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(loans.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(loans.get(i).getDate()));
					if (loans.get(i).getType() == 'C')
						data.add("+" + _GUIGlobals.formatMoney(loans.get(i).getAmount()));
					else
						data.add("-" + _GUIGlobals.formatMoney(loans.get(i).getAmount()));
					Bank bank = AppLogic.Banks.searchByLoanTransactionNo(loans.get(i).getNumber());
					if (bank != null) {
						data.add(_GUIGlobals.formatBank(bank));
						data.add(null);
					} else {
						data.add(loans.get(i).getEntityName());
						data.add(_GUIGlobals.formatMoney(AppLogic.Loaners
								.get(AppLogic.Loaners.indexOf(loans.get(i).getEntityName())).getBalance()));
					}
					tm.addRow(data.toArray());
				}
				break;
			}
			case "OTHER TRANSACTION": {
				headers.add("TRANSACTION");
				headers.add("DATE");
				headers.add("AMOUNT");
				headers.add("ENTITY");
				headers.add("DESCRIPTION");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Transaction> transactions = AppLogic.Transactions.getPureTransactions();
				for (int i = 0; i < transactions.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(transactions.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(transactions.get(i).getDate()));
					if (transactions.get(i).getType() == 'C')
						data.add("+" + _GUIGlobals.formatMoney(transactions.get(i).getAmount()));
					else
						data.add("-" + _GUIGlobals.formatMoney(transactions.get(i).getAmount()));
					data.add(transactions.get(i).getEntityName());
					data.add(transactions.get(i).getDescription());
					tm.addRow(data.toArray());
				}
				break;
			}
			case "SUPPLIER": {
				headers.add("NAME");
				headers.add("CONTACT");
				headers.add("NUMBER");
				headers.add("LOCATION");
				headers.add("MATERIALS");
				headers.add("SERVICES");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Supplier> suppliers = AppLogic.Suppliers.getList();
				for (int i = 0; i < suppliers.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(suppliers.get(i).getName());
					data.add(suppliers.get(i).getContactPerson());
					data.add(suppliers.get(i).getContactNumber());
					data.add(suppliers.get(i).getLocation());
					int materials = 0;
					int services = 0;
					ArrayList<Offering> offerings = suppliers.get(i).getOfferings();
					for (int j = 0; j < offerings.size(); j++)
						if (offerings.get(j).getType() == 'M')
							materials++;
						else
							services++;
					data.add(Integer.toString(materials));
					data.add(Integer.toString(services));
					tm.addRow(data.toArray());
				}
				break;
			}
			case "EMPLOYEE/PARTNER": {
				headers.add("NAME");
				headers.add("LICENSE");
				headers.add("VISA EXPIRY");
				headers.add("CARD EXPIRY");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Emp_Partner> emp_partners = AppLogic.Emp_Partners.getList();
				for (int i = 0; i < emp_partners.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(emp_partners.get(i).getName());
					data.add(AppLogic.Licenses.get(AppLogic.Licenses.indexOf(emp_partners.get(i).getLicenseNumber()))
							.getName());
					data.add(_GUIGlobals.formatDate(emp_partners.get(i).getVisaExpiry()));
					data.add(_GUIGlobals.formatDate(emp_partners.get(i).getCardExpiry()));
					tm.addRow(data.toArray());
				}
				break;
			}
			case "DRIVER": {
				headers.add("NAME");
				headers.add("VEHICLE");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Driver> drivers = AppLogic.Drivers.getList();
				for (int i = 0; i < drivers.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(drivers.get(i).getName());
					data.add(drivers.get(i).getPlateNo());
					tm.addRow(data.toArray());
				}
				break;
			}
			case "LOANER": {
				headers.add("NAME");
				headers.add("BALANCE");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Loaner> loaners = AppLogic.Loaners.getList();
				for (int i = 0; i < loaners.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(loaners.get(i).getName());
					data.add(_GUIGlobals.formatMoney(loaners.get(i).getBalance()));
					tm.addRow(data.toArray());
				}
				break;
			}
			case "INVESTOR": {
				headers.add("NAME");
				headers.add("INVESTED");
				headers.add("PROFITED");
				headers.add("PROFIT RATE");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Investor> investors = AppLogic.Investors.getList();
				for (int i = 0; i < investors.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(investors.get(i).getName());
					data.add(_GUIGlobals.formatMoney(investors.get(i).getInvested()));
					data.add(_GUIGlobals.formatMoney(investors.get(i).getProfited()));
					data.add(_GUIGlobals.formatMoney(investors.get(i).getProfitRate()));
					tm.addRow(data.toArray());
				}
				break;
			}
			case "MATERIAL": {
				headers.add("NAME");
				headers.add("SUPPLIER");
				headers.add("PRICE");
				headers.add("TOTAL STOCK");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Offering> materials = AppLogic.Offerings.getAllMaterials();
				for (int i = 0; i < materials.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					ArrayList<OfferingTag> offeringTags = AppLogic.OfferingTags.searchByOfferingName(
							materials.get(i).getOfferingName(), materials.get(i).getSupplierName());
					if (!offeringTags.isEmpty()) {
						for (int j = 0; j < offeringTags.size(); j++) {
							data = new ArrayList<String>();
							data.add(offeringTags.get(j).getTag());
							data.add(materials.get(i).getSupplierName());
							data.add(_GUIGlobals.formatMoney(offeringTags.get(j).getPrice()));
							int stock = 0;
							ArrayList<Inventory> inventories = AppLogic.Inventories.getList();
							for (int k = 0; k < inventories.size(); k++) {
								ArrayList<Item> items = inventories.get(k).getItems();
								for (int l = 0; l < items.size(); l++)
									if (items.get(l).getPriceTagID() == offeringTags.get(j).getID())
										stock += items.get(l).getQuantity();
							}
							data.add(Integer.toString(stock));
							tm.addRow(data.toArray());
						}
					} else {
						data = new ArrayList<String>();
						data.add(materials.get(i).getOfferingName());
						data.add(materials.get(i).getSupplierName());
						data.add(null);
						data.add(Integer.toString(0));
						tm.addRow(data.toArray());
					}
				}
				break;
			}
			case "PRODUCT": {
				headers.add("CODE");
				headers.add("NAME");
				headers.add("SIZE");
				headers.add("PCS/CTN");
				headers.add("PRICE/PC");
				headers.add("TOTAL STOCK");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Product> products = AppLogic.Products.getList();
				for (int i = 0; i < products.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					ArrayList<ProductTag> productTags = AppLogic.ProductTags
							.searchByProductCode(products.get(i).getCode());
					if (!productTags.isEmpty()) {
						for (int j = 0; j < productTags.size(); j++) {
							data = new ArrayList<String>();
							data.add(Integer.toString(products.get(i).getCode()));
							data.add(PriceTags.get(j).getTag());
							data.add(new DecimalFormat("####0").format(products.get(i).getSize())
									+ products.get(i).getUnit());
							data.add(Integer.toString(products.get(i).getPiecesPerCarton()));
							data.add(_GUIGlobals.formatMoney(productTags.get(j).getPrice()));
							int stock = 0;
							ArrayList<Inventory> inventories = AppLogic.Inventories.getList();
							for (int k = 0; k < inventories.size(); k++) {
								ArrayList<Item> items = inventories.get(k).getItems();
								for (int l = 0; l < items.size(); l++)
									if (items.get(l).getPriceTagID() == productTags.get(j).getID())
										stock += items.get(l).getQuantity();
							}
							data.add(Integer.toString(stock));
							tm.addRow(data.toArray());
						}
					} else {
						data = new ArrayList<String>();
						data.add(Integer.toString(products.get(i).getCode()));
						data.add(products.get(i).getName());
						data.add(new DecimalFormat("####0").format(products.get(i).getSize())
								+ products.get(i).getUnit());
						data.add(Integer.toString(products.get(i).getPiecesPerCarton()));
						data.add(null);
						data.add(Integer.toString(0));
						tm.addRow(data.toArray());
					}
				}
				break;
			}
			case "SERVICE": {
				headers.add("NAME");
				headers.add("SUPPLIER");
				headers.add("PRICE");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Offering> services = AppLogic.Offerings.getAllServices();
				for (int i = 0; i < services.size(); i++) {
					ArrayList<OfferingTag> offeringTags = AppLogic.OfferingTags
							.searchByOfferingName(services.get(i).getOfferingName(), services.get(i).getSupplierName());
					if (!offeringTags.isEmpty())
						for (int j = 0; j < offeringTags.size(); j++) {
							ArrayList<String> data = new ArrayList<String>();
							data.add(offeringTags.get(j).getTag());
							data.add(services.get(i).getSupplierName());
							data.add(_GUIGlobals.formatMoney(offeringTags.get(j).getPrice()));
							tm.addRow(data.toArray());
						}
				}
				break;
			}
			case "PRODUCTION": {
				headers.add("PROD. ID");
				headers.add("START DATE");
				headers.add("END DATE");
				headers.add("PRODUCT");
				headers.add("PRICE/CTN");
				headers.add("CTNs");
				headers.add("COST");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Production> productions = AppLogic.Productions.getList();
				for (int i = 0; i < productions.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(productions.get(i).getID()));
					data.add(_GUIGlobals.formatDate(productions.get(i).getStartDate()));
					data.add(_GUIGlobals.formatDate(productions.get(i).getEndDate()));
					ProductTag productTag = AppLogic.ProductTags
							.get(AppLogic.ProductTags.indexOf(productions.get(i).getProductTag()));
					data.add(productTag.getTag());
					double pricePerCarton = productTag.getPrice() * AppLogic.Products
							.get(AppLogic.Products.indexOf(productTag.getProductCode())).getPiecesPerCarton();
					data.add(_GUIGlobals.formatMoney(pricePerCarton));
					data.add(Integer.toString(productions.get(i).getCartons()));
					double cost = pricePerCarton * productions.get(i).getCartons();
					data.add(_GUIGlobals.formatMoney(cost));
					tm.addRow(data.toArray());
				}
				break;
			}
			case "INVOICE": {
				headers.add("NUMBER");
				headers.add("DATE");
				headers.add("NAME");
				headers.add("TERMS");
				headers.add("TOTAL");
				tm.setColumnIdentifiers(headers.toArray());

				ArrayList<Invoice> invoices = AppLogic.Invoices.getList();
				for (int i = 0; i < invoices.size(); i++) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(Integer.toString(invoices.get(i).getNumber()));
					data.add(_GUIGlobals.formatDate(invoices.get(i).getDate()));
					data.add(invoices.get(i).getName());
					data.add(invoices.get(i).getPaymentTerms());
					double total = 0;
					ArrayList<Item> items = invoices.get(i).getItems();
					ArrayList<Double> rates = invoices.get(i).getRates();
					for (int j = 0; j < items.size(); j++) {
						if (rates.get(j) != -1)
							total += items.get(j).getQuantity() * rates.get(j);
					}
					data.add(_GUIGlobals.formatMoney(total));
					tm.addRow(data.toArray());
				}
				break;
			}
			}
		Main.getTableT().setModel(tm);
		currentTable = table;
	}

	public static void update() {
		populate(currentTable);
	}
}
