package AppLogic;

import java.sql.Date;
import java.util.ArrayList;

import Core.PaymentVoucher;

public class PaymentVouchers {
	private static ArrayList<PaymentVoucher> paymentVouchers = new ArrayList<PaymentVoucher>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<PaymentVoucher> list) {
		paymentVouchers = list;
	}

	public static ArrayList<PaymentVoucher> getList() {
		return paymentVouchers;
	}

	public static void deleteList() {
		paymentVouchers = new ArrayList<PaymentVoucher>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.PaymentVoucherDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.PaymentVoucherDatabase.save();
	}

	public static boolean add(PaymentVoucher paymentVoucher) {
		if (indexOf(paymentVoucher.getNumber()) == -1)
			return paymentVouchers.add(paymentVoucher);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= paymentVouchers.size() || index == -1)
			return false;
		else
			return (paymentVouchers.remove(index) != null);
	}

	public static boolean update(int number, Date date, String being, double amount, String paymentMethod,
			String paymentTo, int lpoNo, int transactionNo) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				paymentVouchers.get(index).setDate(date);
			if (being != null)
				paymentVouchers.get(index).setBeing(being);
			paymentVouchers.get(index).setAmount(amount);
			if (paymentMethod != null)
				paymentVouchers.get(index).setPaymentMethod(paymentMethod);
			if (paymentTo != null)
				paymentVouchers.get(index).setPaymentTo(paymentTo);
			paymentVouchers.get(index).setLpoNo(lpoNo);
			paymentVouchers.get(index).setTransactionNo(transactionNo);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < paymentVouchers.size(); i++) {
			if (paymentVouchers.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static PaymentVoucher get(int index) {
		if (index >= paymentVouchers.size() || index == -1)
			return null;
		else
			return paymentVouchers.get(index);
	}
}
