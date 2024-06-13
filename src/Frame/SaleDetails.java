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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import Facade.Functions;
import GUI.RectangularButton;
import GUI._GUIGlobals;


/**
 *
 * @author Abid-Temp
 */
public class SaleDetails extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int inventoryNo;
	private int invoiceNo;
	private int transportTransactionNo;

	private JLabel backgroundL;
	private JComboBox<String> inventoryNoCB;
	private JComboBox<String> invoiceNoCB;
	private JPanel functionP;
	private JComboBox<String> transportTransactionNoCB;
	private JLayeredPane functionsLP;
	private JCheckBox isTransportCB;
	private RectangularButton addInvoiceB;
	private RectangularButton addTransportB;

	public void setBackground(JLabel background) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(_GUIGlobals.directory + "\\Raw Materials\\Templates\\sale\\Slide2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(_GUIGlobals.frameWidth, _GUIGlobals.frameHeight, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		background.setIcon(imageIcon);
	}

	public JLabel getBackgroundL() {
		return backgroundL;
	}

	public JPanel getFunctionP() {
		return functionP;
	}

	public JLayeredPane getFunctionsLP() {
		return functionsLP;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JComboBox<String> getInventoryNoCB() {
		return inventoryNoCB;
	}

	public JComboBox<String> getInvoiceNoCB() {
		return invoiceNoCB;
	}

	public JComboBox<String> getTransportTransactionNoCB() {
		return transportTransactionNoCB;
	}

	public JCheckBox getIsTransportCB() {
		return isTransportCB;
	}

	public RectangularButton getAddInvoiceB() {
		return addInvoiceB;
	}

	public RectangularButton getAddTransportB() {
		return addTransportB;
	}

	public int getInventoryNo() {
		return inventoryNo;
	}

	public void setInventoryNo(int inventoryNo) {
		this.inventoryNo = inventoryNo;
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public int getTransportTransactionNo() {
		return transportTransactionNo;
	}

	public void setTransportTransactionNo(int transportTransactionNo) {
		this.transportTransactionNo = transportTransactionNo;
	}

	public Dimension getInventoryCBSize() {
		double wfactor = 4.003707136;
		double hfactor = 26.78571429;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}
	
	public Dimension getInventoryCBPos() {
		double wfactor = 12.46153846;
		double hfactor = 12.46153846;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}
	
	public Dimension getInvoiceCBSize() {
		double wfactor = 187.7777778;
		double hfactor = 40.32;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}
	
	public Dimension getInvoiceCBPos() {
		double wfactor = 12.46153846;
		double hfactor = 9.375;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}
	
	public Dimension getTransportTransactionNoCBSize() {
		double wfactor = 10.27755749;
		double hfactor = 26.78571429;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}
	
	public Dimension getTransportTransactionNoCBPos() {
		double wfactor = 12.46153846;
		double hfactor = 5.306603774;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}
	
	public Dimension getAddInvoiceBSize() {
		double wfactor = 23.45701357;
		double hfactor = 26.78571429;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}
	
	public Dimension getAddInvoiceBPos() {
		double wfactor = 5.374248393;
		double hfactor = 9.375;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}
	
	public Dimension getAddTransportBSize() {
		double wfactor = 23.45701357;
		double hfactor = 26.78571429;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}
	
	public Dimension getAddTransportBPos() {
		double wfactor = 5.374248393;
		double hfactor = 5.306603774;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}
	
	public Dimension getIsTransportCBPos() {
		double wfactor = 40;
		double hfactor = 6.206896552;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}
	
	public void setEditable(boolean isEnabled) {
		getInventoryNoCB().setEnabled(isEnabled);
		getInvoiceNoCB().setEnabled(isEnabled);
		getTransportTransactionNoCB().setEnabled(isEnabled);
		getIsTransportCB().setEnabled(isEnabled);
		getAddInvoiceB().setEnabled(isEnabled);
		getAddTransportB().setEnabled(isEnabled);
	}

	/**
	 * Creates new form Transaction
	 */
	public SaleDetails(JPanel panel) {
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

		functionsLP = new JLayeredPane();
		backgroundL = new JLabel();
		invoiceNoCB = new JComboBox<>();
		inventoryNoCB = new JComboBox<>();
		transportTransactionNoCB = new JComboBox<>();
		addInvoiceB = new RectangularButton();
		addTransportB = new RectangularButton();
		isTransportCB = new JCheckBox();
		inventoryNo = ((Frame.Sale)Functions.getTempObj()).getInventoryNo();
		invoiceNo = ((Frame.Sale)Functions.getTempObj()).getInvoiceNo();
		transportTransactionNo = ((Frame.Sale)Functions.getTempObj()).getTransportTransactionNo();

		setUndecorated(true);
		setPreferredSize(new Dimension(_GUIGlobals.frameWidth, _GUIGlobals.frameHeight));
		requestFocus();
		getContentPane().setLayout(new AbsoluteLayout());

		functionsLP.setBackground(new Color(13, 13, 13));
		functionsLP.setForeground(_GUIGlobals.fontColor);
		functionsLP.setLayout(new AbsoluteLayout());
		functionsLP.add(functionP, new AbsoluteConstraints(0, 0, -1, -1));
		getContentPane().add(functionsLP, new AbsoluteConstraints(Transaction.getFunctionsLPPos().width, Transaction.getFunctionsLPPos().height, -1, -1));

		inventoryNoCB.setBackground(_GUIGlobals.fontColor);
		inventoryNoCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		inventoryNoCB.setForeground(_GUIGlobals.fontColor);
		inventoryNoCB.setModel(new DefaultComboBoxModel<>(new String[] { "" }));
		inventoryNoCB.setBorder(null);
		inventoryNoCB.setPreferredSize(new Dimension(478, 30));
		inventoryNoCB.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				@SuppressWarnings("unchecked")
				String inventoryString = (String) ((JComboBox<String>) arg0.getSource()).getSelectedItem();
				if (inventoryString != null)
					if (!inventoryString.equals(""))
						if (inventoryString.contains(":")) {
							inventoryNo = _GUIGlobals.parseWarehouse(inventoryString).getInventoryNo();
						} else {
							inventoryNo = _GUIGlobals.parseInventory(inventoryString).getNumber();
						}
			}
		});
		getContentPane().add(inventoryNoCB, new AbsoluteConstraints(154, 50, -1, -1));

		invoiceNoCB.setBackground(_GUIGlobals.fontColor);
		invoiceNoCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		invoiceNoCB.setForeground(_GUIGlobals.fontColor);
		invoiceNoCB.setModel(new DefaultComboBoxModel<>(new String[] { "" }));
		invoiceNoCB.setBorder(null);
		invoiceNoCB.setPreferredSize(new Dimension(187, 30));
		invoiceNoCB.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String invoiceString = (String) ((JComboBox<String>) arg0.getSource()).getSelectedItem();
				if (invoiceString != null)
					if (!invoiceString.equals(""))
						invoiceNo = Integer.parseInt(invoiceString);
			}
		});
		getContentPane().add(invoiceNoCB, new AbsoluteConstraints(154, 121, -1, -1));

		transportTransactionNoCB.setBackground(_GUIGlobals.fontColor);
		transportTransactionNoCB.setForeground(_GUIGlobals.fontColor);
		transportTransactionNoCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18()));
		transportTransactionNoCB.setModel(new DefaultComboBoxModel<>(new String[] { "" }));
		transportTransactionNoCB.setBorder(null);
		transportTransactionNoCB.setPreferredSize(new Dimension(187, 30));
		transportTransactionNoCB.setEnabled(false);
		transportTransactionNoCB.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String transportString = (String) ((JComboBox<String>) arg0.getSource()).getSelectedItem();
				if (transportString != null)
					if (!transportString.equals(""))
						transportTransactionNo = Integer.parseInt(transportString);
			}

		});
		getContentPane().add(transportTransactionNoCB,
				new AbsoluteConstraints(154, 209, -1, -1));

		isTransportCB.setForeground(_GUIGlobals.fontColor);
		isTransportCB.setBorder(null);
		isTransportCB.setSelected(false);
		isTransportCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (((JCheckBox) evt.getSource()).isSelected()) {
					transportTransactionNoCB.setEnabled(true);
				} else {
					transportTransactionNoCB.setSelectedIndex(-1);
					transportTransactionNoCB.setEnabled(false);
				}
			}
		});
		getContentPane().add(isTransportCB, new AbsoluteConstraints(48, 174, -1, -1));

		addInvoiceB.setBorder(null);
		addInvoiceB.setPreferredSize(new Dimension(82, 40));
		addInvoiceB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Functions.create("ADD INVOICE");
			}

		});
		getContentPane().add(addInvoiceB, new AbsoluteConstraints(357, 116, -1, -1));

		addTransportB.setBorder(null);
		addTransportB.setPreferredSize(new Dimension(82, 40));
		addTransportB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Functions.create("ADD TRANSPORT DETAILS");
			}

		});
		getContentPane().add(addTransportB, new AbsoluteConstraints(357, 205, -1, -1));

		setBackground(backgroundL);
		backgroundL.setBorder(BorderFactory.createLineBorder(_GUIGlobals.fontColor));
		backgroundL.setPreferredSize(new Dimension(_GUIGlobals.frameWidth, _GUIGlobals.frameHeight));
		getContentPane().add(backgroundL, new AbsoluteConstraints(0, 0, -1, -1));

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}// </editor-fold>//GEN-END:initComponents
}
