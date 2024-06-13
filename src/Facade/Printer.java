package Facade;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import Core.Invoice;
import Core.ProductTag;
import Frame.SavePanel2;
import GUI.Main;
import GUI.RectangularButton;
import GUI._GUIGlobals;

public class Printer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static BufferedImage image;
	private static Object object;
	private static Object object2;

	private static String getFormType() {
		String formType = null;
		switch (object.getClass().toString()) {
		case "class Core.Invoice": {
			formType = "invoice";
			break;
		}
		}
		return formType;
	}

	public Printer() {
		try {
			image = ImageIO
					.read(new File(Main.appDirectory + "\\Raw Materials\\Templates\\NHGT\\" + getFormType() + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		image = process();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(), image.getHeight());
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private void drawInvoice(Graphics2D g2d) {
		Invoice invoice = (Invoice) object;
		ArrayList<Double> rates = (ArrayList<Double>) object2;

		String invoiceNo = Integer.toString(invoice.getNumber());
		double wfactor = 14.38848921;
		double hfactor = 7.258064516;
		Dimension d = new Dimension((int) (_GUIGlobals.screenWidth / wfactor),
				(int) (_GUIGlobals.screenHeight / hfactor));
		g2d.setPaint(Color.RED);
		g2d.setFont(new Font("Calibri Light", 0, _GUIGlobals.getFontSize_Forms()));
		FontMetrics fm = g2d.getFontMetrics();
		g2d.drawString(invoiceNo, d.width, d.height + (int) (fm.getHeight() / 2));

		String date = invoice.getDate().toString();
		wfactor = 2.949852507;
		hfactor = 6.901840491;
		d = new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor));
		g2d.setPaint(Color.BLACK);
		g2d.setFont(new Font("Century Gothic", 0, _GUIGlobals.getFontSize_24()));
		g2d.drawString(date, d.width, d.height + (int) (fm.getHeight() / 2));

		String name = invoice.getName();
		wfactor = 13.24503311;
		hfactor = 5.136986301;
		d = new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor));
		g2d.drawString(name, d.width, d.height + (int) (fm.getHeight() / 2));

		String paymentTerms = invoice.getPaymentTerms();
		wfactor = 9.216589862;
		hfactor = 4.166666667;
		d = new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor));
		g2d.drawString(paymentTerms, d.width, d.height + (int) (fm.getHeight() / 2));

		if (invoice.getPiNo() != -1) {
			String piNo = Integer.toString(invoice.getPiNo());
			wfactor = 2.949852507;
			hfactor = 4.166666667;
			d = new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor));
			g2d.drawString(piNo, d.width, d.height + (int) (fm.getHeight() / 2));
		}

		double totalAmount = 0;
		hfactor = 2.914507772;
		Dimension gap = new Dimension(0, (int) (_GUIGlobals.screenHeight / 28.125));
		for (int i = 0; i < invoice.getItems().size(); i++) {
			ProductTag productTag = AppLogic.ProductTags
					.get(AppLogic.ProductTags.indexOf(invoice.getItems().get(i).getPriceTagID()));
			String itemName = productTag.getTag();
			wfactor = 13.98601399;
			d = new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor));
			g2d.drawString(itemName, d.width, d.height + (int) (fm.getHeight() / 2) + (int) (i * gap.getHeight()));

			String quantity = Integer.toString(invoice.getItems().get(i).getQuantity());
			wfactor = 3.424657534;
			d = new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor));
			g2d.drawString(quantity, d.width, d.height + (int) (fm.getHeight() / 2) + (int) (i * gap.getHeight()));

			String rate = new DecimalFormat("###0.00##").format((rates.get(i)));
			wfactor = 2.989536622;
			d = new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor));
			g2d.drawString(rate, d.width, d.height + (int) (fm.getHeight() / 2) + (int) (i * gap.getHeight()));

			String amount = new DecimalFormat("###,###,###,##0.00")
					.format(invoice.getItems().get(i).getQuantity() * rates.get(i));
			wfactor = 2.478314746;
			d = new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor));
			g2d.drawString(amount, d.width, d.height + (int) (fm.getHeight() / 2) + (int) (i * gap.getHeight()));
			totalAmount += invoice.getItems().get(i).getQuantity() * rates.get(i);
		}
		String amountString = new DecimalFormat("###,###,###,##0.00").format(totalAmount);
		wfactor = 2.478314746;
		d = new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor));
		g2d.setFont(new Font("Century Gothic", Font.BOLD, _GUIGlobals.getFontSize_24()));
		g2d.drawString(amountString, d.width, d.height + (int) (fm.getHeight() / 2) + (int) (15 * gap.getHeight()));

		String wholeNumber = NumberToWord.convert((int) totalAmount);
		String decimal = new DecimalFormat("00").format((totalAmount % 1) * 100);
		wfactor = 52.63157895;
		hfactor = 1.14213198;
		d = new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor));
		g2d.setFont(new Font("Century Gothic", Font.BOLD, _GUIGlobals.getFontSize_14()));
		g2d.drawString(wholeNumber + " AND " + decimal + " FILS", d.width, d.height + (int) (fm.getHeight() / 2));
	}

	private void drawForm(Graphics2D g2d) {
		switch (object.getClass().toString()) {
		case "class Core.Invoice": {
			drawInvoice(g2d);
			break;
		}
		}
	}

	private BufferedImage process() {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		g2d.drawImage(image, 0, 0, w, h, this);
		drawForm(g2d);
		g2d.dispose();
		return img;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

	public static void print(Object object, Object object2) {
		Printer.object = object;
		Printer.object2 = object2;
		JFrame f = new JFrame();
		f.setUndecorated(true);
		f.setTitle("Invoice");
		f.requestFocus();
		f.getContentPane().setLayout(new AbsoluteLayout());
		f.add(new Printer(), new AbsoluteConstraints(0, 0, -1, -1));

		SavePanel2 saveP = new SavePanel2();
		ActionListener saveAL = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser configFC = new JFileChooser();
				configFC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int choice = configFC.showOpenDialog(null);
				if (choice == JFileChooser.APPROVE_OPTION) {
					String directory = configFC.getSelectedFile().getAbsolutePath();
					File fout = new File(directory + "\\" + getFormType() + ".jpg");
					try {
						ImageIO.write(image, "png", fout);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					f.dispose();
				}
			}
		};
		ActionListener printAL = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PrinterJob printJob = PrinterJob.getPrinterJob();
				printJob.setPrintable(new ImagePrintable(printJob, image));

				if (printJob.printDialog()) {
					try {
						printJob.print();
						f.dispose();
					} catch (PrinterException prt) {
						prt.printStackTrace();
					}
				}
			}
		};
		ActionListener closeAL = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				f.dispose();
			}
		};
		((RectangularButton) saveP.getComponent(0)).addActionListener(saveAL);
		((RectangularButton) saveP.getComponent(1)).addActionListener(printAL);
		((RectangularButton) saveP.getComponent(2)).addActionListener(closeAL);
		f.getContentPane().add(saveP, new AbsoluteConstraints(image.getWidth(), 0, -1, -1));
		f.pack();
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}