package AppLogic;

import java.sql.Date;
import java.util.ArrayList;

import Core.Item;
import Core.Invoice;

public class Invoices {
	private static ArrayList<Invoice> invoices = new ArrayList<Invoice>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Invoice> list) {
		invoices = list;
	}

	public static ArrayList<Invoice> getList() {
		return invoices;
	}

	public static void deleteList() {
		invoices = new ArrayList<Invoice>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.InvoiceDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.InvoiceDatabase.save();
	}

	public static boolean add(Invoice invoice) {
		if (indexOf(invoice.getNumber()) == -1)
			return invoices.add(invoice);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= invoices.size() || index == -1)
			return false;
		else
			return (invoices.remove(index) != null);
	}

	public static int max() {
		int max = -1;
		if (!invoices.isEmpty())
			max = invoices.get(0).getNumber();
		for (int i = 1; i < invoices.size(); i++)
			if (invoices.get(i).getNumber() > max)
				max = invoices.get(i).getNumber();
		return max;
	}
	
	public static boolean update(int number, Date date, String name, String paymentTerms, int piNo, ArrayList<Item> items,
			ArrayList<Double> rates) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				invoices.get(index).setDate(date);
			if (name != null)
				invoices.get(index).setName(name);
			if (paymentTerms != null)
				invoices.get(index).setPaymentTerms(paymentTerms);
			invoices.get(index).setPiNo(piNo);
			if (items != null)
				invoices.get(index).setItems(items);
			if (rates != null)
				invoices.get(index).setRates(rates);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < invoices.size(); i++) {
			if (invoices.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Invoice get(int index) {
		if (index >= invoices.size() || index == -1)
			return null;
		else
			return invoices.get(index);
	}
}
