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
import javax.swing.JCheckBox;
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

import Facade.Functions;
import GUI.RectangularButton;
import GUI._GUIGlobals;

/**
 *
 * @author Abid-Temp
 */
public class ImportDetails extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int transportTransactionNo;
	private String toLocation;
	private String fromLocation;

	private JLabel backgroundL;
	private JFormattedTextField customDutyFTF;
	private JFormattedTextField deliveryOrderFTF;
	private JFormattedTextField clearingAgentFTF;
	private JFormattedTextField tokenFTF;
	private JFormattedTextField THCFTF;
	private JComboBox<String> fromLocationCB;
	private JComboBox<String> toLocationCB;
	private JPanel functionP;
	private JComboBox<String> transportTransactionNoCB;
	private JLayeredPane functionsLP;
	private JCheckBox isTransportCB;
	private RectangularButton addTransportB;

	public void setBackground(JLabel background) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(_GUIGlobals.directory + "\\Raw Materials\\Templates\\import\\Slide2.jpg"));
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

	public JComboBox<String> getTransportTransactionNoCB() {
		return transportTransactionNoCB;
	}

	public JCheckBox getIsTransportCB() {
		return isTransportCB;
	}

	public RectangularButton getAddTransportB() {
		return addTransportB;
	}

	public int getTransportTransactionNo() {
		return transportTransactionNo;
	}

	public void setTransportTransactionNo(int transportTransactionNo) {
		this.transportTransactionNo = transportTransactionNo;
	}

	public JFormattedTextField getCustomDutyFTF() {
		return customDutyFTF;
	}

	public void setCustomDutyFTF(JFormattedTextField customDutyFTF) {
		this.customDutyFTF = customDutyFTF;
	}

	public JFormattedTextField getDeliveryOrderFTF() {
		return deliveryOrderFTF;
	}

	public void setDeliveryOrderFTF(JFormattedTextField deliveryOrderFTF) {
		this.deliveryOrderFTF = deliveryOrderFTF;
	}

	public JFormattedTextField getClearingAgentFTF() {
		return clearingAgentFTF;
	}

	public void setClearingAgentFTF(JFormattedTextField clearingAgentFTF) {
		this.clearingAgentFTF = clearingAgentFTF;
	}

	public JFormattedTextField getTokenFTF() {
		return tokenFTF;
	}

	public void setTokenFTF(JFormattedTextField tokenFTF) {
		this.tokenFTF = tokenFTF;
	}

	public JFormattedTextField getTHCFTF() {
		return THCFTF;
	}

	public void setTHCFTF(JFormattedTextField tHCFTF) {
		THCFTF = tHCFTF;
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

	public void setToLocationCB(JComboBox<String> toLocationCB) {
		this.toLocationCB = toLocationCB;
	}

	public void setBackgroundL(JLabel backgroundL) {
		this.backgroundL = backgroundL;
	}

	public void setFunctionP(JPanel functionP) {
		this.functionP = functionP;
	}

	public void setTransportTransactionNoCB(JComboBox<String> transportTransactionNoCB) {
		this.transportTransactionNoCB = transportTransactionNoCB;
	}

	public void setFunctionsLP(JLayeredPane functionsLP) {
		this.functionsLP = functionsLP;
	}

	public void setIsTransportCB(JCheckBox isTransportCB) {
		this.isTransportCB = isTransportCB;
	}

	public void setAddTransportB(RectangularButton addTransportB) {
		this.addTransportB = addTransportB;
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

	public Dimension getCustomDutyFTFSize() {
		double wfactor = 11.07692308;
		double hfactor = 26.78571429;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getCustomDutyFTFPos() {
		double wfactor = 13.38151781;
		double hfactor = 9.375;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getDeliveryOrderFTFSize() {
		double wfactor = 11.07692308;
		double hfactor = 26.78571429;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getDeliveryOrderFTFPos() {
		double wfactor = 4.171226263;
		double hfactor = 9.375;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getClearingAgentFTFSize() {
		double wfactor = 11.07692308;
		double hfactor = 26.78571429;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getClearingAgentFTFPos() {
		double wfactor = 13.38151781;
		double hfactor = 5.798969072;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getTokenFTFSize() {
		double wfactor = 11.07692308;
		double hfactor = 26.78571429;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getTokenFTFPos() {
		double wfactor = 4.171226263;
		double hfactor = 5.798969072;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getTHCFTFSize() {
		double wfactor = 11.07692308;
		double hfactor = 26.78571429;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getTHCFTFPos() {
		double wfactor = 13.38151781;
		double hfactor = 4.197761194;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
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

	public Dimension getTransportTransactionNoCBSize() {
		double wfactor = 10.37837838;
		double hfactor = 32.72727273;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getTransportTransactionNoCBPos() {
		double wfactor = 12.1575985;
		double hfactor = 3.102391931;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getIsTransportCBPos() {
		double wfactor = 39.87692308;
		double hfactor = 3.4;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getAddTransportBSize() {
		double wfactor = 23.45701357;
		double hfactor = 26.78571429;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getAddTransportBPos() {
		double wfactor = 5.345432048;
		double hfactor = 3.133704735;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public void setEditable(boolean isEnabled) {
		customDutyFTF.setEnabled(isEnabled);
		deliveryOrderFTF.setEnabled(isEnabled);
		clearingAgentFTF.setEnabled(isEnabled);
		tokenFTF.setEnabled(isEnabled);
		THCFTF.setEnabled(isEnabled);
		getTransportTransactionNoCB().setEnabled(isEnabled);
		getIsTransportCB().setEnabled(isEnabled);
		getAddTransportB().setEnabled(isEnabled);
		fromLocationCB.setEnabled(isEnabled);
		toLocationCB.setEnabled(isEnabled);
	}

	/**
	 * Creates new form Transaction
	 */
	public ImportDetails(JPanel panel) {
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
		customDutyFTF = new JFormattedTextField();
		deliveryOrderFTF = new JFormattedTextField();
		clearingAgentFTF = new JFormattedTextField();
		THCFTF = new JFormattedTextField();
		tokenFTF = new JFormattedTextField();
		transportTransactionNoCB = new JComboBox<>();
		addTransportB = new RectangularButton();
		isTransportCB = new JCheckBox();
		transportTransactionNo = ((Frame.Import) Functions.getTempObj()).getTransportTransactionNo();
		fromLocationCB = new JComboBox<String>();
		toLocationCB = new JComboBox<String>();

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

		customDutyFTF.setBackground(new Color(13, 13, 13));
		customDutyFTF.setBorder(null);
		customDutyFTF.setPreferredSize(new Dimension(getCustomDutyFTFSize().width, getCustomDutyFTFSize().height));
		customDutyFTF.setForeground(_GUIGlobals.fontColor);
		customDutyFTF.setFormatterFactory(
				new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("###,###,###,##0.00##"))));
		customDutyFTF.setText("0.00");
		customDutyFTF.setHorizontalAlignment(JTextField.CENTER);
		customDutyFTF.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		customDutyFTF.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						customDutyFTF.selectAll();
					}
				});
			}
		});
		getContentPane().add(customDutyFTF,
				new AbsoluteConstraints(getCustomDutyFTFPos().width, getCustomDutyFTFPos().height, -1, -1));

		deliveryOrderFTF.setBackground(new Color(13, 13, 13));
		deliveryOrderFTF.setBorder(null);
		deliveryOrderFTF
				.setPreferredSize(new Dimension(getDeliveryOrderFTFSize().width, getDeliveryOrderFTFSize().height));
		deliveryOrderFTF.setForeground(_GUIGlobals.fontColor);
		deliveryOrderFTF.setFormatterFactory(
				new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("###,###,###,##0.00##"))));
		deliveryOrderFTF.setText("0.00");
		deliveryOrderFTF.setHorizontalAlignment(JTextField.CENTER);
		deliveryOrderFTF.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		deliveryOrderFTF.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						deliveryOrderFTF.selectAll();
					}
				});
			}
		});
		getContentPane().add(deliveryOrderFTF,
				new AbsoluteConstraints(getDeliveryOrderFTFPos().width, getDeliveryOrderFTFPos().height, -1, -1));

		clearingAgentFTF.setBackground(new Color(13, 13, 13));
		clearingAgentFTF.setBorder(null);
		clearingAgentFTF
				.setPreferredSize(new Dimension(getClearingAgentFTFSize().width, getClearingAgentFTFSize().height));
		clearingAgentFTF.setForeground(_GUIGlobals.fontColor);
		clearingAgentFTF.setFormatterFactory(
				new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("###,###,###,##0.00##"))));
		clearingAgentFTF.setText("0.00");
		clearingAgentFTF.setHorizontalAlignment(JTextField.CENTER);
		clearingAgentFTF.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		clearingAgentFTF.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						clearingAgentFTF.selectAll();
					}
				});
			}
		});
		getContentPane().add(clearingAgentFTF,
				new AbsoluteConstraints(getClearingAgentFTFPos().width, getClearingAgentFTFPos().height, -1, -1));

		THCFTF.setBackground(new Color(13, 13, 13));
		THCFTF.setBorder(null);
		THCFTF.setPreferredSize(new Dimension(getTHCFTFSize().width, getTHCFTFSize().height));
		THCFTF.setForeground(_GUIGlobals.fontColor);
		THCFTF.setFormatterFactory(
				new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("###,###,###,##0.00##"))));
		THCFTF.setText("0.00");
		THCFTF.setHorizontalAlignment(JTextField.CENTER);
		THCFTF.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		THCFTF.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						THCFTF.selectAll();
					}
				});
			}
		});
		getContentPane().add(THCFTF, new AbsoluteConstraints(getTHCFTFPos().width, getTHCFTFPos().height, -1, -1));

		tokenFTF.setBackground(new Color(13, 13, 13));
		tokenFTF.setBorder(null);
		tokenFTF.setPreferredSize(new Dimension(getTokenFTFSize().width, getTokenFTFSize().height));
		tokenFTF.setForeground(_GUIGlobals.fontColor);
		tokenFTF.setFormatterFactory(
				new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("###,###,###,##0.00##"))));
		tokenFTF.setText("0.00");
		tokenFTF.setHorizontalAlignment(JTextField.CENTER);
		tokenFTF.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		tokenFTF.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						tokenFTF.selectAll();
					}
				});
			}
		});
		getContentPane().add(tokenFTF,
				new AbsoluteConstraints(getTokenFTFPos().width, getTokenFTFPos().height, -1, -1));

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

		transportTransactionNoCB.setBackground(_GUIGlobals.fontColor);
		transportTransactionNoCB.setForeground(_GUIGlobals.fontColor);
		transportTransactionNoCB.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_18()));
		transportTransactionNoCB.setModel(new DefaultComboBoxModel<>(new String[] { "" }));
		transportTransactionNoCB.setBorder(null);
		transportTransactionNoCB.setPreferredSize(
				new Dimension(getTransportTransactionNoCBSize().width, getTransportTransactionNoCBSize().height));
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
		getContentPane().add(transportTransactionNoCB, new AbsoluteConstraints(getTransportTransactionNoCBPos().width,
				getTransportTransactionNoCBPos().height, -1, -1));

		isTransportCB.setForeground(_GUIGlobals.fontColor);
		isTransportCB.setBorder(null);
		isTransportCB.setSelected(false);
		isTransportCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (((JCheckBox) evt.getSource()).isSelected()) {
					transportTransactionNoCB.setEnabled(true);
					addTransportB.setEnabled(true);
				} else {
					transportTransactionNoCB.setSelectedIndex(-1);
					transportTransactionNoCB.setEnabled(false);
					addTransportB.setEnabled(false);
				}
			}
		});
		getContentPane().add(isTransportCB,
				new AbsoluteConstraints(getIsTransportCBPos().width, getIsTransportCBPos().height, -1, -1));

		addTransportB.setEnabled(false);
		addTransportB.setBorder(null);
		addTransportB.setPreferredSize(new Dimension(getAddTransportBSize().width, getAddTransportBSize().height));
		addTransportB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Functions.create("ADD TRANSPORT DETAILS 2");
			}

		});
		getContentPane().add(addTransportB,
				new AbsoluteConstraints(getAddTransportBPos().width, getAddTransportBPos().height, -1, -1));

		setBackground(backgroundL);
		backgroundL.setBorder(BorderFactory.createLineBorder(_GUIGlobals.fontColor));
		backgroundL.setPreferredSize(new Dimension(_GUIGlobals.frameWidth, _GUIGlobals.frameHeight));
		getContentPane().add(backgroundL, new AbsoluteConstraints(0, 0, -1, -1));

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}// </editor-fold>//GEN-END:initComponents
}
