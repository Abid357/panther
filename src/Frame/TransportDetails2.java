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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import GUI._GUIGlobals;

/**
 *
 * @author Abid-Temp
 */
public class TransportDetails2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String toLocation;
	private String fromLocation;
	private int inventoryNo;
	private double amount;

	private JLabel backgroundL;
	private JComboBox<String> fromLocationCB;
	private JComboBox<String> toLocationCB;
	private JComboBox<String> inventoryNoCB;
	private JFormattedTextField amountFTF;
	private JPanel functionP;
	private JLayeredPane functionsLP;

	public void setBackground(JLabel background) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(_GUIGlobals.directory + "\\Raw Materials\\Templates\\transport\\Slide3.jpg"));
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

	public JComboBox<String> getFromLocationCB() {
		return fromLocationCB;
	}

	public void setFromLocationCB(JComboBox<String> fromLocationCB) {
		this.fromLocationCB = fromLocationCB;
	}

	public JComboBox<String> getToLocationCB() {
		return toLocationCB;
	}

	public JComboBox<String> getInventoryNoCB() {
		return inventoryNoCB;
	}

	public void setToLocationCB(JComboBox<String> toLocationCB) {
		this.toLocationCB = toLocationCB;
	}

	public void setBackgroundL(JLabel backgroundL) {
		this.backgroundL = backgroundL;
	}

	public void setFunctionP(JPanel functionP) {
		this.functionP = functionP;
	}

	public void setFunctionsLP(JLayeredPane functionsLP) {
		this.functionsLP = functionsLP;
	}

	public void setInventoryNoCB(JComboBox<String> inventoryNoCB) {
		this.inventoryNoCB = inventoryNoCB;
	}

	public JFormattedTextField getAmountTF() {
		return amountFTF;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public int getInventoryNo() {
		return this.inventoryNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setInventoryNo(int inventoryNo) {
		this.inventoryNo = inventoryNo;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Dimension getFromLocationCBSize() {
		double wfactor = 9.062937063;
		double hfactor = 32.72727273;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getFromLocationCBPos() {
		double wfactor = 18.12587413;
		double hfactor = 22.6;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getToLocationCBSize() {
		double wfactor = 9.062937063;
		double hfactor = 32.72727273;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getToLocationCBPos() {
		double wfactor = 4.531468531;
		double hfactor = 22.6;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getInventoryNoCBSize() {
		double wfactor = 4.052532833;
		double hfactor = 32.72727273;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getInventoryNoCBPos() {
		double wfactor = 12.1575985;
		double hfactor = 9.221311475;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getAmountFTFSize() {
		double wfactor = 8.861538462;
		double hfactor = 36;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getAmountFTFPos() {
		double wfactor = 19.17159763;
		double hfactor = 5.710659898;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public void setEditable(boolean isEnabled) {
		fromLocationCB.setEnabled(isEnabled);
		toLocationCB.setEnabled(isEnabled);
		inventoryNoCB.setEnabled(isEnabled);
		amountFTF.setEnabled(isEnabled);
	}

	/**
	 * Creates new form Transaction
	 */
	public TransportDetails2(JPanel panel) {
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
		fromLocationCB = new JComboBox<String>();
		toLocationCB = new JComboBox<String>();
		inventoryNoCB = new JComboBox<String>();
		amountFTF = new JFormattedTextField();
		inventoryNo = -1;
		amount = 0;

		setUndecorated(true);
		setPreferredSize(new Dimension(_GUIGlobals.frameWidth, _GUIGlobals.frameHeight));
		requestFocus();
		getContentPane().setLayout(new AbsoluteLayout());

		functionsLP.setBackground(new Color(13, 13, 13));
		functionsLP.setForeground(_GUIGlobals.fontColor);
		functionsLP.setLayout(new AbsoluteLayout());
		functionsLP.add(functionP, new AbsoluteConstraints(0, 0, -1, -1));
		getContentPane().add(functionsLP, new AbsoluteConstraints(Transaction.getFunctionsLPPos().width,
				Transaction.getFunctionsLPPos().height, -1, -1));

		fromLocationCB.setEditable(true);
		fromLocationCB.setBackground(_GUIGlobals.fontColor);
		fromLocationCB.setForeground(_GUIGlobals.fontColor);
		fromLocationCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		fromLocationCB.setModel(new DefaultComboBoxModel<>(new String[] { "" }));
		fromLocationCB.setBorder(null);
		fromLocationCB.setPreferredSize(new Dimension(getFromLocationCBSize().width, getFromLocationCBSize().height));
		fromLocationCB.addItemListener(new ItemListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				String locationString = (String) ((JComboBox<String>) arg0.getSource()).getSelectedItem();
				if (locationString != null)
					if (!locationString.equals(""))
						fromLocation = locationString;
			}
		});
		getContentPane().add(fromLocationCB,
				new AbsoluteConstraints(getFromLocationCBPos().width, getFromLocationCBPos().height, -1, -1));

		toLocationCB.setEditable(true);
		toLocationCB.setBackground(_GUIGlobals.fontColor);
		toLocationCB.setForeground(_GUIGlobals.fontColor);
		toLocationCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		toLocationCB.setModel(new DefaultComboBoxModel<>(new String[] { "" }));
		toLocationCB.setBorder(null);
		toLocationCB.setPreferredSize(new Dimension(getToLocationCBSize().width, getToLocationCBSize().height));
		toLocationCB.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String locationString = (String) ((JComboBox<String>) arg0.getSource()).getSelectedItem();
				if (locationString != null)
					if (!locationString.equals(""))
						toLocation = locationString;
			}
		});
		getContentPane().add(toLocationCB,
				new AbsoluteConstraints(getToLocationCBPos().width, getToLocationCBPos().height, -1, -1));

		inventoryNoCB.setBackground(_GUIGlobals.fontColor);
		inventoryNoCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		inventoryNoCB.setForeground(_GUIGlobals.fontColor);
		inventoryNoCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
		inventoryNoCB.setBorder(null);
		inventoryNoCB
				.setPreferredSize(new java.awt.Dimension(getInventoryNoCBSize().width, getInventoryNoCBSize().height));
		getContentPane().add(inventoryNoCB,
				new AbsoluteConstraints(getInventoryNoCBPos().width, getInventoryNoCBPos().height, -1, -1));

		amountFTF.setBackground(new Color(13, 13, 13));
		amountFTF.setBorder(null);
		amountFTF.setForeground(_GUIGlobals.fontColor);
		amountFTF.setPreferredSize(new Dimension(getAmountFTFSize().width, getAmountFTFSize().height));
		amountFTF.setFormatterFactory(
				new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("###,###,###,##0.00##"))));
		amountFTF.setHorizontalAlignment(JTextField.CENTER);
		amountFTF.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		amountFTF.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						amountFTF.selectAll();
					}
				});
			}
		});
		getContentPane().add(amountFTF,
				new AbsoluteConstraints(getAmountFTFPos().width, getAmountFTFPos().height, -1, -1));

		setBackground(backgroundL);
		backgroundL.setBorder(BorderFactory.createLineBorder(_GUIGlobals.fontColor));
		backgroundL.setPreferredSize(new Dimension(_GUIGlobals.frameWidth, _GUIGlobals.frameHeight));
		getContentPane().add(backgroundL, new AbsoluteConstraints(0, 0, -1, -1));

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}// </editor-fold>//GEN-END:initComponents
}
