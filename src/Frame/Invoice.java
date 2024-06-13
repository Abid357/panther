/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import com.toedter.calendar.JDateChooser;

import Core.Item;
import Core.Product;
import Core.ProductTag;
import Facade.Functions;

import javax.swing.JFrame;

import GUI._GUIGlobals;

/**
 *
 * @author Abid-Temp
 */
public class Invoice extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel invoiceNoL;
	private JDateChooser dateDC;
	private JComboBox<String> nameCB;
	private JTextField paymentTermsTF;
	private JComboBox<String> proformaInvoiceCB;
	private ArrayList<JComboBox<String>> itemCBs;
	private ArrayList<JSpinner> quantitySs;
	private ArrayList<JFormattedTextField> rateFTFs;
	private ArrayList<JFormattedTextField> amountFTFs;
	private JPanel functionP;
	private static BufferedImage img;
	private Date date;
	private JLabel backgroundL;
	private JComboBox<String> inventoryCB;
	private int inventoryNo;

	public void setBackground(JLabel background) {
		img = null;
		try {
			img = ImageIO.read(new File(_GUIGlobals.directory + "\\Raw Materials\\Templates\\NHGT\\invoice.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		background.setIcon(imageIcon);
	}

	public Date getDate() {
		return date;
	}

	public JTextField getPaymentTermsTF() {
		return paymentTermsTF;
	}

	public JComboBox<String> getProformaInvoiceCB() {
		return proformaInvoiceCB;
	}

	public JLabel getInvoiceNoL() {
		return invoiceNoL;
	}

	public JDateChooser getDateDC() {
		return dateDC;
	}

	public JComboBox<String> getNameCB() {
		return nameCB;
	}

	public ArrayList<JComboBox<String>> getItemCBs() {
		return itemCBs;
	}

	public ArrayList<JSpinner> getQuantitySs() {
		return quantitySs;
	}

	public ArrayList<JFormattedTextField> getRateFTFs() {
		return rateFTFs;
	}

	public ArrayList<JFormattedTextField> getAmountFTFs() {
		return amountFTFs;
	}

	public JComboBox<String> getInventoryCB() {
		return inventoryCB;
	}

	public int getInventoryNo() {
		return inventoryNo;
	}

	public JPanel getFunctionP() {
		return functionP;
	}

	public static Dimension getInvoiceNoLSize() {
		double wfactor = 15.03759398;
		double hfactor = 15.4109589;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getInvoiceNoLPos() {
		double wfactor = 18.34862385;
		double hfactor = 5.853846154;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getDateDCSize() {
		double wfactor = 10;
		double hfactor = 24.45652174;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getDateDCPos() {
		double wfactor = 3.100775194;
		double hfactor = 7.705479452;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getNameCBSize() {
		double wfactor = 3.091190108;
		double hfactor = 38.79310345;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getNameCBPos() {
		double wfactor = 13.88888889;
		double hfactor = 5.208333333;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getPaymentTermsTFSize() {
		double wfactor = 10.47120419;
		double hfactor = 38.79310345;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getPaymentTermsTFPos() {
		double wfactor = 9.345794393;
		double hfactor = 4.197761194;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getProformaInvoiceCBSize() {
		double wfactor = 12.65822785;
		double hfactor = 38.79310345;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getProformaInvoiceCBPos() {
		double wfactor = 2.971768202;
		double hfactor = 4.197761194;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getItemCBSize() {
		double wfactor = 4.545454545;
		double hfactor = 28.125;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getItemCBPos() {
		double wfactor = 14.92537313;
		double hfactor = 2.945026178;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getQuantitySSize() {
		double wfactor = 25;
		double hfactor = 28.125;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getQuantitySPos() {
		double wfactor = 3.460207612;
		double hfactor = 2.945026178;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getRateFTFSize() {
		double wfactor = 14.38848921;
		double hfactor = 28.125;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getRateFTFPos() {
		double wfactor = 3.03030303;
		double hfactor = 2.945026178;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getAmountFTFSize() {
		double wfactor = 12.12121212;
		double hfactor = 28.125;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getAmountFTFPos() {
		double wfactor = 2.509410289;
		double hfactor = 2.945026178;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getInventoryCBSize() {
		double wfactor = 13.61702128;
		double hfactor = 25.11627907;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getInventoryCBPos() {
		double wfactor = 105.3333333;
		double hfactor = 5.625;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor) + img.getWidth(),
				(int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public void setEditable(boolean isEnabled) {
		getNameCB().setEnabled(isEnabled);
		getDateDC().setEnabled(isEnabled);
		if (getItemCBs() != null)
			for (JComboBox<String> cb : getItemCBs())
				cb.setEnabled(isEnabled);
		if (getQuantitySs() != null)
			for (JSpinner s : getQuantitySs())
				s.setEnabled(isEnabled);
		if (getRateFTFs() != null)
			for (JTextField tf : getRateFTFs())
				tf.setEnabled(isEnabled);
		if (getAmountFTFs() != null)
			for (JTextField tf : getAmountFTFs())
				tf.setEnabled(isEnabled);
	}

	/**
	 * Creates new form Transaction
	 */
	public Invoice(JPanel panel) {
		functionP = panel;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		invoiceNoL = new JLabel();
		nameCB = new JComboBox<String>();
		dateDC = new JDateChooser();
		paymentTermsTF = new JTextField();
		proformaInvoiceCB = new JComboBox<String>();
		itemCBs = new ArrayList<JComboBox<String>>();
		quantitySs = new ArrayList<JSpinner>();
		rateFTFs = new ArrayList<JFormattedTextField>();
		amountFTFs = new ArrayList<JFormattedTextField>();
		backgroundL = new JLabel();
		inventoryCB = new JComboBox<String>();
		inventoryNo = -1;

		setUndecorated(true);
		setTitle("Invoice");
		requestFocus();
		getContentPane().setLayout(new AbsoluteLayout());

		invoiceNoL.setForeground(Color.RED);
		invoiceNoL.setFont(new Font("Calibri Light", 0, _GUIGlobals.getFontSize_Forms()));
		getContentPane().add(invoiceNoL, new AbsoluteConstraints(getInvoiceNoLSize().width, getInvoiceNoLSize().height,
				getInvoiceNoLPos().width, getInvoiceNoLPos().height));

		dateDC.setForeground(Color.BLACK);
		dateDC.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		dateDC.setDate(Calendar.getInstance().getTime());
		dateDC.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getPropertyName().equals("date")) {
					date = new Date(dateDC.getDate().getTime());
				}
			}
		});
		getContentPane().add(dateDC, new AbsoluteConstraints(getDateDCPos().width, getDateDCPos().height,
				getDateDCSize().width, getDateDCSize().height));

		date = new Date(dateDC.getDate().getTime());

		nameCB.setBackground(Color.WHITE);
		nameCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_24())); // NOI18N
		nameCB.setForeground(Color.BLACK);
		nameCB.setBorder(null);
		getContentPane().add(nameCB, new AbsoluteConstraints(getNameCBPos().width, getNameCBPos().height,
				getNameCBSize().width, getNameCBSize().height));

		paymentTermsTF.setBackground(Color.WHITE);
		paymentTermsTF.setForeground(Color.BLACK);
		paymentTermsTF.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_24())); // NOI18N
		paymentTermsTF.setBorder(null);
		getContentPane().add(paymentTermsTF, new AbsoluteConstraints(getPaymentTermsTFPos().width,
				getPaymentTermsTFPos().height, getPaymentTermsTFSize().width, getPaymentTermsTFSize().height));

		proformaInvoiceCB.setBackground(Color.WHITE);
		proformaInvoiceCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_24())); // NOI18N
		proformaInvoiceCB.setForeground(Color.BLACK);
		proformaInvoiceCB.setBorder(null);
		Functions.loadProformaInvoiceCB(proformaInvoiceCB, -1);
		getContentPane().add(proformaInvoiceCB, new AbsoluteConstraints(getProformaInvoiceCBPos().width,
				getProformaInvoiceCBPos().height, getProformaInvoiceCBSize().width, getProformaInvoiceCBSize().height));

		JFormattedTextField totalAmountFTF = new JFormattedTextField();
		totalAmountFTF.setForeground(Color.BLACK);
		totalAmountFTF.setBackground(Color.WHITE);
		totalAmountFTF.setFont(new Font("Century Gothic", Font.BOLD, _GUIGlobals.getFontSize_18()));
		totalAmountFTF.setFormatterFactory(
				new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("###,###,###,##0.00"))));
		totalAmountFTF.setHorizontalAlignment(JTextField.CENTER);
		totalAmountFTF.setBorder(null);
		getContentPane().add(totalAmountFTF,
				new AbsoluteConstraints(getAmountFTFPos().width,
						getAmountFTFPos().height + (getAmountFTFSize().height) * 15, getAmountFTFSize().width,
						getAmountFTFSize().height));

		for (int i = 0; i < 15; i++) {
			JComboBox<String> itemCB = new JComboBox<String>();
			itemCB.setBackground(Color.WHITE);
			itemCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_24())); // NOI18N
			itemCB.setForeground(Color.BLACK);
			getContentPane().add(itemCB,
					new AbsoluteConstraints(getItemCBPos().width, getItemCBPos().height + (getItemCBSize().height) * i,
							getItemCBSize().width, getItemCBSize().height));
			itemCBs.add(itemCB);

			JSpinner quantityS = new JSpinner();
			quantityS.setForeground(Color.BLACK);
			quantityS.setBackground(Color.WHITE);
			quantityS.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18()));
			getContentPane().add(quantityS,
					new AbsoluteConstraints(getQuantitySPos().width,
							getQuantitySPos().height + (getQuantitySSize().height) * i, getQuantitySSize().width,
							getQuantitySSize().height));
			quantitySs.add(quantityS);

			JFormattedTextField rateFTF = new JFormattedTextField();
			rateFTF.setForeground(Color.BLACK);
			rateFTF.setBackground(Color.WHITE);
			rateFTF.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18()));
			rateFTF.setFormatterFactory(
					new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("###0.00##"))));
			rateFTF.setHorizontalAlignment(JTextField.CENTER);
			rateFTF.setBorder(null);
			getContentPane().add(rateFTF,
					new AbsoluteConstraints(getRateFTFPos().width,
							getRateFTFPos().height + (getRateFTFSize().height) * i, getRateFTFSize().width,
							getRateFTFSize().height));
			rateFTFs.add(rateFTF);

			JFormattedTextField amountFTF = new JFormattedTextField();
			amountFTF.setForeground(Color.BLACK);
			amountFTF.setBackground(Color.WHITE);
			amountFTF.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18()));
			amountFTF.setFormatterFactory(
					new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("###,###,###,##0.00"))));
			amountFTF.setHorizontalAlignment(JTextField.CENTER);
			amountFTF.setBorder(null);
			amountFTF.addPropertyChangeListener(new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					// TODO Auto-generated method stud
					if (evt.getPropertyName().equals("value"))
						if (evt.getNewValue() != null)
							if (!evt.getNewValue().toString().equals("")) {
								double amount = _GUIGlobals.parseMoney(evt.getNewValue().toString());
								double oldAmount = 0;
								if (evt.getOldValue() != null)
									if (!evt.getOldValue().toString().equals(""))
										oldAmount = _GUIGlobals.parseMoney(evt.getOldValue().toString());
								double totalAmount = 0;
								if (totalAmountFTF.getText() != null)
									if (!totalAmountFTF.getText().equals(""))
										totalAmount = _GUIGlobals.parseMoney(totalAmountFTF.getText());
								totalAmountFTF.setValue(totalAmount - oldAmount + amount);
							}
				}
			});
			getContentPane().add(amountFTF,
					new AbsoluteConstraints(getAmountFTFPos().width,
							getAmountFTFPos().height + (getAmountFTFSize().height) * i, getAmountFTFSize().width,
							getAmountFTFSize().height));
			amountFTFs.add(amountFTF);

			itemCB.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent evt) {
					// TODO Auto-generated method stub
					Item item = null;
					if (evt.getStateChange() == ItemEvent.SELECTED)
						item = _GUIGlobals.parseItem((String) itemCB.getSelectedItem(), inventoryNo);
					if (item != null) {
						ProductTag productTag = AppLogic.ProductTags
								.get(AppLogic.ProductTags.indexOf(item.getPriceTagID()));
						Product product = AppLogic.Products.get(AppLogic.Products.indexOf(productTag.getProductCode()));
						rateFTF.setValue(productTag.getPrice() * product.getPiecesPerCarton());
						quantityS.setModel(new SpinnerNumberModel(0, 0, item.getQuantity(), 1));
					}
				}
			});

			quantityS.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent arg0) {
					// TODO Auto-generated method stub
					JSpinner s = (JSpinner) arg0.getSource();
					double rate = -1;
					if (rateFTF.getText() != null)
						if (!rateFTF.getText().isEmpty())
							rate = _GUIGlobals.parseMoney(rateFTF.getText());
					if (rate != -1)
						amountFTF.setValue(rate * (int) s.getValue());
				}
			});
		}
		amountFTFs.add(totalAmountFTF);

		setBackground(backgroundL);
		backgroundL.setBorder(BorderFactory.createLineBorder(_GUIGlobals.fontColor));
		backgroundL.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
		getContentPane().add(backgroundL, new AbsoluteConstraints(0, 0, -1, -1));

		inventoryCB.setBackground(Color.WHITE);
		inventoryCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_13())); // NOI18N
		inventoryCB.setForeground(Color.BLACK);
		inventoryCB.setBorder(null);
		Functions.loadInventoryCB(inventoryCB, -1);
		inventoryCB.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getStateChange() == ItemEvent.SELECTED) {
					inventoryNo = _GUIGlobals.parseInventory((String) inventoryCB.getSelectedItem()).getNumber();
					if (inventoryNo != -1)
						for (int i = 0; i < 15; i++)
							Functions.loadItemCB(itemCBs.get(i), -1, inventoryNo, true);
				}
			}

		});
		getContentPane().add(inventoryCB, new AbsoluteConstraints(getInventoryCBPos().width, getInventoryCBPos().height,
				getInventoryCBSize().width, getInventoryCBSize().height));

		getContentPane().add(functionP, new AbsoluteConstraints(img.getWidth(), 0, -1, -1));

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}// </editor-fold>//GEN-END:initComponents
}
