package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Import;
import Core.Item;
import Core.Production;
import Core.Sale;
import Core.Transport;

public class Transports {
	private static ArrayList<Transport> transports = new ArrayList<Transport>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Transport> list) {
		transports = list;
	}

	public static ArrayList<Transport> getList() {
		return transports;
	}

	public static void deleteList() {
		transports = new ArrayList<Transport>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.TransportDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.TransportDatabase.save();
	}

	public static boolean add(Transport transport) {
		if (indexOf(transport.getNumber()) == -1) {
			if (AppLogic.Transactions.add(transport))
				return transports.add(transport);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= transports.size() || index == -1)
			return false;
		else
			return (transports.remove(index) != null);
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items, String toLocation,
			String fromLocation, int inventoryNo) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				transports.get(index).setDate(date);
			if (time != null)
				transports.get(index).setTime(time);
			transports.get(index).setAmount(amount);
			transports.get(index).setType(type);
			if (description != null)
				transports.get(index).setDescription(description);
			if (bankAccountNo != null)
				transports.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				transports.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				transports.get(index).setEntityName(entityName);
			if (items != null)
				transports.get(index).setItems(items);
			if (toLocation != null)
				transports.get(index).setToLocation(toLocation);
			if (fromLocation != null)
				transports.get(index).setFromLocation(fromLocation);
			transports.get(index).setInventoryNo(inventoryNo);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < transports.size(); i++) {
			if (transports.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Transport get(int index) {
		if (index >= transports.size() || index == -1)
			return null;
		else
			return transports.get(index);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public static ArrayList<Transport> getPureList(){
		ArrayList<Transport> pureTransports = new ArrayList<Transport>();
		for (Transport t : transports)
			pureTransports.add(t);
		ArrayList<Import> imports = AppLogic.Imports.getList();
		ArrayList<Sale> sales = AppLogic.Sales.getList();
		ArrayList<Production> productions = AppLogic.Productions.getList();
		for (Import i : imports) 
			if (pureTransports.contains(indexOf(i.getTransportTransactionNo())))
				pureTransports.remove(i.getTransportTransactionNo());
		for (Sale s : sales) 
			if (pureTransports.contains(indexOf(s.getTransportTransactionNo())))
				pureTransports.remove(s.getTransportTransactionNo());
		for (Production p : productions) 
			if (pureTransports.contains(indexOf(p.getTransportTransactionNo())))
				pureTransports.remove(p.getTransportTransactionNo());
		return pureTransports;
	}
}
