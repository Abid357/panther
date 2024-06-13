package AppLogic;

import java.sql.Date;
import java.util.ArrayList;

import Core.Item;
import Core.ProformaInvoice;

public class ProformaInvoices {
	private static ArrayList<ProformaInvoice> proformaInvoices = new ArrayList<ProformaInvoice>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<ProformaInvoice> list) {
		proformaInvoices = list;
	}

	public static ArrayList<ProformaInvoice> getList() {
		return proformaInvoices;
	}

	public static void deleteList() {
		proformaInvoices = new ArrayList<ProformaInvoice>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.ProformaInvoiceDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.ProformaInvoiceDatabase.save();
	}

	public static boolean add(ProformaInvoice proformaInvoice) {
		if (indexOf(proformaInvoice.getNumber()) == -1)
			return proformaInvoices.add(proformaInvoice);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= proformaInvoices.size() || index == -1)
			return false;
		else
			return (proformaInvoices.remove(index) != null);
	}

	public static boolean update(int number, Date issueDate, Date validity, String shippingLocation, String shippingMethod,
			String shippingTerms, String paymentTerms, String notes, String consignee, String lpoNo,
			ArrayList<Item> items) {
		int index = indexOf(number);
		if (index != -1) {
			if (issueDate != null)
				proformaInvoices.get(index).setIssueDate(issueDate);
			if (validity != null)
				proformaInvoices.get(index).setValidity(validity);
			if (shippingLocation != null)
				proformaInvoices.get(index).setShippingLocation(shippingLocation);
			if (shippingMethod != null)
				proformaInvoices.get(index).setShippingMethod(shippingMethod);
			if (shippingTerms != null)
				proformaInvoices.get(index).setShippingTerms(shippingTerms);
			if (paymentTerms != null)
				proformaInvoices.get(index).setPaymentTerms(paymentTerms);
			if (notes != null)
				proformaInvoices.get(index).setNotes(notes);
			if (consignee != null)
				proformaInvoices.get(index).setConsignee(consignee);
			if (lpoNo != null)
				proformaInvoices.get(index).setLpoNo(lpoNo);
			if (items != null)
				proformaInvoices.get(index).setItems(items);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < proformaInvoices.size(); i++) {
			if (proformaInvoices.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static ProformaInvoice get(int index) {
		if (index >= proformaInvoices.size() || index == -1)
			return null;
		else
			return proformaInvoices.get(index);
	}
}
