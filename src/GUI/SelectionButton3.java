package GUI;

import java.awt.Dimension;

import Facade.TableViewer;

public class SelectionButton3 extends javax.swing.JToggleButton {
	
	public Dimension getSize() {
		double wfactor = 14.43609023;
		double hfactor = 40;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SelectionButton3(String name) {
		setBackground(new java.awt.Color(0, 0, 0));
		setFont(new java.awt.Font("Century Gothic", 0, _GUIGlobals.getFontSize_15()));
		setForeground(new java.awt.Color(166, 130, 52));
		setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED,
				new java.awt.Color(166, 130, 52), new java.awt.Color(166, 130, 52), new java.awt.Color(166, 130, 52),
				new java.awt.Color(166, 130, 52)));
		setText(name);
		setToolTipText(name);
		setPreferredSize(new java.awt.Dimension(getSize().width, getSize().height));
		Main.getButtonGroup3().add(this);
		addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// TODO Auto-generated method stub
				if (((SelectionButton3) evt.getSource()).isSelected()) {
					if (_GUIGlobals.activeFrames == 0)
						Facade.Functions.create(getText());
					TableViewer.populate(getText());
				}
			}
		});
		Main.getPanel3().add(this);
	}
}
