package AppLogic;

import java.sql.Date;
import java.util.ArrayList;

import Core.Item;
import Core.LocalPurchaseOrder;

public class LocalPurchaseOrders {
	private static ArrayList<LocalPurchaseOrder> localPurchaseOrders = new ArrayList<LocalPurchaseOrder>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<LocalPurchaseOrder> list) {
		localPurchaseOrders = list;
	}

	public static ArrayList<LocalPurchaseOrder> getList() {
		return localPurchaseOrders;
	}

	public static void deleteList() {
		localPurchaseOrders = new ArrayList<LocalPurchaseOrder>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.LocalPurchaseOrderDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.LocalPurchaseOrderDatabase.save();
	}

	public static boolean add(LocalPurchaseOrder localPurchaseOrder) {
		if (indexOf(localPurchaseOrder.getNumber()) == -1)
			return localPurchaseOrders.add(localPurchaseOrder);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= localPurchaseOrders.size() || index == -1)
			return false;
		else
			return (localPurchaseOrders.remove(index) != null);
	}

	public static boolean update(int number, Date issueDate, String invoiceNo, String sendToName,
			ArrayList<Item> items) {
		int index = indexOf(number);
		if (index != -1) {
			if (issueDate != null)
				localPurchaseOrders.get(index).setIssueDate(issueDate);
			if (invoiceNo != null)
				localPurchaseOrders.get(index).setinvoiceNo(invoiceNo);
			if (sendToName != null)
				localPurchaseOrders.get(index).setSendToName(sendToName);
			if (items != null)
				localPurchaseOrders.get(index).setItems(items);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < localPurchaseOrders.size(); i++) {
			if (localPurchaseOrders.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static LocalPurchaseOrder get(int index) {
		if (index >= localPurchaseOrders.size() || index == -1)
			return null;
		else
			return localPurchaseOrders.get(index);
	}
}
