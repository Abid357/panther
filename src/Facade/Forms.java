package Facade;

import java.util.ArrayList;

import Core.Invoice;
import Core.Item;
import Core.Pocket;
import Core.Sale;
import GUI.Log;
import GUI.Logger;

public class Forms {
	public static boolean addInvoice(Invoice invoice) {
		if (AppLogic.Invoices.add(invoice)) {
			Logger.add(new Log("Invoice", "Number", invoice.getNumber(), "Customer", invoice.getName(), Log.ADD));
			TableViewer.update();
			AppLogic.Invoices.setDirty(true);
			return true;
		} else
			return false;
	}
}
