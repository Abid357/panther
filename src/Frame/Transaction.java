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
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Calendar;

import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLayeredPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import com.toedter.calendar.JDateChooser;

import Core.Item;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Facade.Functions;
import GUI.RectangularButton;
import GUI._GUIGlobals;

/**
 *
 * @author Abid-Temp
 */
public class Transaction extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFormattedTextField amountTF;
	private JLabel backgroundL;
	private JComboBox<String> bankAccountCB;
	private JComboBox<String> chequeNoCB;
	private JDateChooser dateDC;
	private JTextArea descriptionTA;
	private JPanel functionP;
	private JComboBox<String> entityCB;
	private JLayeredPane functionsLP;
	private JCheckBox isBankCB;
	private JCheckBox isChequeCB;
	private JTextField itemTF;
	private JSpinner timeS;
	private JTextField transactionTF;
	private JSlider typeS;
	private Date date;
	private ArrayList<Item> items;
	private RectangularButton addBankB;
	private RectangularButton addChequeB;
	private RectangularButton selectItemB;

	public void setBackground(JLabel background) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(_GUIGlobals.directory + "\\Raw Materials\\Templates\\transaction.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(_GUIGlobals.frameWidth, _GUIGlobals.frameHeight, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		background.setIcon(imageIcon);
	}

	public JFormattedTextField getAmountTF() {
		return amountTF;
	}

	public JLabel getBackgroundL() {
		return backgroundL;
	}

	public JComboBox<String> getBankAccountCB() {
		return bankAccountCB;
	}

	public JComboBox<String> getChequeNoCB() {
		return chequeNoCB;
	}

	public JDateChooser getDateDC() {
		return dateDC;
	}

	public JTextArea getDescriptionTA() {
		return descriptionTA;
	}

	public JPanel getFunctionP() {
		return functionP;
	}

	public JComboBox<String> getEntityCB() {
		return entityCB;
	}

	public JLayeredPane getFunctionsLP() {
		return functionsLP;
	}

	public JCheckBox getIsBankCB() {
		return isBankCB;
	}

	public JCheckBox getIsChequeCB() {
		return isChequeCB;
	}

	public JTextField getItemTF() {
		return itemTF;
	}

	public JSpinner getTimeS() {
		return timeS;
	}

	public JTextField getTransactionTF() {
		return transactionTF;
	}

	public JSlider getTypeS() {
		return typeS;
	}

	public Date getDate() {
		return date;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public JButton getAddBankB() {
		return addBankB;
	}

	public JButton getAddChequeB() {
		return addChequeB;
	}

	public JButton getSelectItemB() {
		return selectItemB;
	}

	public static Dimension getTransactionTFSize() {
		double wfactor = 13.71428571;
		double hfactor = 27;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getTransactionTFPos() {
		double wfactor = 70;
		double hfactor = 11.6;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getDateDCSize() {
		double wfactor = 12.8;
		double hfactor = 36;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getDateDCPos() {
		double wfactor = 6.620689655;
		double hfactor = 22.5;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getTimeSSize() {
		double wfactor = 22.58823529;
		double hfactor = 33;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getTimeSPos() {
		double wfactor = 3.516483516;
		double hfactor = 22.5;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getAmountTFSize() {
		double wfactor = 11.29411765;
		double hfactor = 36;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getAmountTFPos() {
		double wfactor = 6.620689655;
		double hfactor = 9;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getTypeSSize() {
		double wfactor = 33.10344828;
		double hfactor = 27;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getTypeSPos() {
		double wfactor = 3.310344828;
		double hfactor = 9.6;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getDescriptionTASize() {
		double wfactor = 3.80952381;
		double hfactor = 108;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getDescriptionTAPos() {
		double wfactor = 14.76923077;
		double hfactor = 5.869565217;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getFunctionsLPSize() {
		return (new Dimension(_GUIGlobals.panelWidth, _GUIGlobals.panelHeight));
	}

	public static Dimension getFunctionsLPPos() {
		double hfactor = 1.730769231;
		return (new Dimension(1, (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getEntityCBSize() {
		double wfactor = 3.650190114;
		double hfactor = 36;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getEntityCBPos() {
		double wfactor = 18.46153846;
		double hfactor = 3.354037267;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getBankAccountCBSize() {
		double wfactor = 5.12;
		double hfactor = 36;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getBankAccountCBPos() {
		double wfactor = 12.0754717;
		double hfactor = 2.608695652;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getChequeNoCBSize() {
		double wfactor = 5.12;
		double hfactor = 36;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getChequeNoCBPos() {
		double wfactor = 12.0754717;
		double hfactor = 2.138613861;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getItemTFSize() {
		double wfactor = 38.4;
		double hfactor = 33.75;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getItemTFPos() {
		double wfactor = 11.92546584;
		double hfactor = 1.87826087;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getIsChequeCBPos() {
		double wfactor = 38.4;
		double hfactor = 2.302771855;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getIsBankCBPos() {
		double wfactor = 42.66666667;
		double hfactor = 2.872340426;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getAddBankBSize() {
		double wfactor = 23.41463415;
		double hfactor = 27;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getAddBankBPos() {
		double wfactor = 3.471971067;
		double hfactor = 2.640586797;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getAddChequeBSize() {
		double wfactor = 23.41463415;
		double hfactor = 27;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getAddChequeBPos() {
		double wfactor = 3.471971067;
		double hfactor = 2.16;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getSelectItemBSize() {
		double wfactor = 23.41463415;
		double hfactor = 27;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public static Dimension getSelectItemBPos() {
		double wfactor = 8.304279476;
		double hfactor = 1.891418564;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public void setEditable(boolean isEnabled) {
		getDateDC().setEnabled(isEnabled);
		getTimeS().setEnabled(isEnabled);
		getAmountTF().setEnabled(isEnabled);
		getTypeS().setEnabled(isEnabled);
		getDescriptionTA().setEnabled(isEnabled);
		getEntityCB().setEnabled(isEnabled);
		getBankAccountCB().setEnabled(isEnabled);
		getChequeNoCB().setEnabled(isEnabled);
		getAddBankB().setEnabled(isEnabled);
		getAddChequeB().setEnabled(isEnabled);
		getSelectItemB().setEnabled(isEnabled);
	}

	/**
	 * Creates new form Transaction
	 */
	public Transaction(JPanel panel) {
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

		transactionTF = new JTextField();
		timeS = new JSpinner(new SpinnerDateModel());
		typeS = new JSlider();
		amountTF = new JFormattedTextField();
		descriptionTA = new JTextArea();
		functionsLP = new JLayeredPane();
		backgroundL = new JLabel();
		chequeNoCB = new JComboBox<>();
		entityCB = new JComboBox<>();
		bankAccountCB = new JComboBox<>();
		itemTF = new JTextField();
		isChequeCB = new JCheckBox();
		isBankCB = new JCheckBox();
		dateDC = new JDateChooser();
		addBankB = new RectangularButton();
		addChequeB = new RectangularButton();
		selectItemB = new RectangularButton();

		setUndecorated(true);
		setTitle("Transaction");
		setPreferredSize(new Dimension(_GUIGlobals.frameWidth, _GUIGlobals.frameHeight));
		requestFocus();
		getContentPane().setLayout(new AbsoluteLayout());

		transactionTF.setEditable(false);
		transactionTF.setBackground(new Color(13, 13, 13));
		transactionTF.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		transactionTF.setForeground(new Color(255, 255, 255));
		transactionTF.setHorizontalAlignment(JTextField.CENTER);
		transactionTF.setBorder(null);
		transactionTF.setPreferredSize(new Dimension(getTransactionTFSize().width, getTransactionTFSize().height));
		getContentPane().add(transactionTF,
				new AbsoluteConstraints(getTransactionTFPos().width, getTransactionTFPos().height, -1, -1));

		dateDC.setForeground(_GUIGlobals.fontColor);
		dateDC.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_14())); // NOI18N
		dateDC.setPreferredSize(new Dimension(getDateDCSize().width, getDateDCSize().height));
		dateDC.setDate(Calendar.getInstance().getTime());
		dateDC.setMaxSelectableDate(Calendar.getInstance().getTime());
		dateDC.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getPropertyName().equals("date")) {
					date = new Date(dateDC.getDate().getTime());
				}
			}
		});
		getContentPane().add(dateDC, new AbsoluteConstraints(getDateDCPos().width, getDateDCPos().height, -1, -1));

		date = new Date(dateDC.getDate().getTime());

		timeS.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_13())); // NOI18N
		timeS.setBorder(null);
		timeS.setPreferredSize(new Dimension(getTimeSSize().width, getTimeSSize().height));
		timeS.setEditor(new JSpinner.DateEditor(timeS, "HH:mm:ss"));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		timeS.setValue(cal.getTime());
		getContentPane().add(timeS, new AbsoluteConstraints(getTimeSPos().width, getTimeSPos().height, -1, -1));

		typeS.setPreferredSize(new Dimension(getTypeSSize().width, getTypeSSize().height));
		typeS.setBackground(new Color(13, 13, 13));
		typeS.setForeground(_GUIGlobals.fontColor);
		typeS.setValue(0);
		typeS.setMaximum(1);
		getContentPane().add(typeS, new AbsoluteConstraints(getTypeSPos().width, getTypeSPos().height, -1, -1));

		amountTF.setBackground(new Color(13, 13, 13));
		amountTF.setBorder(null);
		amountTF.setForeground(_GUIGlobals.fontColor);
		amountTF.setPreferredSize(new Dimension(getAmountTFSize().width, getAmountTFSize().height));
		amountTF.setFormatterFactory(
				new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("###,###,###,##0.00##"))));
		amountTF.setHorizontalAlignment(JTextField.CENTER);
		amountTF.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		amountTF.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						amountTF.selectAll();
					}
				});
			}
		});
		getContentPane().add(amountTF,
				new AbsoluteConstraints(getAmountTFPos().width, getAmountTFPos().height, -1, -1));

		descriptionTA.setBackground(new Color(13, 13, 13));
		descriptionTA.setColumns(20);
		descriptionTA.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		descriptionTA.setForeground(_GUIGlobals.fontColor);
		descriptionTA.setLineWrap(true);
		descriptionTA.setRows(4);
		descriptionTA.setTabSize(3);
		descriptionTA.setBorder(null);
		descriptionTA.setPreferredSize(new Dimension(getDescriptionTASize().width, getDescriptionTASize().height));
		getContentPane().add(descriptionTA,
				new AbsoluteConstraints(getDescriptionTAPos().width, getDescriptionTAPos().height, -1, -1));

		functionsLP.setPreferredSize(new Dimension(getFunctionsLPSize().width, getFunctionsLPSize().height));
		functionsLP.setBackground(new Color(13, 13, 13));
		functionsLP.setForeground(_GUIGlobals.fontColor);
		functionsLP.setLayout(new AbsoluteLayout());
		functionsLP.add(functionP, new AbsoluteConstraints(0, 0, -1, -1));
		getContentPane().add(functionsLP,
				new AbsoluteConstraints(getFunctionsLPPos().width, getFunctionsLPPos().height, -1, -1));

		chequeNoCB.setBackground(_GUIGlobals.fontColor);
		chequeNoCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		chequeNoCB.setForeground(_GUIGlobals.fontColor);
		chequeNoCB.setModel(new DefaultComboBoxModel<>(new String[] { "" }));
		chequeNoCB.setBorder(null);
		chequeNoCB.setEnabled(false);
		chequeNoCB.setPreferredSize(new Dimension(getChequeNoCBSize().width, getChequeNoCBSize().height));
		getContentPane().add(chequeNoCB,
				new AbsoluteConstraints(getChequeNoCBPos().width, getChequeNoCBPos().height, -1, -1));

		entityCB.setBackground(_GUIGlobals.fontColor);
		entityCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		entityCB.setForeground(_GUIGlobals.fontColor);
		entityCB.setBorder(null);
		entityCB.setPreferredSize(new Dimension(getEntityCBSize().width, getEntityCBSize().height));
		entityCB.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getStateChange() == ItemEvent.SELECTED)
					Functions.loadBankAccountCB(bankAccountCB, (String) evt.getItem(), null);
			}

		});
		getContentPane().add(entityCB,
				new AbsoluteConstraints(getEntityCBPos().width, getEntityCBPos().height, -1, -1));

		bankAccountCB.setBackground(_GUIGlobals.fontColor);
		bankAccountCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		bankAccountCB.setForeground(_GUIGlobals.fontColor);
		bankAccountCB.setModel(new DefaultComboBoxModel<>(new String[] { "" }));
		bankAccountCB.setBorder(null);
		bankAccountCB.setEnabled(false);
		bankAccountCB.setPreferredSize(new Dimension(getBankAccountCBSize().width, getBankAccountCBSize().height));
		bankAccountCB.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getStateChange() == ItemEvent.SELECTED)
					Functions.loadChequeCB(chequeNoCB, (String) evt.getItem(), null);
			}

		});
		getContentPane().add(bankAccountCB,
				new AbsoluteConstraints(getBankAccountCBPos().width, getBankAccountCBPos().height, -1, -1));

		itemTF.setEditable(false);
		itemTF.setBackground(new Color(13, 13, 13));
		itemTF.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_24())); // NOI18N
		itemTF.setForeground(_GUIGlobals.fontColor);
		itemTF.setHorizontalAlignment(JTextField.CENTER);
		itemTF.setBorder(null);
		itemTF.setPreferredSize(new Dimension(getItemTFSize().width, getItemTFSize().height));
		getContentPane().add(itemTF, new AbsoluteConstraints(getItemTFPos().width, getItemTFPos().height, -1, -1));

		isChequeCB.setForeground(_GUIGlobals.fontColor);
		isChequeCB.setBorder(null);
		isChequeCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (((JCheckBox) evt.getSource()).isSelected()) {
					chequeNoCB.setEnabled(true);
				} else {
					chequeNoCB.setSelectedIndex(-1);
					chequeNoCB.setEnabled(false);
				}
			}
		});
		getContentPane().add(isChequeCB,
				new AbsoluteConstraints(getIsChequeCBPos().width, getIsChequeCBPos().height, -1, -1));

		isBankCB.setForeground(_GUIGlobals.fontColor);
		isBankCB.setBorder(null);
		isBankCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (((JCheckBox) evt.getSource()).isSelected()) {
					bankAccountCB.setEnabled(true);
				} else {
					bankAccountCB.setSelectedIndex(-1);
					bankAccountCB.setEnabled(false);
				}
			}
		});
		getContentPane().add(isBankCB,
				new AbsoluteConstraints(getIsBankCBPos().width, getIsBankCBPos().height, -1, -1));

		addBankB.setBorder(null);
		addBankB.setPreferredSize(new Dimension(getAddBankBSize().width, getAddBankBSize().height));
		addBankB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Functions.create("ADD BANK");
			}

		});
		getContentPane().add(addBankB,
				new AbsoluteConstraints(getAddBankBPos().width, getAddBankBPos().height, -1, -1));

		addChequeB.setBorder(null);
		addChequeB.setPreferredSize(new Dimension(getAddChequeBSize().width, getAddChequeBSize().height));
		addChequeB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Functions.create("ADD CHEQUE");
			}

		});
		getContentPane().add(addChequeB,
				new AbsoluteConstraints(getAddChequeBPos().width, getAddChequeBPos().height, -1, -1));

		selectItemB.setBorder(null);
		selectItemB.setPreferredSize(new Dimension(getSelectItemBSize().width, getSelectItemBSize().height));
		selectItemB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Functions.create("ADD ITEM DETAILS");
			}

		});
		getContentPane().add(selectItemB,
				new AbsoluteConstraints(getSelectItemBPos().width, getSelectItemBPos().height, -1, -1));

		setBackground(backgroundL);
		backgroundL.setBorder(BorderFactory.createLineBorder(_GUIGlobals.fontColor));
		backgroundL.setPreferredSize(new Dimension(_GUIGlobals.frameWidth, _GUIGlobals.frameHeight));
		getContentPane().add(backgroundL, new AbsoluteConstraints(0, 0, -1, -1));

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}// </editor-fold>//GEN-END:initComponents
}
