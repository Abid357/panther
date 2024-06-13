package GUI;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.ButtonModel;
import javax.swing.JButton;

class CircularButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color TL = new Color(1f, 1f, 1f, .2f);
	private static final Color BR = new Color(0f, 0f, 0f, .4f);
	private static final Color ST = new Color(1f, 1f, 1f, .2f);
	private static final Color SB = new Color(1f, 1f, 1f, .1f);
	private Color ssc;
	private Color bgc;
	private int x = 21, y = 8, w = 75, h = 75;

	public CircularButton(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public CircularButton() {
		
	}

	@Override
	public void updateUI() {
		super.updateUI();
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
		setForeground(new Color(166, 130, 52));
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Shape area = new Ellipse2D.Float(x, y, w, h);
		ssc = TL;
		bgc = BR;
		ButtonModel m = getModel();
		if (m.isPressed()) {
			ssc = SB;
			bgc = ST;
		} else if (m.isRollover()) {
			ssc = ST;
			bgc = SB;
		}
		g2.setPaint(new GradientPaint(x, y, ssc, x, y + h, bgc, true));
		g2.fill(area);
		g2.setPaint(BR);
		g2.draw(area);
		g2.dispose();
		super.paintComponent(g);
	}
}