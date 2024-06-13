package Facade;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Core.Bank;
import Core.Cheque;
import Core.Customer;
import Core.Driver;
import Core.Emp_Partner;
import Core.Entity;
import Core.Import;
import Core.Inventory;
import Core.Investor;
import Core.Invoice;
import Core.Item;
import Core.License;
import Core.Loan;
import Core.Loaner;
import Core.Offering;
import Core.PriceTag;
import Core.Product;
import Core.ProductTag;
import Core.ProformaInvoice;
import Core.RenewalFee;
import Core.Rent;
import Core.Supplier;
import Core.Transport;
import Core.Vehicle;
import Core.Warehouse;
import Frame.AddPanel;
import Frame.AddPanel2;
import Frame.Domestic;
import Frame.EditPanel;
import Frame.Investment;
import Frame.ItemDetails;
import Frame.Maintenance;
import Frame.Notification;
import Frame.PriceDetails;
import Frame.Profit;
import Frame.Salary;
import Frame.SaleDetails;
import Frame.Transaction;
import Frame.UpdatePanel;
import GUI.Log;
import GUI.Logger;
import GUI.Main;
import GUI.RectangularButton;
import GUI._GUIGlobals;

public class Functions {

	private static Object tempObj;
	private static Object tempObj2;

	public static Object getTempObj() {
		return tempObj;
	}

	public static Object getTempObj2() {
		return tempObj2;
	}

	public static void setTempObj(Object tempObj) {
		Functions.tempObj = tempObj;
	}

	public static void setTempObj2(Object tempObj2) {
		Functions.tempObj2 = tempObj2;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadBankAccountCB(JComboBox<String> CB, String entityName, String bankAccountNo) {
		CB.removeAllItems();
		ArrayList<Bank> banks = AppLogic.Banks.searchByEntityName(entityName);
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i < banks.size(); i++) {
			strings.add(GUI._GUIGlobals.formatBank(banks.get(i)));
		}
		CB.setModel(new DefaultComboBoxModel(strings.toArray()));
		if (bankAccountNo != null)
			if (!bankAccountNo.equals("")) {
				Bank bank = AppLogic.Banks.get(AppLogic.Banks.indexOf(bankAccountNo));
				CB.setSelectedItem(GUI._GUIGlobals.formatBank(bank));
			} else
				CB.setSelectedIndex(-1);
		else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadChequeCB(JComboBox<String> CB, String bankAccountNo, String chequeNo) {
		ArrayList<Cheque> cheques = AppLogic.Cheques.searchByBankAccountNo(bankAccountNo);
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i < cheques.size(); i++) {
			strings.add(cheques.get(i).getNumber());
		}
		CB.setModel(new DefaultComboBoxModel(strings.toArray()));
		if (chequeNo != null) {
			Cheque cheque = AppLogic.Cheques.get(AppLogic.Cheques.indexOf(chequeNo, bankAccountNo));
			CB.setSelectedItem(cheque.getNumber());
		} else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadCustomerCB(JComboBox<String> CB) {
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<Customer> customers = AppLogic.Customers.getList();
		strings.add("--- COMPANIES ---");
		for (int i = 0; i < customers.size(); i++)
			if (!customers.get(i).getName().equals(customers.get(i).getContactPerson()))
				strings.add(customers.get(i).getName());
		strings.add("--- INDIVIDUALS ---");
		for (int i = 0; i < customers.size(); i++)
			if (customers.get(i).getName().equals(customers.get(i).getContactPerson()))
				strings.add(customers.get(i).getName());
		CB.setModel(new DefaultComboBoxModel(strings.toArray()) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void setSelectedItem(Object item) {
				if (item != null)
					if (item.toString().startsWith("---"))
						return;
				super.setSelectedItem(item);
			};
		});
		CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadEntityCB(JComboBox<String> CB) {
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<Entity> companies = AppLogic.Entities.getAllCompanies();
		ArrayList<Entity> individuals = AppLogic.Entities.getAllIndividuals();
		strings.add("--- COMPANIES ---");
		for (int i = 0; i < companies.size(); i++)
			strings.add(companies.get(i).getName());
		strings.add("--- INDIVIDUALS ---");
		for (int i = 0; i < individuals.size(); i++)
			strings.add(individuals.get(i).getName());
		CB.setModel(new DefaultComboBoxModel(strings.toArray()) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void setSelectedItem(Object item) {
				if (item != null)
					if (item.toString().startsWith("---"))
						return;
				super.setSelectedItem(item);
			};
		});
		CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadLoanEntityCB(JComboBox<String> CB) {
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<Bank> banks = AppLogic.Banks.getList();
		ArrayList<Entity> individuals = AppLogic.Entities.getAllIndividuals();
		strings.add("--- BANKS ---");
		for (int i = 0; i < banks.size(); i++)
			strings.add(_GUIGlobals.formatBank(banks.get(i)));
		strings.add("--- INDIVIDUALS ---");
		for (int i = 0; i < individuals.size(); i++)
			strings.add(individuals.get(i).getName());
		CB.setModel(new DefaultComboBoxModel(strings.toArray()) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void setSelectedItem(Object item) {
				if (item != null)
					if (item.toString().startsWith("---"))
						return;
				super.setSelectedItem(item);
			};
		});
		CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadFamilyCB(JComboBox<String> CB, String entityName) {
		ArrayList<String> strings = new ArrayList<String>();
		Entity entity1 = AppLogic.Entities.get(AppLogic.Entities.indexOf("Abid Farhan"));
		Entity entity2 = AppLogic.Entities.get(AppLogic.Entities.indexOf("Aqib Raihan"));
		Entity entity3 = AppLogic.Entities.get(AppLogic.Entities.indexOf("Asif Hasan"));
		strings.add(entity1.getName());
		strings.add(entity2.getName());
		strings.add(entity3.getName());
		CB.setModel(new DefaultComboBoxModel(strings.toArray()));
		CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadInvestorCB(JComboBox<String> CB, String investor) {
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<Investor> investors = AppLogic.Investors.getList();
		strings.add("--- INVESTORS ---");
		for (int i = 0; i < investors.size(); i++)
			strings.add(investors.get(i).getName());
		CB.setModel(new DefaultComboBoxModel(strings.toArray()) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void setSelectedItem(Object item) {
				if (item != null)
					if (item.toString().startsWith("---"))
						return;
				super.setSelectedItem(item);
			};
		});
		if (investor != null)
			CB.setSelectedItem(investor);
		else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadDriverCB(JComboBox<String> CB, String driver) {
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<Driver> drivers = AppLogic.Drivers.getList();
		strings.add("--- DRIVERS ---");
		for (int i = 0; i < drivers.size(); i++)
			strings.add(drivers.get(i).getName());
		CB.setModel(new DefaultComboBoxModel(strings.toArray()) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void setSelectedItem(Object item) {
				if (item != null)
					if (item.toString().startsWith("---"))
						return;
				super.setSelectedItem(item);
			};
		});
		if (driver != null)
			CB.setSelectedItem(driver);
		else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadLicenseCB(JComboBox<String> CB, String licenseNo) {
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<License> licenses = AppLogic.Licenses.getList();
		for (int i = 0; i < licenses.size(); i++)
			strings.add(_GUIGlobals.formatLicense(licenses.get(i)));
		CB.setModel(new DefaultComboBoxModel(strings.toArray()));
		if (licenseNo != null)
			CB.setSelectedItem(licenseNo);
		else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadEmpPartnerCB(JComboBox<String> CB, String emp_partner) {
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<Emp_Partner> emp_partners = AppLogic.Emp_Partners.getList();
		strings.add("--- EMPLOYEES/PARTNERS ---");
		for (int i = 0; i < emp_partners.size(); i++)
			strings.add(emp_partners.get(i).getName());
		CB.setModel(new DefaultComboBoxModel(strings.toArray()) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void setSelectedItem(Object item) {
				if (item != null)
					if (item.toString().startsWith("---"))
						return;
				super.setSelectedItem(item);
			};
		});
		if (emp_partner != null)
			CB.setSelectedItem(emp_partner);
		else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadInventoryCB(JComboBox<String> CB, int inventoryNo) {
		ArrayList<Inventory> inventories = AppLogic.Inventories.getList();
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i < inventories.size(); i++) {
			Supplier supplier = AppLogic.Suppliers.searchByInventory(inventories.get(i).getNumber());
			if (supplier != null)
				strings.add(supplier.getName() + " " + _GUIGlobals.formatInventory(inventories.get(i)));
			Warehouse warehouse = AppLogic.Warehouses.searchByInventory(inventories.get(i).getNumber());
			if (warehouse != null)
				strings.add(
						_GUIGlobals.formatWarehouse(warehouse) + " " + _GUIGlobals.formatInventory(inventories.get(i)));
		}
		CB.setModel(new DefaultComboBoxModel(strings.toArray()));
		if (inventoryNo != -1) {
			Supplier supplier = AppLogic.Suppliers.searchByInventory(inventoryNo);
			if (supplier != null)
				CB.setSelectedItem(supplier.getName() + " " + _GUIGlobals
						.formatInventory(AppLogic.Inventories.get(AppLogic.Inventories.indexOf(inventoryNo))));
			else {
				Warehouse warehouse = AppLogic.Warehouses.searchByInventory(inventoryNo);
				if (warehouse != null)
					CB.setSelectedItem(_GUIGlobals.formatWarehouse(warehouse) + " " + _GUIGlobals
							.formatInventory(AppLogic.Inventories.get(AppLogic.Inventories.indexOf(inventoryNo))));
			}
		} else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadLocationCB(JComboBox<String> CB, String location) {
		ArrayList<Import> imports = AppLogic.Imports.getList();
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i < imports.size(); i++) {
			String string = imports.get(i).getFromLocation();
			if (!strings.contains(string))
				strings.add(string);
			string = imports.get(i).getToLocation();
			if (!strings.contains(string))
				strings.add(string);
		}
		ArrayList<Transport> transports = AppLogic.Transports.getList();
		for (int i = 0; i < transports.size(); i++) {
			String string = transports.get(i).getFromLocation();
			if (!strings.contains(string))
				strings.add(string);
			string = transports.get(i).getToLocation();
			if (!strings.contains(string))
				strings.add(string);
		}
		CB.setModel(new DefaultComboBoxModel(strings.toArray()));
		if (location != null)
			CB.setSelectedItem(location);
		else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadRentTypeCB(JComboBox<String> CB, String rentType) {
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<Rent> rents = AppLogic.Rents.getList();
		for (int i = 0; i < rents.size(); i++) {
			String string = rents.get(i).getRentType();
			if (string != null)
				if (!strings.contains(string))
					strings.add(string);
		}
		CB.setModel(new DefaultComboBoxModel(strings.toArray()));
		if (rentType != null)
			CB.setSelectedItem(rentType);
		else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadRenewalTypeCB(JComboBox<String> CB, String renewalType) {
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<RenewalFee> renewalFees = AppLogic.RenewalFees.getList();
		for (int i = 0; i < renewalFees.size(); i++) {
			String string = renewalFees.get(i).getRenewalType();
			if (string != null)
				if (!strings.contains(string))
					strings.add(string);
		}
		CB.setModel(new DefaultComboBoxModel(strings.toArray()));
		if (renewalType != null)
			CB.setSelectedItem(renewalType);
		else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadVehicleCB(JComboBox<String> CB, String vehiclePlateNo) {
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<Vehicle> vehicles = AppLogic.Vehicles.getList();
		for (int i = 0; i < vehicles.size(); i++) {
			String string = _GUIGlobals.formatVehicle(vehicles.get(i));
			if (string != null)
				if (!strings.contains(string))
					strings.add(string);
		}
		CB.setModel(new DefaultComboBoxModel(strings.toArray()));
		if (vehiclePlateNo != null)
			CB.setSelectedItem(vehiclePlateNo);
		else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadLoanCB(JComboBox<String> CB, String entityName, String loanType, int loanRef) {
		CB.removeAllItems();
		if (entityName != null)
			if (!entityName.equals("")) {
				Loaner loaner = AppLogic.Loaners.get(AppLogic.Loaners.indexOf(entityName));
				if (loaner != null)
					if (loaner.getBalance() != 0) {
						ArrayList<String> strings = new ArrayList<String>();
						ArrayList<Loan> loans = AppLogic.Loans.getList();
						ArrayList<Loan> cLoans = new ArrayList<Loan>();
						ArrayList<Loan> dLoans = new ArrayList<Loan>();
						for (int i = 0; i < loans.size(); i++) {
							if (loans.get(i).getEntityName().equals(entityName) && loans.get(i).getType() == 'C')
								cLoans.add(loans.get(i));
							if (loans.get(i).getEntityName().equals(entityName) && loans.get(i).getType() == 'D')
								dLoans.add(loans.get(i));
						}
						if (loanType == "from") {
							for (int i = 0; i < cLoans.size(); i++)
								if (cLoans.get(i).getLoanTransactionNo() == -1) {
									double amount = cLoans.get(i).getAmount();
									for (int j = 0; j < dLoans.size(); j++)
										if (dLoans.get(j).getLoanTransactionNo() == cLoans.get(i).getNumber())
											amount -= dLoans.get(j).getAmount();
									if (amount != 0)
										strings.add(_GUIGlobals.formatLoan(cLoans.get(i), amount));
								}
						} else if (loanType == "to")
							for (int i = 0; i < dLoans.size(); i++)
								if (dLoans.get(i).getLoanTransactionNo() == -1) {
									double amount = dLoans.get(i).getAmount();
									for (int j = 0; j < cLoans.size(); j++)
										if (cLoans.get(j).getLoanTransactionNo() == dLoans.get(i).getNumber())
											amount -= cLoans.get(j).getAmount();
									if (amount != 0)
										strings.add(_GUIGlobals.formatLoan(dLoans.get(i), amount));
								}
						CB.setModel(new DefaultComboBoxModel(strings.toArray()));
						if (loanRef == -1)
							CB.setSelectedIndex(-1);
						else {
							Loan loan = AppLogic.Loans.get(AppLogic.Loans.indexOf(loanRef));
							String string = "";
							double amount = loan.getAmount();
							if (loan.getType() == 'C') {
								for (int i = 0; i < loans.size(); i++)
									if (loans.get(i).getType() == 'D'
											&& loans.get(i).getLoanTransactionNo() == loan.getNumber())
										amount -= loans.get(i).getAmount();
							} else {
								for (int i = 0; i < loans.size(); i++)
									if (loans.get(i).getType() == 'C'
											&& loans.get(i).getLoanTransactionNo() == loan.getNumber())
										amount -= loans.get(i).getAmount();
							}
							string = _GUIGlobals.formatLoan(loan, amount);
							CB.setSelectedItem(string);
						}
					}
			}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadItemCB(JComboBox<String> CB, int tag, int inventoryNo, boolean isProductsOnly) {
		ArrayList<String> strings = new ArrayList<String>();
		Inventory inventory = AppLogic.Inventories.get(AppLogic.Inventories.indexOf(inventoryNo));
		if (inventory != null) {
			ArrayList<Item> items = inventory.getItems();
			if (isProductsOnly) {
				for (int i = 0; i < items.size(); i++) {
					ProductTag productTag = AppLogic.ProductTags
							.get(AppLogic.ProductTags.indexOf(items.get(i).getPriceTagID()));
					if (productTag != null) {
						Product product = AppLogic.Products.get(AppLogic.Products.indexOf(productTag.getProductCode()));
						String string = productTag.getTag() + " ["
								+ (productTag.getPrice() * product.getPiecesPerCarton()) + " AED]";
						strings.add(string);
					}
				}
			} else {
				for (int i = 0; i < items.size(); i++) {
					PriceTag priceTag = AppLogic.PriceTags
							.get(AppLogic.PriceTags.indexOf(items.get(i).getPriceTagID()));
					if (priceTag != null) {
						String string = priceTag.getTag() + " [" + priceTag.getPrice() + " AED]";
						strings.add(string);
					}
				}
			}
		} else {
			if (isProductsOnly)
				for (int i = 0; i < AppLogic.ProductTags.getList().size(); i++) {
					ProductTag productTag = AppLogic.ProductTags.get(i);
					String string = productTag.getTag() + " [" + productTag.getPrice() + " AED]";
					strings.add(string);
				}
			else
				for (int i = 0; i < AppLogic.PriceTags.getList().size(); i++) {
					PriceTag priceTag = AppLogic.PriceTags.get(i);
					String string = priceTag.getTag() + " [" + priceTag.getPrice() + " AED]";
					strings.add(string);
				}
		}
		CB.setModel(new DefaultComboBoxModel(strings.toArray()));
		if (tag != -1) {
			PriceTag priceTag = AppLogic.PriceTags.get(AppLogic.PriceTags.indexOf(tag));
			CB.setSelectedItem(priceTag.getTag() + " [" + priceTag.getPrice() + " AED]");
		} else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadAllItemCB(JComboBox<String> CB, int tag, boolean isProductsOnly) {
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<Product> products = AppLogic.Products.getList();
		strings.add("--- PRODUCTS ---");
		for (int i = 0; i < products.size(); i++)
			strings.add(_GUIGlobals.formatProduct(products.get(i)));
		if (!isProductsOnly) {
			strings.add("--- OFFERINGS ---");
			ArrayList<Offering> offerings = AppLogic.Offerings.getList();
			for (int i = 0; i < offerings.size(); i++)
				strings.add(_GUIGlobals.formatOffering(offerings.get(i)));
		}
		CB.setModel(new DefaultComboBoxModel(strings.toArray()) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void setSelectedItem(Object item) {
				if (item != null)
					if (item.toString().startsWith("---"))
						return;
				super.setSelectedItem(item);
			};
		});
		if (tag != -1) {
			PriceTag priceTag = AppLogic.PriceTags.get(AppLogic.PriceTags.indexOf(tag));
			CB.setSelectedItem(priceTag.getTag() + " [" + priceTag.getPrice() + " AED]");
		} else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadTransportCB(JComboBox<String> CB, int transportTransactionNo) {
		ArrayList<Transport> transports = AppLogic.Transports.getPureList();
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i < transports.size(); i++) {
			strings.add("" + transports.get(i).getNumber());
		}
		CB.setModel(new DefaultComboBoxModel(strings.toArray()));
		if (transportTransactionNo != -1) {
			Transport transport = AppLogic.Transports.get(AppLogic.Transports.indexOf(transportTransactionNo));
			if (transport != null)
				CB.setSelectedItem(new SimpleDateFormat("dd MMM yyyy")
						.format(transport.getDate() + " [TransNo=" + transport.getNumber() + "]"));
		} else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadInvoiceCB(JComboBox<String> CB, int invoiceNo) {
		ArrayList<Invoice> invoices = AppLogic.Invoices.getList();
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i < invoices.size(); i++) {
			strings.add("" + invoices.get(i).getNumber());
		}
		CB.setModel(new DefaultComboBoxModel(strings.toArray()));
		if (invoiceNo != -1)
			CB.setSelectedItem(Integer.toString(invoiceNo));
		else
			CB.setSelectedIndex(-1);
	}

	public static void loadProformaInvoiceCB(JComboBox<String> CB, int piNo) {
		ArrayList<ProformaInvoice> proformaInvoices = AppLogic.ProformaInvoices.getList();
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i < proformaInvoices.size(); i++) {
			strings.add("" + proformaInvoices.get(i).getNumber());
		}
		CB.setModel(new DefaultComboBoxModel(strings.toArray()));
		if (piNo != -1)
			CB.setSelectedItem(Integer.toString(piNo));
		else
			CB.setSelectedIndex(-1);
	}

	@SuppressWarnings("unchecked")
	public static void reset(JFrame frame) {
		ArrayList<JPanel> panels = new ArrayList<JPanel>();
		for (Component frameC : frame.getRootPane().getComponents()) {
			if (frameC instanceof JPanel)
				panels.add((JPanel) frameC);
			else if (frameC instanceof JLayeredPane)
				for (int i = 0; i < ((JLayeredPane) frameC).getComponentCount(); i++)
					panels.add((JPanel) ((JLayeredPane) frameC).getComponent(i));
			else if (frameC instanceof JFormattedTextField) {
				_GUIGlobals.parseMoney(((JFormattedTextField) frameC).getText());
				((JFormattedTextField) frameC).setText("0.00");
			} else if (frameC instanceof JTextField)
				try {
					Integer.parseInt(((JTextField) frameC).getText());
					((JTextField) frameC).setText("0");
				} catch (NumberFormatException e) {
					try {
						Double.parseDouble(((JTextField) frameC).getText());
						((JTextField) frameC).setText("0.00");
					} catch (NumberFormatException e2) {
						try {
							Long.parseLong(((JTextField) frameC).getText());
							((JTextField) frameC).setText("0.00");
						} catch (NumberFormatException e3) {
							((JTextField) frameC).setText("");
						}
					}
				}
			else if (frameC instanceof JComboBox)
				((JComboBox<String>) frameC).setSelectedIndex(-1);
			else if (frameC instanceof JTextArea)
				((JTextArea) frameC).setText("");
			else if (frameC instanceof JSpinner)
				((JSpinner) frameC).setValue(java.util.Calendar.getInstance().getTime());
			else if (frameC instanceof JCheckBox)
				((JCheckBox) frameC).setSelected(false);
		}
		for (int i = 0; i < panels.size(); i++) {
			for (Component panelC : panels.get(i).getComponents()) {
				if (panelC instanceof JFormattedTextField) {
					_GUIGlobals.parseMoney(((JFormattedTextField) panelC).getText());
					((JFormattedTextField) panelC).setText("0.00");
				} else if (panelC instanceof JTextField)
					try {
						Integer.parseInt(((JTextField) panelC).getText());
						((JTextField) panelC).setText("0");
					} catch (NumberFormatException e) {
						try {
							Double.parseDouble(((JTextField) panelC).getText());
							((JTextField) panelC).setText("0.00");
						} catch (NumberFormatException e2) {
							try {
								Long.parseLong(((JTextField) panelC).getText());
								((JTextField) panelC).setText("0.00");
							} catch (NumberFormatException e3) {
								((JTextField) panelC).setText("");
							}
						}
					}
				else if (panelC instanceof JComboBox)
					((JComboBox<String>) panelC).setSelectedIndex(-1);
				else if (panelC instanceof JTextArea)
					((JTextArea) panelC).setText("");
				else if (panelC instanceof JSpinner)
					((JSpinner) panelC).setValue(java.util.Calendar.getInstance().getTime());
				else if (panelC instanceof JCheckBox)
					((JCheckBox) panelC).setSelected(false);
			}
		}
	}

	public static void create(String selection) {
		switch (selection) {
		case "ADD SALE": {
			Frame.Sale saleF = new Frame.Sale(new AddPanel());
			if (AppLogic.Transactions.max() == -1)
				saleF.getTransactionTF().setText("ERROR");
			else
				saleF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
			saleF.getAmountTF().setText("0.00");
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = -1;
					if (!saleF.getTransactionTF().getText().equals(""))
						number = Integer.parseInt(saleF.getTransactionTF().getText());
					Date date = saleF.getDate();
					Time time = null;
					double amount = 0;
					if ((saleF.getTimeS().getValue() != null))
						time = Time.valueOf(
								new SimpleDateFormat("HH:mm:ss").format((java.util.Date) saleF.getTimeS().getValue()));
					if (!saleF.getAmountTF().getText().equals(""))
						amount = Double.parseDouble("" + _GUIGlobals.parseMoney(saleF.getAmountTF().getText()));
					char type = 'D';
					if (saleF.getTypeS().getValue() == 0)
						type = 'C';
					String entityName = (String) saleF.getEntityCB().getSelectedItem();
					if (entityName != null)
						if (!entityName.equals("")) {
							String description = saleF.getDescriptionTA().getText();
							String bankAccountNo = (String) saleF.getBankAccountCB().getSelectedItem();
							if (saleF.getIsBankCB().isSelected())
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										if (bankAccountNo.equals(""))
											new Notification("Please select a bank.");
							String chequeNo = (String) saleF.getChequeNoCB().getSelectedItem();
							if (saleF.getIsChequeCB().isSelected())
								if (chequeNo != null)
									if (saleF.getIsChequeCB().isSelected() && chequeNo.equals(""))
										new Notification("Please select a cheque.");
							if (!saleF.getIsSaleDetailsCB().isSelected())
								new Notification("Please enter sale details.");
							else if (saleF.getItems() == null)
								new Notification("Please enter an item.");
							else if (saleF.getItems().isEmpty())
								new Notification("Please enter an item.");
							else if (saleF.getIsBankCB().isSelected()) {
								if (saleF.getBankAccountCB().getSelectedItem() == null)
									new Notification("Please select a bank.");
								else if (saleF.getBankAccountCB().getSelectedItem().equals(""))
									new Notification("Please select a bank.");
							} else if (saleF.getIsChequeCB().isSelected()) {
								if (saleF.getChequeNoCB() == null)
									new Notification("Please select a cheque.");
								else if (saleF.getChequeNoCB().getSelectedItem().equals(""))
									new Notification("Please select a cheque.");
							} else {
								int inventoryNo = saleF.getInventoryNo();
								int invoiceNo = saleF.getInvoiceNo();
								int transportTransactionNo = saleF.getTransportTransactionNo();
								ArrayList<Item> items = saleF.getItems();
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										bankAccountNo = _GUIGlobals.parseBank(bankAccountNo).getAccountNo();
								if (Transactions.addSale(new Core.Sale(number, date, time, amount, type, description,
										bankAccountNo, chequeNo, entityName, items, inventoryNo, invoiceNo,
										transportTransactionNo))) {
									new Notification("Sale added.");
									reset(saleF);
									saleF.reset();
									saleF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
								} else {
									new Notification("Operation failed: Transaction could not be added.");
								}
							}
						} else
							new Notification("Please select an entity.");
					else
						new Notification("Please select an entity.");
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					saleF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) saleF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) saleF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadCustomerCB(saleF.getEntityCB());
			saleF.setVisible(true);
			tempObj = saleF;
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD SALE DETAILS": {
			SaleDetails saleDetailsF = new SaleDetails(new AddPanel());
			ActionListener addAL = new ActionListener() {

				@SuppressWarnings("unlikely-arg-type")
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (saleDetailsF.getInventoryNo() == -1)
						new Notification("Please select an inventory.");
					else if (saleDetailsF.getIsTransportCB().isSelected()) {
						if (saleDetailsF.getTransportTransactionNoCB() == null)
							new Notification("Please select a transport.");
						else if (saleDetailsF.getTransportTransactionNoCB().equals(""))
							new Notification("Please select a transport.");
						else
							saleDetailsF.setTransportTransactionNo(
									(int) saleDetailsF.getTransportTransactionNoCB().getSelectedItem());
					} else {
						saleDetailsF.setInventoryNo(
								_GUIGlobals.parseInventory((String) saleDetailsF.getInventoryNoCB().getSelectedItem())
										.getNumber());
						if (saleDetailsF.getInvoiceNoCB().getSelectedItem() != null)
							if (!saleDetailsF.getInvoiceNoCB().getSelectedItem().equals(""))
								saleDetailsF.setInvoiceNo(
										Integer.parseInt((String) saleDetailsF.getInvoiceNoCB().getSelectedItem()));
						((Frame.Sale) tempObj).setInventoryNo(saleDetailsF.getInventoryNo());
						((Frame.Sale) tempObj).setInvoiceNo(saleDetailsF.getInvoiceNo());
						((Frame.Sale) tempObj).setTransportTransactionNo(saleDetailsF.getTransportTransactionNo());
						((Frame.Sale) tempObj).getIsSaleDetailsCB().setSelected(true);
						if (saleDetailsF.getInvoiceNo() == -1)
							((Frame.Sale) tempObj).setItems(null);
						else {
							Invoice invoice = AppLogic.Invoices
									.get(AppLogic.Invoices.indexOf(saleDetailsF.getInvoiceNo()));
							((Frame.Sale) tempObj).setItems(invoice.getItems());
						}
						if (((Frame.Sale) tempObj).getItems() == null)
							((Frame.Sale) tempObj).getItemTF().setText("0");
						else
							((Frame.Sale) tempObj).getItemTF().setText("" + ((Frame.Sale) tempObj).getItems().size());
						saleDetailsF.dispose();
						_GUIGlobals.activeFrames--;
					}
				}

			};

			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					saleDetailsF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) saleDetailsF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) saleDetailsF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadInventoryCB(saleDetailsF.getInventoryNoCB(), ((Frame.Sale) tempObj).getInventoryNo());
			loadInvoiceCB(saleDetailsF.getInvoiceNoCB(), ((Frame.Sale) tempObj).getInvoiceNo());
			loadTransportCB(saleDetailsF.getTransportTransactionNoCB(),
					((Frame.Sale) tempObj).getTransportTransactionNo());
			saleDetailsF.setVisible(true);
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD IMPORT": {
			Frame.Import importF = new Frame.Import(new AddPanel());
			if (AppLogic.Transactions.max() == -1)
				importF.getTransactionTF().setText("ERROR");
			else
				importF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
			importF.getAmountTF().setText("0.00");
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = -1;
					if (!importF.getTransactionTF().getText().equals(""))
						number = Integer.parseInt(importF.getTransactionTF().getText());
					Date date = importF.getDate();
					Time time = null;
					double amount = 0;
					if ((importF.getTimeS().getValue() != null))
						time = Time.valueOf(new SimpleDateFormat("HH:mm:ss")
								.format((java.util.Date) importF.getTimeS().getValue()));
					if (!importF.getAmountTF().getText().equals(""))
						amount = Double.parseDouble("" + _GUIGlobals.parseMoney(importF.getAmountTF().getText()));
					char type = 'D';
					if (importF.getTypeS().getValue() == 0)
						type = 'C';
					String entityName = (String) importF.getEntityCB().getSelectedItem();
					if (entityName != null)
						if (!entityName.equals("")) {
							String description = importF.getDescriptionTA().getText();
							String bankAccountNo = (String) importF.getBankAccountCB().getSelectedItem();
							if (importF.getIsBankCB().isSelected())
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										if (bankAccountNo.equals(""))
											new Notification("Please select a bank.");
							String chequeNo = (String) importF.getChequeNoCB().getSelectedItem();
							if (importF.getIsChequeCB().isSelected())
								if (chequeNo != null)
									if (importF.getIsChequeCB().isSelected() && chequeNo.equals(""))
										new Notification("Please select a cheque.");
							if (!importF.getIsImportDetailsCB().isSelected())
								new Notification("Please enter import details.");
							else if (importF.getItems() == null)
								new Notification("Please enter an item.");
							else if (importF.getItems().isEmpty())
								new Notification("Please enter an item.");
							else if (importF.getIsBankCB().isSelected()) {
								if (importF.getBankAccountCB().getSelectedItem() == null)
									new Notification("Please select a bank.");
								else if (importF.getBankAccountCB().getSelectedItem().equals(""))
									new Notification("Please select a bank.");
							} else if (importF.getIsChequeCB().isSelected()) {
								if (importF.getChequeNoCB() == null)
									new Notification("Please select a cheque.");
								else if (importF.getChequeNoCB().getSelectedItem().equals(""))
									new Notification("Please select a cheque.");
							} else {
								String fromLocation = importF.getFromLocation();
								String toLocation = importF.getToLocation();
								double customDuty = importF.getCustomDuty();
								double deliveryOrder = importF.getDeliveryOrder();
								double clearingAgent = importF.getClearingAgent();
								double token = importF.getToken();
								double THC = importF.getTHC();
								int transportTransactionNo = importF.getTransportTransactionNo();
								ArrayList<Item> items = importF.getItems();
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										bankAccountNo = _GUIGlobals.parseBank(bankAccountNo).getAccountNo();
								if (Transactions.addImport(
										new Core.Import(number, date, time, amount, type, description, bankAccountNo,
												chequeNo, entityName, items, toLocation, fromLocation, customDuty,
												deliveryOrder, clearingAgent, token, THC, transportTransactionNo))) {
									new Notification("Import added.");
									reset(importF);
									importF.reset();
									importF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
								} else {
									new Notification("Operation failed: Transaction could not be added.");
								}
							}
						} else
							new Notification("Please select an entity.");
					else
						new Notification("Please select an entity.");
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					importF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) importF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) importF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadEntityCB(importF.getEntityCB());
			importF.setVisible(true);
			tempObj = importF;
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD IMPORT DETAILS": {
			Frame.ImportDetails importDetailsF = new Frame.ImportDetails(new AddPanel());
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (importDetailsF.getIsTransportCB().isSelected()
							&& importDetailsF.getTransportTransactionNoCB().isEnabled()) {
						if (importDetailsF.getTransportTransactionNoCB().getSelectedItem() == null)
							new Notification("Please select a transport.");
						else if (importDetailsF.getTransportTransactionNoCB().getSelectedItem().equals(""))
							new Notification("Please select a transport.");
						else
							importDetailsF.setTransportTransactionNo(Integer
									.parseInt((String) importDetailsF.getTransportTransactionNoCB().getSelectedItem()));
					} else {
						if (importDetailsF.getFromLocationCB().getSelectedItem() != null)
							if (!importDetailsF.getFromLocationCB().getSelectedItem().equals(""))
								if (importDetailsF.getToLocationCB().getSelectedItem() != null)
									if (!importDetailsF.getToLocationCB().getSelectedItem().equals("")) {
										((Frame.Import) tempObj).setFromLocation(
												(String) importDetailsF.getFromLocationCB().getSelectedItem());
										((Frame.Import) tempObj).setToLocation(
												(String) importDetailsF.getToLocationCB().getSelectedItem());
										((Frame.Import) tempObj).setCustomDuty(
												_GUIGlobals.parseMoney(importDetailsF.getCustomDutyFTF().getText()));
										((Frame.Import) tempObj).setClearingAgent(
												_GUIGlobals.parseMoney(importDetailsF.getClearingAgentFTF().getText()));
										((Frame.Import) tempObj).setDeliveryOrder(
												_GUIGlobals.parseMoney(importDetailsF.getDeliveryOrderFTF().getText()));
										((Frame.Import) tempObj).setToken(
												_GUIGlobals.parseMoney(importDetailsF.getTokenFTF().getText()));
										((Frame.Import) tempObj)
												.setTHC(_GUIGlobals.parseMoney(importDetailsF.getTHCFTF().getText()));
										((Frame.Import) tempObj).getIsImportDetailsCB().setSelected(true);
										if (((Frame.Import) tempObj).getItems() == null)
											((Frame.Import) tempObj).getItemTF().setText("0");
										else
											((Frame.Import) tempObj).getItemTF()
													.setText("" + ((Frame.Sale) tempObj).getItems().size());
										double amount = ((Frame.Import) tempObj).getCustomDuty()
												+ ((Frame.Import) tempObj).getClearingAgent()
												+ ((Frame.Import) tempObj).getDeliveryOrder()
												+ ((Frame.Import) tempObj).getToken()
												+ ((Frame.Import) tempObj).getTHC();
										((Frame.Import) tempObj).setAmountTF(amount);
										importDetailsF.dispose();
										_GUIGlobals.activeFrames--;
									} else
										new Notification("Please enter both locations.");
								else
									new Notification("Please enter both locations.");
							else
								new Notification("Please enter both locations.");
						else
							new Notification("Please enter both locations.");
					}
				}

			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					importDetailsF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) importDetailsF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) importDetailsF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadLocationCB(importDetailsF.getFromLocationCB(), null);
			loadLocationCB(importDetailsF.getToLocationCB(), null);
			importDetailsF.setVisible(true);
			tempObj2 = importDetailsF;
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD LOCAL": {
			Frame.Purchase purchaseF = new Frame.Purchase(new AddPanel());
			if (AppLogic.Transactions.max() == -1)
				purchaseF.getTransactionTF().setText("ERROR");
			else
				purchaseF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
			purchaseF.getAmountTF().setText("0.00");
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = -1;
					if (!purchaseF.getTransactionTF().getText().equals(""))
						number = Integer.parseInt(purchaseF.getTransactionTF().getText());
					Date date = purchaseF.getDate();
					Time time = null;
					double amount = 0;
					if ((purchaseF.getTimeS().getValue() != null))
						time = Time.valueOf(new SimpleDateFormat("HH:mm:ss")
								.format((java.util.Date) purchaseF.getTimeS().getValue()));
					if (!purchaseF.getAmountTF().getText().equals(""))
						amount = Double.parseDouble("" + _GUIGlobals.parseMoney(purchaseF.getAmountTF().getText()));
					char type = 'D';
					if (purchaseF.getTypeS().getValue() == 0)
						type = 'C';
					String entityName = (String) purchaseF.getEntityCB().getSelectedItem();
					if (entityName != null)
						if (!entityName.equals("")) {
							String description = purchaseF.getDescriptionTA().getText();
							String bankAccountNo = (String) purchaseF.getBankAccountCB().getSelectedItem();
							if (purchaseF.getIsBankCB().isSelected())
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										if (bankAccountNo.equals(""))
											new Notification("Please select a bank.");
							String chequeNo = (String) purchaseF.getChequeNoCB().getSelectedItem();
							if (purchaseF.getIsChequeCB().isSelected())
								if (chequeNo != null)
									if (purchaseF.getIsChequeCB().isSelected() && chequeNo.equals(""))
										new Notification("Please select a cheque.");
							if (!purchaseF.getIsPurchaseDetailsCB().isSelected())
								new Notification("Please enter import details.");
							else if (purchaseF.getItems() == null)
								new Notification("Please enter an item.");
							else if (purchaseF.getItems().isEmpty())
								new Notification("Please enter an item.");
							else if (purchaseF.getIsBankCB().isSelected()) {
								if (purchaseF.getBankAccountCB().getSelectedItem() == null)
									new Notification("Please select a bank.");
								else if (purchaseF.getBankAccountCB().getSelectedItem().equals(""))
									new Notification("Please select a bank.");
							} else if (purchaseF.getIsChequeCB().isSelected()) {
								if (purchaseF.getChequeNoCB() == null)
									new Notification("Please select a cheque.");
								else if (purchaseF.getChequeNoCB().getSelectedItem().equals(""))
									new Notification("Please select a cheque.");
							} else {
								int transportTransactionNo = purchaseF.getTransportTransactionNo();
								ArrayList<Item> items = purchaseF.getItems();
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										bankAccountNo = _GUIGlobals.parseBank(bankAccountNo).getAccountNo();
								if (Transactions
										.addPurchase(new Core.Purchase(number, date, time, amount, type, description,
												bankAccountNo, chequeNo, entityName, items, transportTransactionNo))) {
									new Notification("Local Purchase added.");
									reset(purchaseF);
									purchaseF.reset();
									purchaseF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
								} else {
									new Notification("Operation failed: Transaction could not be added.");
								}
							}
						} else
							new Notification("Please select an entity.");
					else
						new Notification("Please select an entity.");
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					purchaseF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) purchaseF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) purchaseF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadEntityCB(purchaseF.getEntityCB());
			purchaseF.setVisible(true);
			tempObj = purchaseF;
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD LOCAL DETAILS": {
			Frame.PurchaseDetails purchaseDetailsF = new Frame.PurchaseDetails(new AddPanel());
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (purchaseDetailsF.getIsTransportCB().isSelected()) {
						if (purchaseDetailsF.getTransportTransactionNoCB().getSelectedItem() == null)
							new Notification("Please select a transport.");
						else if (purchaseDetailsF.getTransportTransactionNoCB().getSelectedItem().equals(""))
							new Notification("Please select a transport.");
						else
							purchaseDetailsF.setTransportTransactionNo(Integer.parseInt(
									(String) purchaseDetailsF.getTransportTransactionNoCB().getSelectedItem()));
					} else {
						((Frame.Purchase) tempObj).getIsPurchaseDetailsCB().setSelected(true);
						if (((Frame.Purchase) tempObj).getItems() == null)
							((Frame.Purchase) tempObj).getItemTF().setText("0");
						else
							((Frame.Purchase) tempObj).getItemTF()
									.setText("" + ((Frame.Sale) tempObj).getItems().size());
						purchaseDetailsF.dispose();
						_GUIGlobals.activeFrames--;
					}
				}

			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					purchaseDetailsF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) purchaseDetailsF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) purchaseDetailsF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			purchaseDetailsF.setVisible(true);
			tempObj2 = purchaseDetailsF;
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD MANUFACTURE": {

			break;
		}
		case "ADD TRANSPORT": {

			break;
		}
		case "ADD TRANSPORT DETAILS 1": {

			break;
		}
		case "ADD TRANSPORT DETAILS 2": {
			Frame.TransportDetails2 transportDetailsF = new Frame.TransportDetails2(new AddPanel());
			transportDetailsF.getAmountTF().setText("0.00");
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (transportDetailsF.getFromLocationCB().getSelectedItem() != null)
						if (!transportDetailsF.getFromLocationCB().getSelectedItem().equals(""))
							if (transportDetailsF.getToLocationCB().getSelectedItem() != null)
								if (!transportDetailsF.getToLocationCB().getSelectedItem().equals(""))
									if (transportDetailsF.getInventoryNoCB().getSelectedItem() != null)
										if (!transportDetailsF.getInventoryNoCB().getSelectedItem().equals("")) {
											if (tempObj.getClass().toString().equals("class Frame.Sale")) {
												((Frame.Sale) tempObj).setFromLocationT((String) transportDetailsF
														.getFromLocationCB().getSelectedItem());
												((Frame.Sale) tempObj).setToLocationT(
														(String) transportDetailsF.getToLocationCB().getSelectedItem());
												((Frame.Sale) tempObj).setInventoryNoT(_GUIGlobals.parseInventory(
														(String) transportDetailsF.getInventoryNoCB().getSelectedItem())
														.getNumber());
												((Frame.Sale) tempObj).setAmountT(_GUIGlobals
														.parseMoney(transportDetailsF.getAmountTF().getText()));
												((Frame.SaleDetails) tempObj2).getTransportTransactionNoCB()
														.setEnabled(false);
											} else if (tempObj.getClass().toString().equals("class Frame.Import")) {
												((Frame.Import) tempObj).setFromLocationT((String) transportDetailsF
														.getFromLocationCB().getSelectedItem());
												((Frame.Import) tempObj).setToLocationT(
														(String) transportDetailsF.getToLocationCB().getSelectedItem());
												((Frame.Import) tempObj).setInventoryNoT(_GUIGlobals.parseInventory(
														(String) transportDetailsF.getInventoryNoCB().getSelectedItem())
														.getNumber());
												((Frame.Import) tempObj).setAmountT(_GUIGlobals
														.parseMoney(transportDetailsF.getAmountTF().getText()));
												((Frame.ImportDetails) tempObj2).getTransportTransactionNoCB()
														.setEnabled(false);
											}
											transportDetailsF.dispose();
											_GUIGlobals.activeFrames--;
										} else
											new Notification("Please select an inventory.");
									else
										new Notification("Please select an inventory.");
								else
									new Notification("Please enter both locations.");
							else
								new Notification("Please enter both locations.");
						else
							new Notification("Please enter both locations.");
					else
						new Notification("Please enter both locations.");
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					transportDetailsF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) transportDetailsF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) transportDetailsF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadLocationCB(transportDetailsF.getFromLocationCB(), null);
			loadLocationCB(transportDetailsF.getToLocationCB(), null);
			loadInventoryCB(transportDetailsF.getInventoryNoCB(), -1);
			transportDetailsF.setVisible(true);
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD DAMAGE": {
			Frame.Damage damageF = new Frame.Damage(new AddPanel());
			if (AppLogic.Transactions.max() == -1)
				damageF.getTransactionTF().setText("ERROR");
			else
				damageF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = -1;
					if (!damageF.getTransactionTF().getText().equals(""))
						number = Integer.parseInt(damageF.getTransactionTF().getText());
					Date date = damageF.getDate();
					Time time = null;
					if ((damageF.getTimeS().getValue() != null))
						time = Time.valueOf(new SimpleDateFormat("HH:mm:ss")
								.format((java.util.Date) damageF.getTimeS().getValue()));
					String description = damageF.getDescriptionTA().getText();
					int inventoryNo = damageF.getInventoryNo();
					if (damageF.getItems() == null)
						new Notification("Please enter an item.");
					else if (damageF.getItems().isEmpty())
						new Notification("Please enter an item.");
					else {
						ArrayList<Item> items = damageF.getItems();
						if (Transactions.addDamage(new Core.Damage(number, date, time, 0, 'D', description, null, null,
								null, items, inventoryNo))) {
							new Notification("Damage added.");
							reset(damageF);
							damageF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
						} else
							new Notification("Operation failed: Transaction could not be added.");
					}
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					damageF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) damageF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) damageF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			damageF.setVisible(true);
			tempObj = damageF;
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD INVESTMENT": {
			Investment investmentF = new Investment(new AddPanel());
			if (AppLogic.Transactions.max() == -1)
				investmentF.getTransactionTF().setText("ERROR");
			else
				investmentF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
			investmentF.getAmountTF().setText("0.00");
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = -1;
					if (!investmentF.getTransactionTF().getText().equals(""))
						number = Integer.parseInt(investmentF.getTransactionTF().getText());
					Date date = investmentF.getDate();
					Time time = null;
					double amount = 0;
					if ((investmentF.getTimeS().getValue() != null))
						time = Time.valueOf(new SimpleDateFormat("HH:mm:ss")
								.format((java.util.Date) investmentF.getTimeS().getValue()));
					if (!investmentF.getAmountTF().getText().equals(""))
						amount = Double.parseDouble("" + _GUIGlobals.parseMoney(investmentF.getAmountTF().getText()));
					char type = 'D';
					if (investmentF.getTypeS().getValue() == 0)
						type = 'C';
					String entityName = (String) investmentF.getEntityCB().getSelectedItem();
					if (entityName != null)
						if (!entityName.equals("")) {
							String description = investmentF.getDescriptionTA().getText();
							String bankAccountNo = (String) investmentF.getBankAccountCB().getSelectedItem();
							if (investmentF.getIsBankCB().isSelected())
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										if (bankAccountNo.equals(""))
											new Notification("Please select a bank.");
							String chequeNo = (String) investmentF.getChequeNoCB().getSelectedItem();
							if (investmentF.getIsChequeCB().isSelected())
								if (chequeNo != null)
									if (investmentF.getIsChequeCB().isSelected() && chequeNo.equals(""))
										new Notification("Please select a cheque.");
							if (investmentF.getIsBankCB().isSelected()) {
								if (investmentF.getBankAccountCB().getSelectedItem() == null)
									new Notification("Please select a bank.");
								else if (investmentF.getBankAccountCB().getSelectedItem().equals(""))
									new Notification("Please select a bank.");
							} else if (investmentF.getIsChequeCB().isSelected()) {
								if (investmentF.getChequeNoCB() == null)
									new Notification("Please select a cheque.");
								else if (investmentF.getChequeNoCB().getSelectedItem().equals(""))
									new Notification("Please select a cheque.");
							} else {
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										bankAccountNo = _GUIGlobals.parseBank(bankAccountNo).getAccountNo();
								if (Transactions.addInvestment(new Core.Investment(number, date, time, amount, type,
										description, bankAccountNo, chequeNo, entityName))) {
									new Notification("Investment added.");
									reset(investmentF);
									investmentF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
								} else {
									new Notification("Operation failed: Transaction could not be added.");
								}
							}
						} else
							new Notification("Please select an entity.");
					else
						new Notification("Please select an entity.");
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					investmentF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) investmentF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) investmentF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadInvestorCB(investmentF.getEntityCB(), null);
			investmentF.setVisible(true);
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD PROFIT": {
			Profit profitF = new Profit(new AddPanel());
			if (AppLogic.Transactions.max() == -1)
				profitF.getTransactionTF().setText("ERROR");
			else
				profitF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
			profitF.getAmountTF().setText("0.00");
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = -1;
					if (!profitF.getTransactionTF().getText().equals(""))
						number = Integer.parseInt(profitF.getTransactionTF().getText());
					Date date = profitF.getDate();
					Time time = null;
					double amount = 0;
					if ((profitF.getTimeS().getValue() != null))
						time = Time.valueOf(new SimpleDateFormat("HH:mm:ss")
								.format((java.util.Date) profitF.getTimeS().getValue()));
					if (!profitF.getAmountTF().getText().equals(""))
						amount = Double.parseDouble("" + _GUIGlobals.parseMoney(profitF.getAmountTF().getText()));
					char type = 'D';
					if (profitF.getTypeS().getValue() == 0)
						type = 'C';
					String entityName = (String) profitF.getEntityCB().getSelectedItem();
					if (entityName != null)
						if (!entityName.equals("")) {
							String description = profitF.getDescriptionTA().getText();
							String bankAccountNo = (String) profitF.getBankAccountCB().getSelectedItem();
							if (profitF.getIsBankCB().isSelected())
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										if (bankAccountNo.equals(""))
											new Notification("Please select a bank.");
							String chequeNo = (String) profitF.getChequeNoCB().getSelectedItem();
							if (profitF.getIsChequeCB().isSelected())
								if (chequeNo != null)
									if (profitF.getIsChequeCB().isSelected() && chequeNo.equals(""))
										new Notification("Please select a cheque.");
							if (profitF.getIsBankCB().isSelected()) {
								if (profitF.getBankAccountCB().getSelectedItem() == null)
									new Notification("Please select a bank.");
								else if (profitF.getBankAccountCB().getSelectedItem().equals(""))
									new Notification("Please select a bank.");
							} else if (profitF.getIsChequeCB().isSelected()) {
								if (profitF.getChequeNoCB() == null)
									new Notification("Please select a cheque.");
								else if (profitF.getChequeNoCB().getSelectedItem().equals(""))
									new Notification("Please select a cheque.");
							} else {
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										bankAccountNo = _GUIGlobals.parseBank(bankAccountNo).getAccountNo();
								if (Transactions.addProfit(new Core.Profit(number, date, time, amount, type,
										description, bankAccountNo, chequeNo, entityName))) {
									new Notification("Profit added.");
									reset(profitF);
									profitF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
								} else {
									new Notification("Operation failed: Transaction could not be added.");
								}
							}
						} else
							new Notification("Please select an entity.");
					else
						new Notification("Please select an entity.");
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					profitF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) profitF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) profitF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadInvestorCB(profitF.getEntityCB(), null);
			profitF.setVisible(true);
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD PROMOTION": {
			Frame.Promotion promotionF = new Frame.Promotion(new AddPanel());
			if (AppLogic.Transactions.max() == -1)
				promotionF.getTransactionTF().setText("ERROR");
			else
				promotionF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = -1;
					if (!promotionF.getTransactionTF().getText().equals(""))
						number = Integer.parseInt(promotionF.getTransactionTF().getText());
					Date date = promotionF.getDate();
					Time time = null;
					if ((promotionF.getTimeS().getValue() != null))
						time = Time.valueOf(new SimpleDateFormat("HH:mm:ss")
								.format((java.util.Date) promotionF.getTimeS().getValue()));

					String description = promotionF.getDescriptionTA().getText();
					boolean isSample = promotionF.getIsSampleCB().isSelected();
					int inventoryNo = promotionF.getInventoryNo();
					if (promotionF.getItems() == null)
						new Notification("Please enter an item.");
					else if (promotionF.getItems().isEmpty())
						new Notification("Please enter an item.");
					else {
						ArrayList<Item> items = promotionF.getItems();
						if (Transactions.addPromotion(new Core.Promotion(number, date, time, 0, 'D', description, null,
								null, null, items, inventoryNo, isSample))) {
							new Notification("Promotion added.");
							reset(promotionF);
							promotionF.reset();
							promotionF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
						} else
							new Notification("Operation failed: Transaction could not be added.");
					}
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					promotionF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) promotionF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) promotionF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			promotionF.setVisible(true);
			tempObj = promotionF;
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD SALARY": {
			Salary salaryF = new Salary(new AddPanel());
			if (AppLogic.Transactions.max() == -1)
				salaryF.getTransactionTF().setText("ERROR");
			else
				salaryF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
			salaryF.getAmountTF().setText("0.00");
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = -1;
					if (!salaryF.getTransactionTF().getText().equals(""))
						number = Integer.parseInt(salaryF.getTransactionTF().getText());
					Date date = salaryF.getDate();
					Time time = null;
					double amount = 0;
					if ((salaryF.getTimeS().getValue() != null))
						time = Time.valueOf(new SimpleDateFormat("HH:mm:ss")
								.format((java.util.Date) salaryF.getTimeS().getValue()));
					if (!salaryF.getAmountTF().getText().equals(""))
						amount = Double.parseDouble("" + _GUIGlobals.parseMoney(salaryF.getAmountTF().getText()));
					char type = 'D';
					if (salaryF.getTypeS().getValue() == 0)
						type = 'C';
					String entityName = (String) salaryF.getEntityCB().getSelectedItem();
					if (entityName != null)
						if (!entityName.equals("")) {
							String description = salaryF.getDescriptionTA().getText();
							String bankAccountNo = (String) salaryF.getBankAccountCB().getSelectedItem();
							if (salaryF.getIsBankCB().isSelected())
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										if (bankAccountNo.equals(""))
											new Notification("Please select a bank.");
							String chequeNo = (String) salaryF.getChequeNoCB().getSelectedItem();
							if (salaryF.getIsChequeCB().isSelected())
								if (chequeNo != null)
									if (salaryF.getIsChequeCB().isSelected() && chequeNo.equals(""))
										new Notification("Please select a cheque.");
							if (salaryF.getIsBankCB().isSelected()) {
								if (salaryF.getBankAccountCB().getSelectedItem() == null)
									new Notification("Please select a bank.");
								else if (salaryF.getBankAccountCB().getSelectedItem().equals(""))
									new Notification("Please select a bank.");
							} else if (salaryF.getIsChequeCB().isSelected()) {
								if (salaryF.getChequeNoCB() == null)
									new Notification("Please select a cheque.");
								else if (salaryF.getChequeNoCB().getSelectedItem().equals(""))
									new Notification("Please select a cheque.");
							} else {
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										bankAccountNo = _GUIGlobals.parseBank(bankAccountNo).getAccountNo();
								if (Transactions.addSalary(new Core.Salary(number, date, time, amount, type,
										description, bankAccountNo, chequeNo, entityName))) {
									new Notification("Salary added.");
									reset(salaryF);
									salaryF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
								} else {
									new Notification("Operation failed: Transaction could not be added.");
								}
							}
						} else
							new Notification("Please select an entity.");
					else
						new Notification("Please select an entity.");
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					salaryF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) salaryF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) salaryF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadEmpPartnerCB(salaryF.getEntityCB(), null);
			salaryF.setVisible(true);
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD RENT": {
			Frame.Rent rentF = new Frame.Rent(new AddPanel());
			if (AppLogic.Transactions.max() == -1)
				rentF.getTransactionTF().setText("ERROR");
			else
				rentF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
			rentF.getAmountTF().setText("0.00");
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = -1;
					if (!rentF.getTransactionTF().getText().equals(""))
						number = Integer.parseInt(rentF.getTransactionTF().getText());
					Date date = rentF.getDate();
					Time time = null;
					double amount = 0;
					if ((rentF.getTimeS().getValue() != null))
						time = Time.valueOf(
								new SimpleDateFormat("HH:mm:ss").format((java.util.Date) rentF.getTimeS().getValue()));
					if (!rentF.getAmountTF().getText().equals(""))
						amount = Double.parseDouble("" + _GUIGlobals.parseMoney(rentF.getAmountTF().getText()));
					char type = 'D';
					if (rentF.getTypeS().getValue() == 0)
						type = 'C';
					String description = rentF.getDescriptionTA().getText();
					if (type == 'C') {
						String entityName = (String) rentF.getEntityCB().getSelectedItem();
						if (entityName != null)
							if (!entityName.equals("")) {
								String rentType = (String) rentF.getRentTypeCB().getSelectedItem();
								if (rentType != null)
									if (!rentType.equals("")) {
										String bankAccountNo = (String) rentF.getBankAccountCB().getSelectedItem();
										if (rentF.getIsBankCB().isSelected())
											if (bankAccountNo != null)
												if (!bankAccountNo.equals(""))
													if (bankAccountNo.equals(""))
														new Notification("Please select a bank.");
										String chequeNo = (String) rentF.getChequeNoCB().getSelectedItem();
										if (rentF.getIsChequeCB().isSelected())
											if (chequeNo != null)
												if (rentF.getIsChequeCB().isSelected() && chequeNo.equals(""))
													new Notification("Please select a cheque.");
										if (rentF.getIsBankCB().isSelected()) {
											if (rentF.getBankAccountCB().getSelectedItem() == null)
												new Notification("Please select a bank.");
											else if (rentF.getBankAccountCB().getSelectedItem().equals(""))
												new Notification("Please select a bank.");
										} else if (rentF.getIsChequeCB().isSelected()) {
											if (rentF.getChequeNoCB() == null)
												new Notification("Please select a cheque.");
											else if (rentF.getChequeNoCB().getSelectedItem().equals(""))
												new Notification("Please select a cheque.");
										} else {
											if (Transactions
													.addRent(
															new Core.Rent(number, date, time, amount, type, description,
																	bankAccountNo, chequeNo, entityName, rentType),
															null)) {
												new Notification("Rent added.");
												reset(rentF);
												rentF.getTransactionTF()
														.setText("" + (AppLogic.Transactions.max() + 1));
											} else {
												new Notification("Operation failed: Transaction could not be added.");
											}
										}
									} else
										new Notification("Please enter rent type.");
								else
									new Notification("Please enter rent type.");
							} else
								new Notification("Please select an entity.");
						else
							new Notification("Please select an entity.");
					} else if (type == 'D') {
						String licenseNo = (String) rentF.getLicenseNoCB().getSelectedItem();
						if (licenseNo != null)
							if (!licenseNo.equals("")) {
								String rentType = (String) rentF.getRentTypeCB().getSelectedItem();
								if (rentType != null)
									if (!rentType.equals("")) {
										String bankAccountNo = (String) rentF.getBankAccountCB().getSelectedItem();
										if (rentF.getIsBankCB().isSelected())
											if (bankAccountNo != null)
												if (!bankAccountNo.equals(""))
													if (bankAccountNo.equals(""))
														new Notification("Please select a bank.");
										String chequeNo = (String) rentF.getChequeNoCB().getSelectedItem();
										if (rentF.getIsChequeCB().isSelected())
											if (chequeNo != null)
												if (rentF.getIsChequeCB().isSelected() && chequeNo.equals(""))
													new Notification("Please select a cheque.");
										if (rentF.getIsBankCB().isSelected()) {
											if (rentF.getBankAccountCB().getSelectedItem() == null)
												new Notification("Please select a bank.");
											else if (rentF.getBankAccountCB().getSelectedItem().equals(""))
												new Notification("Please select a bank.");
										} else if (rentF.getIsChequeCB().isSelected()) {
											if (rentF.getChequeNoCB() == null)
												new Notification("Please select a cheque.");
											else if (rentF.getChequeNoCB().getSelectedItem().equals(""))
												new Notification("Please select a cheque.");
										} else {
											if (bankAccountNo != null)
												if (!bankAccountNo.equals(""))
													bankAccountNo = _GUIGlobals.parseBank(bankAccountNo).getAccountNo();
											if (Transactions.addRent(new Core.Rent(number, date, time, amount, type,
													description, _GUIGlobals.parseBank(bankAccountNo).getAccountNo(),
													chequeNo, null, rentType), licenseNo)) {
												new Notification("Rent added.");
												reset(rentF);
												rentF.getTransactionTF()
														.setText("" + (AppLogic.Transactions.max() + 1));
											} else {
												new Notification("Operation failed: Transaction could not be added.");
											}
										}
									} else
										new Notification("Please enter rent type.");
								else
									new Notification("Please enter rent type.");
							} else
								new Notification("Please select a license.");
						else
							new Notification("Please select a license.");
					}
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					rentF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) rentF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) rentF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadDriverCB(rentF.getEntityCB(), null);
			loadLicenseCB(rentF.getLicenseNoCB(), null);
			loadRentTypeCB(rentF.getRentTypeCB(), null);
			rentF.setVisible(true);
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD RENEWAL": {
			Frame.Renewal renewalFeeF = new Frame.Renewal(new AddPanel());
			if (AppLogic.Transactions.max() == -1)
				renewalFeeF.getTransactionTF().setText("ERROR");
			else
				renewalFeeF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
			renewalFeeF.getAmountTF().setText("0.00");
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = -1;
					if (!renewalFeeF.getTransactionTF().getText().equals(""))
						number = Integer.parseInt(renewalFeeF.getTransactionTF().getText());
					Date date = renewalFeeF.getDate();
					Time time = null;
					double amount = 0;
					if ((renewalFeeF.getTimeS().getValue() != null))
						time = Time.valueOf(new SimpleDateFormat("HH:mm:ss")
								.format((java.util.Date) renewalFeeF.getTimeS().getValue()));
					if (!renewalFeeF.getAmountTF().getText().equals(""))
						amount = Double.parseDouble("" + _GUIGlobals.parseMoney(renewalFeeF.getAmountTF().getText()));
					char type = 'D';
					if (renewalFeeF.getTypeS().getValue() == 0)
						type = 'C';
					String description = renewalFeeF.getDescriptionTA().getText();
					if (type == 'C') {
						String entityName = (String) renewalFeeF.getEntityCB().getSelectedItem();
						if (entityName != null)
							if (!entityName.equals("")) {
								String renewalFeeType = (String) renewalFeeF.getRenewalTypeCB().getSelectedItem();
								if (renewalFeeType != null)
									if (!renewalFeeType.equals("")) {
										String bankAccountNo = (String) renewalFeeF.getBankAccountCB()
												.getSelectedItem();
										if (renewalFeeF.getIsBankCB().isSelected())
											if (bankAccountNo != null)
												if (!bankAccountNo.equals(""))
													if (bankAccountNo.equals(""))
														new Notification("Please select a bank.");
										String chequeNo = (String) renewalFeeF.getChequeNoCB().getSelectedItem();
										if (renewalFeeF.getIsChequeCB().isSelected())
											if (chequeNo != null)
												if (renewalFeeF.getIsChequeCB().isSelected() && chequeNo.equals(""))
													new Notification("Please select a cheque.");
										if (renewalFeeF.getIsBankCB().isSelected()) {
											if (renewalFeeF.getBankAccountCB().getSelectedItem() == null)
												new Notification("Please select a bank.");
											else if (renewalFeeF.getBankAccountCB().getSelectedItem().equals(""))
												new Notification("Please select a bank.");
										} else if (renewalFeeF.getIsChequeCB().isSelected()) {
											if (renewalFeeF.getChequeNoCB() == null)
												new Notification("Please select a cheque.");
											else if (renewalFeeF.getChequeNoCB().getSelectedItem().equals(""))
												new Notification("Please select a cheque.");
										} else {
											if (bankAccountNo != null)
												if (!bankAccountNo.equals(""))
													bankAccountNo = _GUIGlobals.parseBank(bankAccountNo).getAccountNo();
											if (Transactions.addRenewalFee(
													new Core.RenewalFee(number, date, time, amount, type, description,
															bankAccountNo, chequeNo, entityName, renewalFeeType),
													null)) {
												new Notification("Renewal Fee added.");
												reset(renewalFeeF);
												renewalFeeF.getTransactionTF()
														.setText("" + (AppLogic.Transactions.max() + 1));
											} else {
												new Notification("Operation failed: Transaction could not be added.");
											}
										}
									} else
										new Notification("Please enter renewal type.");
								else
									new Notification("Please enter renewal type.");
							} else
								new Notification("Please select an entity.");
						else
							new Notification("Please select an entity.");
					} else if (type == 'D') {
						String licenseNo = (String) renewalFeeF.getLicenseNoCB().getSelectedItem();
						if (licenseNo != null)
							if (!licenseNo.equals("")) {
								License license = _GUIGlobals.parseLicense(licenseNo);
								String renewalFeeType = (String) renewalFeeF.getRenewalTypeCB().getSelectedItem();
								if (renewalFeeType != null)
									if (!renewalFeeType.equals("")) {
										String bankAccountNo = (String) renewalFeeF.getBankAccountCB()
												.getSelectedItem();
										if (renewalFeeF.getIsBankCB().isSelected())
											if (bankAccountNo != null)
												if (!bankAccountNo.equals(""))
													if (bankAccountNo.equals(""))
														new Notification("Please select a bank.");
										String chequeNo = (String) renewalFeeF.getChequeNoCB().getSelectedItem();
										if (renewalFeeF.getIsChequeCB().isSelected())
											if (chequeNo != null)
												if (renewalFeeF.getIsChequeCB().isSelected() && chequeNo.equals(""))
													new Notification("Please select a cheque.");
										if (renewalFeeF.getIsBankCB().isSelected()) {
											if (renewalFeeF.getBankAccountCB().getSelectedItem() == null)
												new Notification("Please select a bank.");
											else if (renewalFeeF.getBankAccountCB().getSelectedItem().equals(""))
												new Notification("Please select a bank.");
										} else if (renewalFeeF.getIsChequeCB().isSelected()) {
											if (renewalFeeF.getChequeNoCB() == null)
												new Notification("Please select a cheque.");
											else if (renewalFeeF.getChequeNoCB().getSelectedItem().equals(""))
												new Notification("Please select a cheque.");
										} else {
											if (bankAccountNo != null)
												if (!bankAccountNo.equals(""))
													bankAccountNo = _GUIGlobals.parseBank(bankAccountNo).getAccountNo();
											if (bankAccountNo != null)
												if (!bankAccountNo.equals(""))
													bankAccountNo = _GUIGlobals.parseBank(bankAccountNo).getAccountNo();
											if (Transactions.addRenewalFee(
													new Core.RenewalFee(number, date, time, amount, type, description,
															bankAccountNo, chequeNo, null, renewalFeeType),
													license.getNumber())) {
												new Notification("Renewal Fee added.");
												reset(renewalFeeF);
												renewalFeeF.getTransactionTF()
														.setText("" + (AppLogic.Transactions.max() + 1));
											} else {
												new Notification("Operation failed: Transaction could not be added.");
											}
										}
									} else
										new Notification("Please enter renewal type.");
								else
									new Notification("Please enter renewal type.");
							} else
								new Notification("Please select a license.");
						else
							new Notification("Please select a license.");
					}
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					renewalFeeF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) renewalFeeF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) renewalFeeF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadDriverCB(renewalFeeF.getEntityCB(), null);
			loadLicenseCB(renewalFeeF.getLicenseNoCB(), null);
			loadRenewalTypeCB(renewalFeeF.getRenewalTypeCB(), null);
			renewalFeeF.setVisible(true);
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD MAINTENANCE": {
			Maintenance maintenanceF = new Maintenance(new AddPanel());
			if (AppLogic.Transactions.max() == -1)
				maintenanceF.getTransactionTF().setText("ERROR");
			else
				maintenanceF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
			maintenanceF.getAmountTF().setText("0.00");
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = -1;
					if (!maintenanceF.getTransactionTF().getText().equals(""))
						number = Integer.parseInt(maintenanceF.getTransactionTF().getText());
					Date date = maintenanceF.getDate();
					Time time = null;
					double amount = 0;
					if ((maintenanceF.getTimeS().getValue() != null))
						time = Time.valueOf(new SimpleDateFormat("HH:mm:ss")
								.format((java.util.Date) maintenanceF.getTimeS().getValue()));
					if (!maintenanceF.getAmountTF().getText().equals(""))
						amount = Double.parseDouble("" + _GUIGlobals.parseMoney(maintenanceF.getAmountTF().getText()));
					char type = 'D';
					if (maintenanceF.getTypeS().getValue() == 0)
						type = 'C';
					String vehiclePlateNo = (String) maintenanceF.getVehicleCB().getSelectedItem();
					if (vehiclePlateNo != null)
						if (!vehiclePlateNo.equals("")) {
							String description = maintenanceF.getDescriptionTA().getText();
							String bankAccountNo = (String) maintenanceF.getBankAccountCB().getSelectedItem();
							if (maintenanceF.getIsBankCB().isSelected())
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										if (bankAccountNo.equals(""))
											new Notification("Please select a bank.");
							String chequeNo = (String) maintenanceF.getChequeNoCB().getSelectedItem();
							if (maintenanceF.getIsChequeCB().isSelected())
								if (chequeNo != null)
									if (maintenanceF.getIsChequeCB().isSelected() && chequeNo.equals(""))
										new Notification("Please select a cheque.");
							if (maintenanceF.getIsBankCB().isSelected()) {
								if (maintenanceF.getBankAccountCB().getSelectedItem() == null)
									new Notification("Please select a bank.");
								else if (maintenanceF.getBankAccountCB().getSelectedItem().equals(""))
									new Notification("Please select a bank.");
							} else if (maintenanceF.getIsChequeCB().isSelected()) {
								if (maintenanceF.getChequeNoCB() == null)
									new Notification("Please select a cheque.");
								else if (maintenanceF.getChequeNoCB().getSelectedItem().equals(""))
									new Notification("Please select a cheque.");
							} else {
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										bankAccountNo = _GUIGlobals.parseBank(bankAccountNo).getAccountNo();
								if (Transactions.addMaintenance(new Core.Maintenance(number, date, time, amount, type,
										description, bankAccountNo, chequeNo,
										_GUIGlobals.parseVehicle(vehiclePlateNo).getPlateNo()))) {
									new Notification("Maintenance added.");
									reset(maintenanceF);
									maintenanceF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
								} else {
									new Notification("Operation failed: Transaction could not be added.");
								}
							}
						} else
							new Notification("Please select a vehicle.");
					else
						new Notification("Please select a vehicle.");
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					maintenanceF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) maintenanceF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) maintenanceF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadVehicleCB(maintenanceF.getVehicleCB(), null);
			maintenanceF.setVisible(true);
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD DOMESTIC": {
			Domestic domesticF = new Domestic(new AddPanel());
			if (AppLogic.Transactions.max() == -1)
				domesticF.getTransactionTF().setText("ERROR");
			else
				domesticF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
			domesticF.getAmountTF().setText("0.00");
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = -1;
					if (!domesticF.getTransactionTF().getText().equals(""))
						number = Integer.parseInt(domesticF.getTransactionTF().getText());
					Date date = domesticF.getDate();
					Time time = null;
					double amount = 0;
					if ((domesticF.getTimeS().getValue() != null))
						time = Time.valueOf(new SimpleDateFormat("HH:mm:ss")
								.format((java.util.Date) domesticF.getTimeS().getValue()));
					if (!domesticF.getAmountTF().getText().equals(""))
						amount = Double.parseDouble("" + _GUIGlobals.parseMoney(domesticF.getAmountTF().getText()));
					char type = 'D';
					if (domesticF.getTypeS().getValue() == 0)
						type = 'C';
					String entityName = (String) domesticF.getEntityCB().getSelectedItem();
					String description = domesticF.getDescriptionTA().getText();
					String bankAccountNo = (String) domesticF.getBankAccountCB().getSelectedItem();
					if (domesticF.getIsBankCB().isSelected())
						if (bankAccountNo != null)
							if (!bankAccountNo.equals(""))
								if (bankAccountNo.equals(""))
									new Notification("Please select a bank.");
					String chequeNo = (String) domesticF.getChequeNoCB().getSelectedItem();
					if (domesticF.getIsChequeCB().isSelected())
						if (chequeNo != null)
							if (domesticF.getIsChequeCB().isSelected() && chequeNo.equals(""))
								new Notification("Please select a cheque.");
					if (domesticF.getIsBankCB().isSelected()) {
						if (domesticF.getBankAccountCB().getSelectedItem() == null)
							new Notification("Please select a bank.");
						else if (domesticF.getBankAccountCB().getSelectedItem().equals(""))
							new Notification("Please select a bank.");
					} else if (domesticF.getIsChequeCB().isSelected()) {
						if (domesticF.getChequeNoCB() == null)
							new Notification("Please select a cheque.");
						else if (domesticF.getChequeNoCB().getSelectedItem().equals(""))
							new Notification("Please select a cheque.");
					} else {
						if (bankAccountNo != null)
							if (!bankAccountNo.equals(""))
								bankAccountNo = _GUIGlobals.parseBank(bankAccountNo).getAccountNo();
						if (Transactions.addDomestic(new Core.Domestic(number, date, time, amount, type, description,
								bankAccountNo, chequeNo, entityName))) {
							new Notification("Domestic added.");
							reset(domesticF);
							domesticF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
						} else {
							new Notification("Operation failed: Transaction could not be added.");
						}
					}
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					domesticF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) domesticF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) domesticF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadFamilyCB(domesticF.getEntityCB(), null);
			domesticF.setVisible(true);
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD LOAN": {
			Frame.Loan loanF = new Frame.Loan(new AddPanel());
			if (AppLogic.Transactions.max() == -1)
				loanF.getTransactionTF().setText("ERROR");
			else
				loanF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
			loanF.getAmountTF().setText("0.00");
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = -1;
					if (!loanF.getTransactionTF().getText().equals(""))
						number = Integer.parseInt(loanF.getTransactionTF().getText());
					Date date = loanF.getDate();
					Time time = null;
					double amount = 0;
					if ((loanF.getTimeS().getValue() != null))
						time = Time.valueOf(
								new SimpleDateFormat("HH:mm:ss").format((java.util.Date) loanF.getTimeS().getValue()));
					if (!loanF.getAmountTF().getText().equals(""))
						amount = Double.parseDouble("" + _GUIGlobals.parseMoney(loanF.getAmountTF().getText()));
					char type = 'D';
					if (loanF.getTypeS().getValue() == 0)
						type = 'C';
					String entityName = (String) loanF.getEntityCB().getSelectedItem();
					if (entityName != null)
						if (!entityName.equals("")) {
							String description = loanF.getDescriptionTA().getText();
							String loanRef = (String) loanF.getLoanRefCB().getSelectedItem();
							boolean isActive = loanF.getIsActiveCB().isSelected();
							String bankAccountNo = (String) loanF.getBankAccountCB().getSelectedItem();
							if (loanF.getIsBankCB().isSelected())
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										if (bankAccountNo.equals(""))
											new Notification("Please select a bank.");
							String chequeNo = (String) loanF.getChequeNoCB().getSelectedItem();
							if (loanF.getIsChequeCB().isSelected())
								if (chequeNo != null)
									if (loanF.getIsChequeCB().isSelected() && chequeNo.equals(""))
										new Notification("Please select a cheque.");
							if (loanF.getIsBankCB().isSelected()) {
								if (loanF.getBankAccountCB().getSelectedItem() == null)
									new Notification("Please select a bank.");
								else if (loanF.getBankAccountCB().getSelectedItem().equals(""))
									new Notification("Please select a bank.");
							} else if (loanF.getIsChequeCB().isSelected()) {
								if (loanF.getChequeNoCB() == null)
									new Notification("Please select a cheque.");
								else if (loanF.getChequeNoCB().getSelectedItem().equals(""))
									new Notification("Please select a cheque.");
							} else {
								if (bankAccountNo != null)
									if (!bankAccountNo.equals(""))
										bankAccountNo = _GUIGlobals.parseBank(bankAccountNo).getAccountNo();
								int loanTransactionNo = -1;
								Bank bank = _GUIGlobals.parseBank(entityName);
								if (bank != null) {
									entityName = null;
								}
								if (loanRef != null)
									if (!loanRef.equals(""))
										loanTransactionNo = _GUIGlobals.parseLoan(loanRef, entityName).getNumber();
								if (bank != null) {
									ArrayList<Integer> loanTransactionNos = bank.getLoanTransactionNos();
									loanTransactionNos.add(number);
									AppLogic.Banks.update(bank.getAccountNo(), null, bank.getBalance(),
											loanTransactionNos, null);
								}
								if (Transactions.addLoan(new Core.Loan(number, date, time, amount, type, description,
										bankAccountNo, chequeNo, entityName, isActive, loanTransactionNo))) {
									new Notification("Loan added.");
									reset(loanF);
									loanF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
								} else {
									new Notification("Operation failed: Transaction could not be added.");
								}
							}
						} else
							new Notification("Please select an entity.");
					else
						new Notification("Please select an entity.");
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					loanF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) loanF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) loanF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadLoanEntityCB(loanF.getEntityCB());
			loanF.setVisible(true);
			_GUIGlobals.activeFrames++;
			break;
		}
		case "OLD LOANS": {

			break;
		}
		case "ADD TRANSACTION": {
			Transaction transactionF = new Transaction(new AddPanel());
			if (AppLogic.Transactions.max() == -1)
				transactionF.getTransactionTF().setText("ERROR");
			else
				transactionF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
			transactionF.getAmountTF().setText("0.00");
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = -1;
					if (!transactionF.getTransactionTF().getText().equals(""))
						number = Integer.parseInt(transactionF.getTransactionTF().getText());
					Date date = transactionF.getDate();
					Time time = null;
					double amount = 0;
					if ((transactionF.getTimeS().getValue() != null))
						time = Time.valueOf(new SimpleDateFormat("HH:mm:ss")
								.format((java.util.Date) transactionF.getTimeS().getValue()));
					if (!transactionF.getAmountTF().getText().equals(""))
						amount = _GUIGlobals.parseMoney(transactionF.getAmountTF().getText());
					char type = 'D';
					if (transactionF.getTypeS().getValue() == 0)
						type = 'C';
					String description = transactionF.getDescriptionTA().getText();
					String bankAccountNo = (String) transactionF.getBankAccountCB().getSelectedItem();
					if (transactionF.getIsBankCB().isSelected() && (bankAccountNo == null || bankAccountNo.equals("")))
						new Notification("Please select a bank.");
					else {
						bankAccountNo = null;
						String chequeNo = (String) transactionF.getChequeNoCB().getSelectedItem();
						if (transactionF.getIsChequeCB().isSelected() && (chequeNo == null || chequeNo.equals("")))
							new Notification("Please select a cheque.");
						else {
							chequeNo = null;
							String entityName = (String) transactionF.getEntityCB().getSelectedItem();
							ArrayList<Item> items = transactionF.getItems();
							if (AppLogic.Transactions.add(new Core.Transaction(number, date, time, amount, type,
									description, bankAccountNo, chequeNo, entityName, items))) {
								new Notification("Transaction added.");
								Logger.add(new Log("Transaction", "Number", number, Log.ADD));
								reset(transactionF);
								transactionF.getTransactionTF().setText("" + (AppLogic.Transactions.max() + 1));
							} else {
								new Notification("Operation failed: Transaction could not be added.");
							}
						}
					}
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					transactionF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) transactionF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) transactionF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadEntityCB(transactionF.getEntityCB());
			transactionF.setVisible(true);
			_GUIGlobals.activeFrames++;
			break;
		}
		case "EDIT TRANSACTION": {
			Transaction transactionF = new Transaction(new EditPanel());
			transactionF.getFunctionsLP().add(new UpdatePanel(),
					new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
			Core.Transaction transaction = AppLogic.Transactions.get(AppLogic.Transactions.getList().size() - 1); // <<<<<<<
																													// CHANGE
																													// THIS
																													// -
																													// RETRIEVE
																													// OBJECT
																													// FROM
			// VIEWER TABLE
			transactionF.getTransactionTF().setText("" + transaction.getNumber());
			if (transaction.getDate() != null)
				transactionF.getDateDC().setDate(transaction.getDate());
			else
				transactionF.getDateDC().setDate(new Date(Long.MIN_VALUE));
			if (transaction.getTime() != null)
				transactionF.getTimeS().setValue(new SimpleDateFormat("HH:mm:ss").format(transaction.getTime()));
			else
				try {
					transactionF.getTimeS().setValue(new SimpleDateFormat("HH:mm:ss").parseObject("00:00:00"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (transaction.getType() == 'C')
				transactionF.getTypeS().setValue(0);
			transactionF.getAmountTF().setText(_GUIGlobals.formatMoney(transaction.getAmount()));
			transactionF.getDescriptionTA().setText(transaction.getDescription());
			loadEntityCB(transactionF.getEntityCB());
			loadBankAccountCB(transactionF.getBankAccountCB(), transaction.getEntityName(),
					transaction.getBankAccountNo());
			loadChequeCB(transactionF.getChequeNoCB(), transaction.getBankAccountNo(), transaction.getChequeNo());
			transactionF.setEditable(false);
			ActionListener editAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					transactionF.setEditable(true);
					transactionF.getFunctionsLP().moveToBack(transactionF.getFunctionP());
					transactionF.getFunctionsLP().getComponent(1).setEnabled(false);
					transactionF.revalidate();
					transactionF.repaint();
					transactionF.getContentPane().repaint();
					transactionF.getContentPane().revalidate();
					transactionF.getFunctionsLP().getComponent(0).repaint();
					transactionF.getFunctionsLP().getComponent(0).revalidate();
					transactionF.getFunctionsLP().getComponent(1).repaint();
					transactionF.getFunctionsLP().getComponent(1).revalidate();
					transactionF.getFunctionsLP().repaint();
					transactionF.getFunctionsLP().revalidate();
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					transactionF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			ActionListener deleteAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub

				}
			};
			ActionListener updateAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub

				}
			};
			ActionListener cancelAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					transactionF.setEditable(false);
					transactionF.getFunctionsLP().moveToFront(transactionF.getFunctionP());
					transactionF.getFunctionsLP().repaint();
					transactionF.getFunctionsLP().revalidate();
				}
			};
			((RectangularButton) transactionF.getFunctionP().getComponent(0)).addActionListener(closeAL);
			((RectangularButton) transactionF.getFunctionP().getComponent(1)).addActionListener(editAL);
			((RectangularButton) transactionF.getFunctionP().getComponent(2)).addActionListener(deleteAL);
			((RectangularButton) ((JPanel) transactionF.getFunctionsLP().getComponent(1)).getComponent(0))
					.addActionListener(cancelAL);
			((RectangularButton) ((JPanel) transactionF.getFunctionsLP().getComponent(1)).getComponent(1))
					.addActionListener(updateAL);
			transactionF.setVisible(true);
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD STOCK": {

			break;
		}
		case "ADD PRODUCT": {

			break;
		}
		case "ADD MATERIAL": {

			break;
		}
		case "ADD SERVICE": {

			break;
		}
		case "ADD INVENTORY": {

			break;
		}
		case "EDIT INVENTORY": {

			break;
		}
		case "DELETE INVENTORY": {

			break;
		}
		case "ADD WAREHOUSE": {

			break;
		}
		case "ADD SUPPLIER": {

			break;
		}
		case "ADD EMPLOYEE/PARTNER": {

			break;
		}
		case "ADD INVESTOR": {

			break;
		}
		case "ADD DRIVER": {

			break;
		}
		case "ADD LOANER": {

			break;
		}
		case "ADD ENTITY": {

			break;
		}
		case "CURRENT": {

			break;
		}
		case "FINISHED": {

			break;
		}
		case "START NEW": {

			break;
		}
		case "ADD BANK": {

			break;
		}
		case "EDIT BANK": {

			break;
		}
		case "DELETE BANK": {

			break;
		}
		case "ADD CHEQUE": {

			break;
		}
		case "ADD LICENSE": {

			break;
		}
		case "EDIT LICENSE": {

			break;
		}
		case "DELETE LICENSE": {

			break;
		}
		case "CHANGE DATES": {
			break;
		}
		case "ADD VEHICLE": {

			break;
		}
		case "EDIT VEHICLE": {

			break;
		}
		case "DELETE VEHICLE": {

			break;
		}
		case "CHANGE DRIVER": {

			break;
		}
		case "ADD INVOICE": {
			Frame.Invoice invoiceF = new Frame.Invoice(new AddPanel2());
			if (AppLogic.Invoices.max() == -1)
				invoiceF.getInvoiceNoL().setText("1001");
			else
				invoiceF.getInvoiceNoL().setText("" + (AppLogic.Invoices.max() + 1));

			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int number = Integer.parseInt(invoiceF.getInvoiceNoL().getText());
					Date date = invoiceF.getDate();
					String paymentTerms = null;
					if (invoiceF.getPaymentTermsTF() != null)
						paymentTerms = invoiceF.getPaymentTermsTF().getText();
					String name = (String) invoiceF.getNameCB().getSelectedItem();
					int piNo = -1;
					if (invoiceF.getProformaInvoiceCB().getSelectedItem() != null)
						if (!invoiceF.getProformaInvoiceCB().getSelectedItem().equals(""))
							piNo = (int) invoiceF.getProformaInvoiceCB().getSelectedItem();
					ArrayList<JComboBox<String>> itemCBs = invoiceF.getItemCBs();
					ArrayList<JSpinner> quantitySs = invoiceF.getQuantitySs();
					ArrayList<JFormattedTextField> rateFTFs = invoiceF.getRateFTFs();
					ArrayList<Item> items = new ArrayList<Item>();
					ArrayList<Double> rates = new ArrayList<Double>();
					for (int i = 0; i < itemCBs.size(); i++) {
						String itemName = (String) itemCBs.get(i).getSelectedItem();
						if (itemName != null)
							if (!itemName.equals("")) {
								Item item = _GUIGlobals.parseItem(itemName, invoiceF.getInventoryNo());
								int quantity = (int) quantitySs.get(i).getValue();
								item.setQuantity(quantity);
								double rate = Double.parseDouble(rateFTFs.get(i).getText());
								items.add(item);
								rates.add(rate);
							}
					}
					Invoice invoice = new Invoice(number, date, name, paymentTerms, piNo, items, rates);
					if (Forms.addInvoice(invoice)) {
						Printer.print(invoice, rates);
						new Notification("Invoice added.");
					} else
						new Notification("Operation failed: Form could not be added.");
					invoiceF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};

			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					invoiceF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) invoiceF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) invoiceF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadCustomerCB(invoiceF.getNameCB());
			invoiceF.setVisible(true);
			_GUIGlobals.activeFrames++;
			break;
		}
		case "ADD PROMORMA INVOICE": {

			break;
		}
		case "ADD PURCHASE ORDER": {

			break;
		}
		case "ADD RECEIPT VOUHCER": {

			break;
		}
		case "ADD PAYMENT VOUCHER": {

			break;
		}
		case "ADD ITEM DETAILS": {
			ItemDetails itemDetailsF = new ItemDetails(new AddPanel());
			ActionListener addAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (itemDetailsF.getInventoryCB().isEnabled())
						if (itemDetailsF.getInventoryCB().getSelectedItem() == null)
							new Notification("Please select an inventory.");
						else if (itemDetailsF.getInventoryCB().getSelectedItem().equals(""))
							new Notification("Please select an inventory.");
						else
							itemDetailsF.setInventoryNo(
									_GUIGlobals.parseInventory((String) itemDetailsF.getInventoryCB().getSelectedItem())
											.getNumber());
					if ((int) itemDetailsF.getNoOfItemsS().getValue() != 0) {
						ArrayList<JComboBox<String>> itemCBs = itemDetailsF.getItemCBs();
						ArrayList<JSpinner> quantitySs = itemDetailsF.getQuantitySs();
						ArrayList<Item> items = new ArrayList<Item>();
						ArrayList<String> names = new ArrayList<String>();
						boolean isEmptyComboBox = false;
						String itemString = null;
						for (int i = 0; i < itemCBs.size(); i++) {
							itemString = (String) itemCBs.get(i).getSelectedItem();
							if (itemString != null)
								if (!itemString.equals("")) {
									Item item = null;
									if (itemDetailsF.getIsSpecific()) {
										item = _GUIGlobals.parseItem(itemString, itemDetailsF.getInventoryNo());
										item.setQuantity((int) quantitySs.get(i).getValue());
									} else {
										item = new Item(-1, (int) quantitySs.get(i).getValue());
										names.add(_GUIGlobals.parseProduct((String) itemCBs.get(i).getSelectedItem())
												.getName());
									}
									items.add(item);
								} else
									isEmptyComboBox = true;
							else
								isEmptyComboBox = true;
						}
						if (!isEmptyComboBox) {
							boolean isDuplicate = false;
							int index = 0;
							ArrayList<Item> duplicateChecker = new ArrayList<Item>();
							while (index < items.size() && !isDuplicate) {
								for (Item dc : duplicateChecker)
									if (items.get(index).getPriceTagID() == dc.getPriceTagID()) {
										isDuplicate = true;
										break;
									}
								duplicateChecker.add(items.get(index));
								index++;
							}
							if (!isDuplicate) {
								boolean needPriceDetails = false;
								if (tempObj.getClass().toString().equals("class Frame.Sale")) {
									((Frame.Sale) tempObj).setItems(items);
									((Frame.Sale) tempObj).setInventoryNo(itemDetailsF.getInventoryNo());
									((Frame.Sale) tempObj).getItemTF().setText("" + items.size());
									needPriceDetails = true;
								} else if (tempObj.getClass().toString().equals("class Frame.Import")) {
									((Frame.Import) tempObj).setItems(items);
									((Frame.Import) tempObj).getItemTF().setText("" + items.size());
									needPriceDetails = true;
								} else if (tempObj.getClass().toString().equals("class Frame.Purchase")) {
									((Frame.Purchase) tempObj).setItems(items);
									((Frame.Purchase) tempObj).getItemTF().setText("" + items.size());
									needPriceDetails = true;
								} else if (tempObj.getClass().toString().equals("class Frame.Damage")) {
									((Frame.Damage) tempObj).setInventoryNo(itemDetailsF.getInventoryNo());
									((Frame.Damage) tempObj).setItems(items);
									((Frame.Damage) tempObj).getItemTF().setText("" + items.size());
								} else if (tempObj.getClass().toString().equals("class Frame.Promotion")) {
									((Frame.Promotion) tempObj).setInventoryNo(itemDetailsF.getInventoryNo());
									((Frame.Promotion) tempObj).setItems(items);
									((Frame.Promotion) tempObj).getItemTF().setText("" + items.size());
								}
								if (needPriceDetails) {
									PriceDetails priceDetailsF = new PriceDetails(new AddPanel());
									priceDetailsF.setNames(names);
									priceDetailsF.setVisible(true);
									_GUIGlobals.activeFrames++;
									ActionListener addAL = new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent arg0) {
											// TODO Auto-generated method stub
											double total = 0;
											ArrayList<JLabel> quantityLs = priceDetailsF.getQuantityLs();
											ArrayList<JFormattedTextField> priceFTFs = priceDetailsF.getPriceFTFs();
											if (!itemDetailsF.getIsSpecific()) {
												for (int i = 0; i < quantityLs.size(); i++)
													total += (Integer.parseInt(quantityLs.get(i).getText())
															* _GUIGlobals.parseMoney(priceFTFs.get(i).getText()));
												priceDetailsF.getAmountFTF().setValue(	
														_GUIGlobals.parseMoney(priceDetailsF.getAmountFTF().getText())
																+ total);
											} else {
												for (int i = 0; i < itemCBs.size(); i++) {
													String itemString = (String) itemCBs.get(i).getSelectedItem();
													if (itemString != null)
														if (!itemString.equals("")) {
															Product product = _GUIGlobals.parseProduct(itemString);
															ProductTag productTag = new ProductTag(
																	AppLogic.PriceTags.max() + 1,
																	_GUIGlobals.parseMoney(priceFTFs.get(i).getText())
																			/ product.getPiecesPerCarton(),
																	product.getName(), product.getCode());
															AppLogic.ProductTags.add(productTag);
														}
												}
											}
											priceDetailsF.dispose();
											_GUIGlobals.activeFrames--;
										}
									};
									ActionListener closeAL = new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent arg0) {
											// TODO Auto-generated method stub
											priceDetailsF.dispose();
											_GUIGlobals.activeFrames--;
										}
									};
									((RectangularButton) priceDetailsF.getFunctionP().getComponent(0))
											.addActionListener(addAL);
									((RectangularButton) priceDetailsF.getFunctionP().getComponent(1))
											.addActionListener(closeAL);
									itemDetailsF.dispose();
									_GUIGlobals.activeFrames--;
								}
							} else
								new Notification("Please remove any duplicate item.");
						} else
							new Notification("Please select an item.");
					} else
						new Notification("Please select an item.");
				}
			};
			ActionListener closeAL = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					itemDetailsF.dispose();
					_GUIGlobals.activeFrames--;
				}
			};
			((RectangularButton) itemDetailsF.getFunctionP().getComponent(0)).addActionListener(addAL);
			((RectangularButton) itemDetailsF.getFunctionP().getComponent(1)).addActionListener(closeAL);
			loadInventoryCB(itemDetailsF.getInventoryCB(), itemDetailsF.getInventoryNo());
			itemDetailsF.setVisible(true);
			_GUIGlobals.activeFrames++;
			break;
		}
		case "OPEN LETTERHEAD": {
			String licenseInitials = LoadSelections.previousSelection2.substring(0,
					LoadSelections.previousSelection2.indexOf(" ["));
			File letterhead = new File(
					Main.appDirectory + "\\Raw Materials\\Templates\\" + licenseInitials + "\\letterhead.docx");
			if (letterhead.exists())
				try {
					Desktop.getDesktop().open(letterhead);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					new Notification("Letterhead could not be opened.");
				}
			break;
		}
		}
	}
}
