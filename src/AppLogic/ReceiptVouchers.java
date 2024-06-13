package AppLogic;

import java.sql.Date;
import java.util.ArrayList;

import Core.ReceiptVoucher;

public class ReceiptVouchers {
	private static ArrayList<ReceiptVoucher> receiptVouchers = new ArrayList<ReceiptVoucher>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<ReceiptVoucher> list) {
		receiptVouchers = list;
	}

	public static ArrayList<ReceiptVoucher> getList() {
		return receiptVouchers;
	}

	public static void deleteList() {
		receiptVouchers = new ArrayList<ReceiptVoucher>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.ReceiptVoucherDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.ReceiptVoucherDatabase.save();
	}

	public static boolean add(ReceiptVoucher receiptVoucher) {
		if (indexOf(receiptVoucher.getNumber()) == -1)
			return receiptVouchers.add(receiptVoucher);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= receiptVouchers.size() || index == -1)
			return false;
		else
			return (receiptVouchers.remove(index) != null);
	}

	public static boolean update(int number, Date date, String being, double amount, String receiptMethod,
			String receiptTo, int invoiceNo, int transactionNo) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				receiptVouchers.get(index).setDate(date);
			if (being != null)
				receiptVouchers.get(index).setBeing(being);
			receiptVouchers.get(index).setAmount(amount);
			if (receiptMethod != null)
				receiptVouchers.get(index).setReceiptMethod(receiptMethod);
			if (receiptTo != null)
				receiptVouchers.get(index).setReceiptTo(receiptTo);
			receiptVouchers.get(index).setinvoiceNo(invoiceNo);
			receiptVouchers.get(index).setTransactionNo(transactionNo);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < receiptVouchers.size(); i++) {
			if (receiptVouchers.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static ReceiptVoucher get(int index) {
		if (index >= receiptVouchers.size() || index == -1)
			return null;
		else
			return receiptVouchers.get(index);
	}

	public static ArrayList<Integer> searchByInvoiceNo(int invoiceNo) {
		ArrayList<Integer> receiptVoucherNos = new ArrayList<Integer>();
		if (invoiceNo != -1) {
			for (int i = 0; i < receiptVouchers.size(); i++)
				if (receiptVouchers.get(i).getinvoiceNo() == invoiceNo)
					receiptVoucherNos.add(receiptVouchers.get(i).getNumber());
		}
		return receiptVoucherNos;
	}
}
