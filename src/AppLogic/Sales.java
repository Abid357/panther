package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Item;
import Core.Sale;

public class Sales {
	private static ArrayList<Sale> sales = new ArrayList<Sale>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Sale> list) {
		sales = list;
	}

	public static ArrayList<Sale> getList() {
		return sales;
	}

	public static void deleteList() {
		sales = new ArrayList<Sale>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.SaleDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.SaleDatabase.save();
	}

	public static boolean add(Sale sale) {
		if (indexOf(sale.getNumber()) == -1) {
			if (AppLogic.Transactions.add(sale))
				return sales.add(sale);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= sales.size() || index == -1)
			return false;
		else
			return (sales.remove(index) != null);
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items, int transportTransactionNo,
			int inventoryNo, int invoiceNo) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				sales.get(index).setDate(date);
			if (time != null)
				sales.get(index).setTime(time);
			sales.get(index).setAmount(amount);
			sales.get(index).setType(type);
			if (description != null)
				sales.get(index).setDescription(description);
			if (bankAccountNo != null)
				sales.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				sales.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				sales.get(index).setEntityName(entityName);
			if (items != null)
				sales.get(index).setItems(items);
			sales.get(index).setTransportTransactionNo(transportTransactionNo);
			sales.get(index).setInventoryNo(inventoryNo);
			sales.get(index).setInvoiceNo(invoiceNo);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < sales.size(); i++) {
			if (sales.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Sale get(int index) {
		if (index >= sales.size() || index == -1)
			return null;
		else
			return sales.get(index);
	}
}
