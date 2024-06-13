/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import GUI._GUIGlobals;

/**
 *
 * @author Abid-Temp
 */
public class AddPanel2 extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form AddPanel2
	 */

	public void setBackground(JLabel background) {
		img = null;
		try {
			img = ImageIO.read(
					new File(GUI._GUIGlobals.directory + "\\Raw Materials\\Templates\\function_buttons2\\Slide1.JPG"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		background.setIcon(imageIcon);
	}

	public Dimension getCloseBSize() {
		double wfactor = 13.61702128;
		double hfactor = 25.11627907;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getCloseBPos() {
		double wfactor = 105.3333333;
		double hfactor = 1.954184211;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getAddBSize() {
		double wfactor = 13.61702128;
		double hfactor = 25.11627907;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getAddBPos() {
		double wfactor = 105.3333333;
		double hfactor = 2.195066929;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public AddPanel2() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		addB = new GUI.RectangularButton();
		closeB = new GUI.RectangularButton();
		addL = new JLabel();

		setLayout(new AbsoluteLayout());

		addB.setBorder(null);
		addB.setPreferredSize(new Dimension(getAddBSize().width, getAddBSize().height));
		add(addB, new AbsoluteConstraints(getAddBPos().width, getAddBPos().height, -1, -1));

		closeB.setBorder(null);
		closeB.setPreferredSize(new Dimension(getCloseBSize().width, getCloseBSize().height));
		add(closeB, new AbsoluteConstraints(getCloseBPos().width, getCloseBPos().height, -1, -1));
		
		setBackground(addL);
		setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
		addL.setPreferredSize(getPreferredSize());
		addL.setBorder(BorderFactory.createLineBorder(_GUIGlobals.fontColor));
		add(addL, new AbsoluteConstraints(0, 0, -1, -1));
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JLabel addL;
	private GUI.RectangularButton addB;
	private GUI.RectangularButton closeB;
	private BufferedImage img;
	// End of variables declaration//GEN-END:variables
}
