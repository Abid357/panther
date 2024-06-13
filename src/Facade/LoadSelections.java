package Facade;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import GUI.SelectionButton2;
import GUI.SelectionButton3;
import GUI._GUIGlobals;
import GUI.Main;

public class LoadSelections {
	private static JPanel panel2;
	private static JPanel panel3;
	public static String previousSelection1;
	public static String previousSelection2;

	public LoadSelections() {
		panel2 = Main.getPanel2();
		panel3 = Main.getPanel3();
	}

	public static void panel2(String selection) {
		if (_GUIGlobals.activeFrames == 0) {
			if (previousSelection1 != null)
				if (previousSelection1.equals(selection))
					return;
			previousSelection1 = selection;
			previousSelection2 = null;
			if (panel2.getComponentCount() != 0)
				panel2.removeAll();
			if (panel3.getComponentCount() != 0)
				panel3.removeAll();
			int buttonCount = 0;
			SelectionButton2[] buttons = null;
			if (selection != null)
				switch (selection) {
				case "TRANSACTION": {
					buttonCount = 15;
					buttons = new SelectionButton2[buttonCount];
					buttons[0] = new SelectionButton2("SALE");
					buttons[1] = new SelectionButton2("PURCHASE");
					buttons[2] = new SelectionButton2("MANUFACTURE");
					buttons[3] = new SelectionButton2("TRANSPORT");
					buttons[4] = new SelectionButton2("DAMAGE");
					buttons[5] = new SelectionButton2("INVESTMENT");
					buttons[6] = new SelectionButton2("PROFIT");
					buttons[7] = new SelectionButton2("PROMOTION");
					buttons[8] = new SelectionButton2("SALARY");
					buttons[9] = new SelectionButton2("RENT");
					buttons[10] = new SelectionButton2("RENEWAL");
					buttons[11] = new SelectionButton2("MAINTENANCE");
					buttons[12] = new SelectionButton2("DOMESTIC");
					buttons[13] = new SelectionButton2("LOAN");
					buttons[14] = new SelectionButton2("OTHER");
					break;
				}
				case "INVENTORY": {
					ArrayList<String> selections = new ArrayList<String>();
					ArrayList<Core.Warehouse> warehouses = AppLogic.Warehouses.getList();
					ArrayList<Core.Supplier> suppliers = AppLogic.Suppliers.getList();
					int extraButtons = 2;
					buttonCount = extraButtons;
					if (warehouses != null) {
						for (int i = 0; i < warehouses.size(); i++) {
							if (warehouses.get(i).getInventoryNo() != -1) {
								selections.add(GUI._GUIGlobals.formatWarehouse(warehouses.get(i)));
								buttonCount++;
							}
						}
					}
					if (suppliers != null)
						for (int i = 0; i < suppliers.size(); i++) {
							if (suppliers.get(i).getInventoryNo() != -1) {
								selections.add(suppliers.get(i).getName());
								buttonCount++;
							}
						}
					buttons = new SelectionButton2[buttonCount];
					for (int i = 0; i < selections.size(); i++) {
						buttons[i] = new SelectionButton2(selections.get(i));
						buttons[i].setFont(new Font("Century Gothic", Font.BOLD, 15));
					}
					buttons[buttonCount - extraButtons] = new SelectionButton2("ADD INVENTORY");
					extraButtons--;
					buttons[buttonCount - extraButtons] = new SelectionButton2("ADD WAREHOUSE");
					extraButtons--;
					break;
				}
				case "ENTITY": {
					buttonCount = 6;
					buttons = new SelectionButton2[buttonCount];
					buttons[0] = new SelectionButton2("SUPPLIER");
					buttons[1] = new SelectionButton2("EMPLOYEE/PARTNER");
					buttons[2] = new SelectionButton2("INVESTOR");
					buttons[3] = new SelectionButton2("DRIVER");
					buttons[4] = new SelectionButton2("LOANER");
					buttons[5] = new SelectionButton2("OTHER");
					break;
				}
				case "ITEM": {
					buttonCount = 3;
					buttons = new SelectionButton2[buttonCount];
					buttons[0] = new SelectionButton2("PRODUCT");
					buttons[1] = new SelectionButton2("MATERIAL");
					buttons[2] = new SelectionButton2("SERVICE");
					break;
				}
				case "PRODUCTION": {
					buttonCount = 3;
					buttons = new SelectionButton2[buttonCount];
					buttons[0] = new SelectionButton2("CURRENT");
					buttons[1] = new SelectionButton2("FINISHED");
					buttons[2] = new SelectionButton2("START NEW");
					break;
				}
				case "BANK": {
					ArrayList<Core.Bank> banks = AppLogic.Banks.getList();
					int extraButtons = 1;
					buttonCount = extraButtons + banks.size();
					buttons = new SelectionButton2[buttonCount];
					for (int i = 0; i < banks.size(); i++) {
						buttons[i] = new SelectionButton2(GUI._GUIGlobals.formatBank(banks.get(i)));
						buttons[i].setFont(new Font("Century Gothic", Font.BOLD, 15));
					}
					buttons[buttonCount - extraButtons] = new SelectionButton2("ADD BANK");
					extraButtons--;
					break;
				}
				case "LICENSE": {
					ArrayList<Core.License> licenses = AppLogic.Licenses.getList();
					int extraButtons = 1;
					buttonCount = extraButtons + licenses.size();
					buttons = new SelectionButton2[buttonCount];
					for (int i = 0; i < licenses.size(); i++) {
						buttons[i] = new SelectionButton2(GUI._GUIGlobals.formatLicense(licenses.get(i)));
						buttons[i].setFont(new Font("Century Gothic", Font.BOLD, 15));
					}
					buttons[buttonCount - extraButtons] = new SelectionButton2("ADD LICENSE");
					extraButtons--;
					break;
				}
				case "VEHICLE": {
					ArrayList<Core.Vehicle> vehicles = AppLogic.Vehicles.getList();
					int extraButtons = 3;
					buttonCount = extraButtons + vehicles.size();
					buttons = new SelectionButton2[buttonCount];
					for (int i = 0; i < vehicles.size(); i++) {
						buttons[i] = new SelectionButton2(_GUIGlobals.formatVehicle(vehicles.get(i)));
						buttons[i].setFont(new Font("Century Gothic", Font.BOLD, 15));
					}
					buttons[buttonCount - extraButtons] = new SelectionButton2("ADD VEHICLE");
					extraButtons--;
					buttons[buttonCount - extraButtons] = new SelectionButton2("ADD MAINTENANCE");
					extraButtons--;
					buttons[buttonCount - extraButtons] = new SelectionButton2("ADD RENT");
					extraButtons--;
					break;
				}
				case "FORM": {
					buttonCount = 5;
					buttons = new SelectionButton2[buttonCount];
					buttons[0] = new SelectionButton2("INVOICE");
					buttons[1] = new SelectionButton2("PROFORMA INVOICE");
					buttons[2] = new SelectionButton2("PURCHASE ORDER");
					buttons[3] = new SelectionButton2("RECEIPT VOUCHER");
					buttons[4] = new SelectionButton2("PAYMENT VOUCHER");
					break;
				}
				case "REPORT":
					buttonCount = 6;
					buttons = new SelectionButton2[buttonCount];
					buttons[0] = new SelectionButton2("DAILY REPORT");
					buttons[1] = new SelectionButton2("INVENTORY");
					buttons[2] = new SelectionButton2("BANK");
					buttons[3] = new SelectionButton2("PRODUCTION");
					buttons[4] = new SelectionButton2("SALES");
					buttons[5] = new SelectionButton2("OTHER");
					break;
				}
			if (((GridLayout) panel2.getLayout()).getRows() < buttonCount)
				panel2.setLayout(new GridLayout(buttonCount, 0));
			else
				panel2.setLayout(new GridLayout(10, 0));
			TableViewer.populate(selection);
		}
	}

	public static void panel3(String selection) {
		if (_GUIGlobals.activeFrames == 0) {
			if (selection.equals("OTHER"))
				selection += " " + previousSelection1;
			if (previousSelection2 != null)
				if (previousSelection2.equals(selection))
					return;
			previousSelection2 = selection;
			if (panel3.getComponentCount() != 0)
				panel3.removeAll();
			int buttonCount = 0;
			SelectionButton3[] buttons = null;
			switch (selection) {
			case "SALE": {
				buttonCount = 3;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				buttons[1] = new SelectionButton3("ADD STOCK");
				break;
			}
			case "PURCHASE": {
				buttonCount = 2;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD LOCAL");
				buttons[1] = new SelectionButton3("ADD IMPORT");
				break;
			}
			case "MANUFACTURE": {
				buttonCount = 3;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				buttons[1] = new SelectionButton3("ADD MATERIAL");
				buttons[2] = new SelectionButton3("ADD SERVICE");
				break;
			}
			case "TRANSPORT": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "DAMAGE": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "INVESTMENT": {
				buttonCount = 2;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				buttons[1] = new SelectionButton3("ADD INVESTOR");
				break;
			}
			case "PROFIT": {
				buttonCount = 2;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				buttons[1] = new SelectionButton3("ADD INVESTOR");
				break;
			}
			case "PROMOTION": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "SALARY": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "RENT": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "RENEWAL": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "MAINTENANCE": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "DOMESTIC": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "LOAN": {
				buttonCount = 2;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				buttons[1] = new SelectionButton3("OLD LOANS");
				break;
			}
			case "OTHER TRANSACTION": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + previousSelection1);
				break;
			}
			case "ADD INVENTORY": {
				Functions.create(selection);
				break;
			}
			case "ADD WAREHOUSE": {
				Functions.create(selection);
				break;
			}
			case "SUPPLIER": {
				buttonCount = 3;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				buttons[1] = new SelectionButton3("ADD MATERIAL");
				buttons[2] = new SelectionButton3("ADD SERVICE");
				break;
			}
			case "EMPLOYEE/PARTNER": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "INVESTOR": {
				buttonCount = 2;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				buttons[1] = new SelectionButton3("ADD INVESTMENT");
				break;
			}
			case "DRIVER": {
				buttonCount = 2;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				buttons[1] = new SelectionButton3("ADD VEHICLE");
				break;
			}
			case "LOANER": {
				buttonCount = 2;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				buttons[1] = new SelectionButton3("ADD LOAN");
				break;
			}
			case "OTHER ENTITY": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + previousSelection1);
				break;
			}
			case "PRODUCT": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "MATERIAL": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "SERVICE": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "CURRENT": {
				Functions.create(selection);
				break;
			}
			case "FINISHED": {
				Functions.create(selection);
				break;
			}
			case "START NEW": {
				Functions.create(selection);
				break;
			}
			case "ADD BANK": {
				Functions.create(selection);
				break;
			}
			case "ADD VEHICLE": {
				Functions.create(selection);
				break;
			}
			case "ADD MAINTENANCE": {
				Functions.create(selection);
				break;
			}
			case "ADD RENT": {
				Functions.create(selection);
				break;
			}
			case "ADD LICENSE": {
				Functions.create(selection);
				break;
			}
			case "INVOICE": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "PROFORMA INVOICE": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "PURCHASE ORDER": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "RECEIPT VOUCHER": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			case "PAYMENT VOUCHER": {
				buttonCount = 1;
				buttons = new SelectionButton3[buttonCount];
				buttons[0] = new SelectionButton3("ADD " + selection);
				break;
			}
			default:
				if (previousSelection1.equals("INVENTORY")) {
					buttonCount = 4;
					buttons = new SelectionButton3[buttonCount];
					buttons[0] = new SelectionButton3("ADD TRANSACTION");
					buttons[1] = new SelectionButton3("ADD STOCK");
					buttons[2] = new SelectionButton3("EDIT INVENTORY");
					buttons[3] = new SelectionButton3("DELETE INVENTORY");
				} else if (previousSelection1.equals("BANK")) {
					buttonCount = 4;
					buttons = new SelectionButton3[buttonCount];
					buttons[0] = new SelectionButton3("ADD TRANSACTION");
					buttons[1] = new SelectionButton3("ADD CHEQUE");
					buttons[2] = new SelectionButton3("EDIT BANK");
					buttons[3] = new SelectionButton3("DELETE BANK");
				} else if (previousSelection1.equals("LICENSE")) {
					buttonCount = 6;
					buttons = new SelectionButton3[buttonCount];
					buttons[0] = new SelectionButton3("ADD RENEWAL");
					buttons[1] = new SelectionButton3("ADD RENT");
					buttons[2] = new SelectionButton3("OPEN LETTERHEAD");
					buttons[3] = new SelectionButton3("CHANGE DATES");
					buttons[4] = new SelectionButton3("EDIT LICENSE");
					buttons[5] = new SelectionButton3("DELETE LICENSE");
				} else if (previousSelection1.equals("VEHICLE")) {
					buttonCount = 4;
					buttons = new SelectionButton3[buttonCount];
					buttons[0] = new SelectionButton3("ADD RENT");
					buttons[1] = new SelectionButton3("CHANGE DRIVER");
					buttons[2] = new SelectionButton3("EDIT VEHICLE");
					buttons[3] = new SelectionButton3("DELETE VEHICLE");
				}
				break;
			}
			if (((GridLayout) panel3.getLayout()).getRows() < buttonCount)
				panel3.setLayout(new GridLayout(buttonCount, 0));
			else
				panel3.setLayout(new GridLayout(10, 0));
			TableViewer.populate(selection);
		}
	}
}
