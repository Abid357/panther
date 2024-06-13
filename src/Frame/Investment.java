/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
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

import javax.swing.JFrame;

import Facade.Functions;
import GUI.RectangularButton;
import GUI._GUIGlobals;

/**
 *
 * @author Abid-Temp
 */
public class Investment extends JFrame {

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
	private JSpinner timeS;
	private JTextField transactionTF;
	private JSlider typeS;
	private Date date;
	private RectangularButton addBankB;
	private RectangularButton addChequeB;

	public void setBackground(JLabel background) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(_GUIGlobals.directory + "\\Raw Materials\\Templates\\investment.jpg"));
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


	public void setAmountTF(double amount) {
		amountTF.setText(_GUIGlobals.formatMoney(amount));
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

	public JButton getAddBankB() {
		return addBankB;
	}

	public JButton getAddChequeB() {
		return addChequeB;
	}

	public Investment getFrame() {
		return this;
	}

	public Dimension getInvestmentDetailsBSize() {
		double wfactor = 7.64940239;
		double hfactor = 24;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getInvestmentDetailsBPos() {
		double wfactor = 5.630498534;
		double hfactor = 1.891418564;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getIsInvestmentDetailsCBSize() {
		double wfactor = 48;
		double hfactor = 27;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getIsInvestmentDetailsCBPos() {
		double wfactor = 3.189368771;
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
	}

	/**
	 * Creates new form Transaction
	 */
	public Investment(JPanel panel) {
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
		isChequeCB = new JCheckBox();
		isBankCB = new JCheckBox();
		dateDC = new JDateChooser();
		addBankB = new RectangularButton();
		addChequeB = new RectangularButton();
		
		setUndecorated(true);
		setTitle("Investment");
		setPreferredSize(new Dimension(_GUIGlobals.frameWidth, _GUIGlobals.frameHeight));
		requestFocus();
		getContentPane().setLayout(new AbsoluteLayout());

		transactionTF.setEditable(false);
		transactionTF.setBackground(new Color(13, 13, 13));
		transactionTF.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		transactionTF.setForeground(new Color(255, 255, 255));
		transactionTF.setHorizontalAlignment(JTextField.CENTER);
		transactionTF.setBorder(null);
		transactionTF.setPreferredSize(
				new Dimension(Transaction.getTransactionTFSize().width, Transaction.getTransactionTFSize().height));
		getContentPane().add(transactionTF, new AbsoluteConstraints(Transaction.getTransactionTFPos().width,
				Transaction.getTransactionTFPos().height, -1, -1));

		dateDC.setForeground(_GUIGlobals.fontColor);
		dateDC.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_14())); // NOI18N
		dateDC.setPreferredSize(new Dimension(Transaction.getDateDCSize().width, Transaction.getDateDCSize().height));
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
		getContentPane().add(dateDC,
				new AbsoluteConstraints(Transaction.getDateDCPos().width, Transaction.getDateDCPos().height, -1, -1));

		date = new Date(dateDC.getDate().getTime());

		timeS.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_13())); // NOI18N
		timeS.setBorder(null);
		timeS.setPreferredSize(new Dimension(Transaction.getTimeSSize().width, Transaction.getTimeSSize().height));
		timeS.setEditor(new JSpinner.DateEditor(timeS, "HH:mm:ss"));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		timeS.setValue(cal.getTime());
		getContentPane().add(timeS,
				new AbsoluteConstraints(Transaction.getTimeSPos().width, Transaction.getTimeSPos().height, -1, -1));

		typeS.setBackground(new Color(13, 13, 13));
		typeS.setForeground(_GUIGlobals.fontColor);
		typeS.setMaximum(1);
		typeS.setPreferredSize(new Dimension(Transaction.getTypeSSize().width, Transaction.getTypeSSize().height));
		typeS.setValue(0);
		typeS.setEnabled(false);
		getContentPane().add(typeS,
				new AbsoluteConstraints(Transaction.getTypeSPos().width, Transaction.getTypeSPos().height, -1, -1));

		amountTF.setBackground(new Color(13, 13, 13));
		amountTF.setBorder(null);
		amountTF.setPreferredSize(
				new Dimension(Transaction.getAmountTFSize().width, Transaction.getAmountTFSize().height));
		amountTF.setForeground(_GUIGlobals.fontColor);
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
		getContentPane().add(amountTF, new AbsoluteConstraints(Transaction.getAmountTFPos().width,
				Transaction.getAmountTFPos().height, -1, -1));

		descriptionTA.setBackground(new Color(13, 13, 13));
		descriptionTA.setColumns(20);
		descriptionTA.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		descriptionTA.setForeground(_GUIGlobals.fontColor);
		descriptionTA.setLineWrap(true);
		descriptionTA.setRows(4);
		descriptionTA.setTabSize(3);
		descriptionTA.setBorder(null);
		descriptionTA.setPreferredSize(
				new Dimension(Transaction.getDescriptionTASize().width, Transaction.getDescriptionTASize().height));
		getContentPane().add(descriptionTA, new AbsoluteConstraints(Transaction.getDescriptionTAPos().width,
				Transaction.getDescriptionTAPos().height, -1, -1));

		functionsLP.setPreferredSize(
				new Dimension(Transaction.getFunctionsLPSize().width, Transaction.getFunctionsLPSize().height));
		functionsLP.setBackground(new Color(13, 13, 13));
		functionsLP.setForeground(_GUIGlobals.fontColor);
		functionsLP.setLayout(new AbsoluteLayout());
		functionsLP.add(functionP, new AbsoluteConstraints(0, 0, -1, -1));
		getContentPane().add(functionsLP, new AbsoluteConstraints(Transaction.getFunctionsLPPos().width,
				Transaction.getFunctionsLPPos().height, -1, -1));

		chequeNoCB.setBackground(_GUIGlobals.fontColor);
		chequeNoCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		chequeNoCB.setForeground(_GUIGlobals.fontColor);
		chequeNoCB.setModel(new DefaultComboBoxModel<>(new String[] { "" }));
		chequeNoCB.setBorder(null);
		chequeNoCB.setEnabled(false);
		chequeNoCB.setPreferredSize(
				new Dimension(Transaction.getChequeNoCBSize().width, Transaction.getChequeNoCBSize().height));
		getContentPane().add(chequeNoCB, new AbsoluteConstraints(Transaction.getChequeNoCBPos().width,
				Transaction.getChequeNoCBPos().height, -1, -1));

		entityCB.setBackground(_GUIGlobals.fontColor);
		entityCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		entityCB.setForeground(_GUIGlobals.fontColor);
		entityCB.setBorder(null);
		entityCB.setPreferredSize(
				new Dimension(Transaction.getEntityCBSize().width, Transaction.getEntityCBSize().height));
		entityCB.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getStateChange() == ItemEvent.SELECTED)
					Functions.loadBankAccountCB(bankAccountCB, (String) evt.getItem(), null);
			}

		});
		getContentPane().add(entityCB, new AbsoluteConstraints(Transaction.getEntityCBPos().width,
				Transaction.getEntityCBPos().height, -1, -1));

		bankAccountCB.setBackground(_GUIGlobals.fontColor);
		bankAccountCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		bankAccountCB.setForeground(_GUIGlobals.fontColor);
		bankAccountCB.setModel(new DefaultComboBoxModel<>(new String[] { "" }));
		bankAccountCB.setBorder(null);
		bankAccountCB.setEnabled(false);
		bankAccountCB.setPreferredSize(
				new Dimension(Transaction.getBankAccountCBSize().width, Transaction.getBankAccountCBSize().height));
		bankAccountCB.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getStateChange() == ItemEvent.SELECTED)
					Functions.loadChequeCB(chequeNoCB, (String) evt.getItem(), null);
			}

		});
		getContentPane().add(bankAccountCB, new AbsoluteConstraints(Transaction.getBankAccountCBPos().width,
				Transaction.getBankAccountCBPos().height, -1, -1));

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
		getContentPane().add(isChequeCB, new AbsoluteConstraints(Transaction.getIsChequeCBPos().width,
				Transaction.getIsChequeCBPos().height, -1, -1));

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
		getContentPane().add(isBankCB, new AbsoluteConstraints(Transaction.getIsBankCBPos().width,
				Transaction.getIsBankCBPos().height, -1, -1));

		addBankB.setBorder(null);
		addBankB.setPreferredSize(new Dimension(Transaction.getAddBankBSize().width, Transaction.getAddBankBSize().height));
		addBankB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Functions.create("ADD BANK");
			}

		});
		getContentPane().add(addBankB, new AbsoluteConstraints(Transaction.getAddBankBPos().width,
				Transaction.getAddBankBPos().height, -1, -1));

		addChequeB.setBorder(null);
		addChequeB.setPreferredSize(new Dimension(Transaction.getAddChequeBSize().width, Transaction.getAddChequeBSize().height));
		addChequeB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Functions.create("ADD CHEQUE");
			}

		});
		getContentPane().add(addChequeB, new AbsoluteConstraints(Transaction.getAddChequeBPos().width,
				Transaction.getAddChequeBPos().height, -1, -1));

		setBackground(backgroundL);
		backgroundL.setBorder(BorderFactory.createLineBorder(_GUIGlobals.fontColor));
		backgroundL.setPreferredSize(new Dimension(_GUIGlobals.frameWidth, _GUIGlobals.frameHeight));
		getContentPane().add(backgroundL, new AbsoluteConstraints(0, 0, -1, -1));

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}// </editor-fold>//GEN-END:initComponents
}
