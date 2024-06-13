package Facade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class _FacadeGlobals implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static HashMap<String, String> pocketOptions;

	public _FacadeGlobals() {
		pocketOptions = new HashMap<String, String>();
		pocketOptions.put("Sale", "Company");
		pocketOptions.put("Import", "Company");
		pocketOptions.put("Local Purchase", "Company");
		pocketOptions.put("Manufacture", "Company");
		pocketOptions.put("Loan", "Company");
		pocketOptions.put("Rent", "Company");
		pocketOptions.put("Renewal Fee", "Company");
		pocketOptions.put("Damage", "Company");
		pocketOptions.put("Profit", "Company");
		pocketOptions.put("Transport", "Company");
		pocketOptions.put("Salary", "Company");
		pocketOptions.put("Promotion", "Company");
		pocketOptions.put("Maintenance", "Company");
		pocketOptions.put("Investment", "Company");
		pocketOptions.put("Domestic", "Company");
		pocketOptions.put("Other", "Company");
	}

	public _FacadeGlobals(HashMap<String, String> pocketOptions) {
		_FacadeGlobals.pocketOptions = pocketOptions;
	}

	public static boolean save(HashMap<String, String> pocketOptions) {
		if (pocketOptions == null)
			return false;
		else
			return update(pocketOptions);
	}

	public static boolean update(HashMap<String, String> pocketOptions) {
		Iterator<Entry<String, String>> iterator = pocketOptions.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entrySet = (Entry<String, String>) iterator.next();
			_FacadeGlobals.pocketOptions.put(entrySet.getKey(), entrySet.getValue());
		}
		return true;
	}
	
	public static String getPocket(String pocket) {
		return pocketOptions.get(pocket);
	}
}
