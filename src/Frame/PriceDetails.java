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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import Core.Item;
import Facade.Functions;
import GUI._GUIGlobals;

/**
 *
 * @author Abid-Temp
 */
public class PriceDetails extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int frameWidth;
	private int frameHeight;

	private JLabel backgroundL;
	private ArrayList<JLabel> itemLs;
	private ArrayList<JLabel> quantityLs;
	private ArrayList<JFormattedTextField> priceFTFs;
	private JFormattedTextField amountFTF;
	private ArrayList<Item> items;
	private int itemCount;

	private JPanel functionP;
	private JLayeredPane functionsLP;

	public void setBackground(JLabel background) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(_GUIGlobals.directory + "\\Raw Materials\\Templates\\price.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(_GUIGlobals.frameWidth, _GUIGlobals.frameHeight, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		background.setIcon(imageIcon);
	}

	public int getItemCBX() {
		return 28;
	}

	public int getItemCBY() {
		return 0;
	}

	public int getItemCBgap() {
		return 18;
	}

	public JFrame getFrame() {
		return this;
	}

	public Dimension getItemLSize() {
		double wfactor = 6.359955834;
		double hfactor = 43.26923077;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getItemLPos() {
		double wfactor = 72.75331565;
		double hfactor = 10.61320755;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getQuantityLSize() {
		double wfactor = 12.359955834;
		double hfactor = 43.26923077;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getQuantityLPos() {
		double wfactor = 5.76331565;
		double hfactor = 10.61320755;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getPriceFTFSize() {
		double wfactor = 16.359955834;
		double hfactor = 45.26923077;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getPriceFTFPos() {
		double wfactor = 3.80331565;
		double hfactor = 10.61320755;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
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

	public void setEditable(boolean isEnabled) {
		// IMPLEMENT ME
	}

	public ArrayList<JFormattedTextField> getPriceFTFs() {
		return priceFTFs;
	}

	public ArrayList<JLabel> getQuantityLs() {
		return quantityLs;
	}

	public JFormattedTextField getAmountFTF() {
		return amountFTF;
	}

	public int getItemCount() {
		return itemCount;
	}

	public ArrayList<JLabel> getItemLs() {
		return itemLs;
	}

	public void setNames(ArrayList<String> names) {
		for (int i = 0; i < getItemCount(); i++)
			getItemLs().get(i).setText(names.get(i));
	}

	public void setClassVariables() {
		switch (Functions.getTempObj().getClass().toString()) {
		case "class Frame.Sale": {
			amountFTF = ((Sale) Functions.getTempObj()).getAmountTF();
			items = ((Sale) Functions.getTempObj()).getItems();
			break;
		}
		case "class Frame.Import": {
			amountFTF = ((Import) Functions.getTempObj()).getAmountTF();
			items = ((Import) Functions.getTempObj()).getItems();
			break;
		}
		case "class Frame.Purchase": {
			amountFTF = ((Purchase) Functions.getTempObj()).getAmountTF();
			items = ((Purchase) Functions.getTempObj()).getItems();
			break;
		}
		}
	}

	/**
	 * Creates new form Transaction
	 */
	public PriceDetails(JPanel panel) {
		frameWidth = _GUIGlobals.frameWidth;
		frameHeight = _GUIGlobals.frameHeight;
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
		setClassVariables();
		itemCount = items.size();
		itemLs = new ArrayList<JLabel>();
		quantityLs = new ArrayList<JLabel>();
		priceFTFs = new ArrayList<JFormattedTextField>();

		setUndecorated(true);
		requestFocus();
		getContentPane().setLayout(new AbsoluteLayout());

		functionsLP.setBackground(new Color(13, 13, 13));
		functionsLP.setForeground(_GUIGlobals.fontColor);
		functionsLP.setLayout(new AbsoluteLayout());
		functionsLP.add(functionP, new AbsoluteConstraints(0, 0, -1, -1));
		getContentPane().add(functionsLP, new AbsoluteConstraints(0, 624, -1, -1));

		for (int i = 0; i < itemCount; i++) {
			JLabel itemL = new JLabel();
			itemL.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_14()));
			itemL.setHorizontalAlignment(JLabel.LEFT);
			itemL.setEnabled(false);
			itemL.setForeground(_GUIGlobals.fontColor);
			if (AppLogic.PriceTags.get(AppLogic.PriceTags.indexOf(items.get(i).getPriceTagID())) != null)
				itemL.setText(
						AppLogic.PriceTags.get(AppLogic.PriceTags.indexOf(items.get(i).getPriceTagID())).getTag());
			itemLs.add(itemL);
			getContentPane().add(itemL, new AbsoluteConstraints(getItemLPos().width,
					(getItemLPos().height + (getItemCBgap() * i)), getItemLSize().width, getItemLSize().height));

			JLabel quantityL = new JLabel();
			quantityL.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_14()));
			quantityL.setHorizontalAlignment(JLabel.CENTER);
			quantityL.setEnabled(false);
			quantityL.setForeground(_GUIGlobals.fontColor);
			quantityL.setText(Integer.toString(items.get(i).getQuantity()));
			quantityLs.add(quantityL);
			getContentPane().add(quantityL,
					new AbsoluteConstraints(getQuantityLPos().width, (getQuantityLPos().height + (getItemCBgap() * i)),
							getQuantityLSize().width, getQuantityLSize().height));

			JFormattedTextField priceFTF = new JFormattedTextField();
			priceFTF.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_14()));
			priceFTF.setHorizontalAlignment(JFormattedTextField.CENTER);
			priceFTF.setForeground(_GUIGlobals.fontColor);
			priceFTF.setBackground(new Color(13, 13, 13));
			priceFTF.setBorder(null);
			priceFTF.setFormatterFactory(
					new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("###,###,###,##0.00##"))));
			priceFTF.setValue(0);
			priceFTF.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent evt) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							priceFTF.selectAll();
						}
					});
				}
			});
			priceFTFs.add(priceFTF);
			getContentPane().add(priceFTF,
					new AbsoluteConstraints(getPriceFTFPos().width, (getPriceFTFPos().height + (getItemCBgap() * i)),
							getPriceFTFSize().width, getPriceFTFSize().height));
		}

		setBackground(backgroundL);
		backgroundL.setBorder(BorderFactory.createLineBorder(_GUIGlobals.fontColor));
		backgroundL.setPreferredSize(new Dimension(_GUIGlobals.frameWidth, _GUIGlobals.frameHeight));
		getContentPane().add(backgroundL, new AbsoluteConstraints(0, 0, -1, -1));

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}// </editor-fold>//GEN-END:initComponents
}
