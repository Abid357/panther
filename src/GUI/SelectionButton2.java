package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Facade.LoadSelections;

public class SelectionButton2 extends javax.swing.JToggleButton {

	public Dimension getSize() {
		double wfactor = 14.43609023;
		double hfactor = 40;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SelectionButton2(String name) {
		setBackground(new java.awt.Color(0, 0, 0));
		setFont(new java.awt.Font("Century Gothic", 0, _GUIGlobals.getFontSize_15()));
		setForeground(new java.awt.Color(166, 130, 52));
		setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED,
				new java.awt.Color(166, 130, 52), new java.awt.Color(166, 130, 52), new java.awt.Color(166, 130, 52),
				new java.awt.Color(166, 130, 52)));
		setText(name);
		setToolTipText(name);
		setPreferredSize(new java.awt.Dimension(getSize().width, getSize().height));
		Main.getButtonGroup2().add(this);
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				if (((SelectionButton2) evt.getSource()).isSelected()) {
					LoadSelections.panel3(getText());
					Main.getScrollPane3().repaint();
					Main.getScrollPane3().revalidate();
				}
			}

		});
		Main.getPanel2().add(this);
	}
}
