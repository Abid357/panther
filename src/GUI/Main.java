/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import Core.Inventory;
import DBMS._DBMSGlobals;
import Facade.LoadSelections;
import Facade.NumberToWord;
import Facade.Printer;
import Facade.QuickViewer;
import Facade.TableViewer;
import Facade._FacadeGlobals;
import Frame.AddPanel;
import Frame.AddPanel2;
import Frame.Domestic;
import Frame.Invoice;
import Frame.Notification;

/**
 *
 * @author Abid-Temp
 */
public class Main extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void setQV(int index, String optionName, String optionValue) {
		((javax.swing.JLabel) QVHeader.getComponent(index)).setText(optionName);
		((javax.swing.JLabel) QVBody.getComponent(index)).setText(optionValue);
	}

	public static javax.swing.JScrollPane getScrollPane1() {
		return selectionSP1;
	}

	public static javax.swing.JScrollPane getScrollPane2() {
		return selectionSP2;
	}

	public static javax.swing.JScrollPane getScrollPane3() {
		return selectionSP3;
	}

	public static javax.swing.JPanel getPanel1() {
		return selectionP1;
	}

	public static javax.swing.JPanel getPanel2() {
		return selectionP2;
	}

	public static javax.swing.JPanel getPanel3() {
		return selectionP3;
	}

	public static javax.swing.ButtonGroup getButtonGroup1() {
		return selectionBG1;
	}

	public static javax.swing.ButtonGroup getButtonGroup2() {
		return selectionBG2;
	}

	public static javax.swing.ButtonGroup getButtonGroup3() {
		return selectionBG3;
	}

	public static javax.swing.JScrollPane getTableSP() {
		return tableSP;
	}

	public static javax.swing.JTable getTableT() {
		return tableT;
	}

	public Dimension getQVHeaderSize() {
		double wfactor = 1.72972973;
		double hfactor = 21.6;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getQVBodySize() {
		double wfactor = 1.72972973;
		double hfactor = 9.818181818;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getQVHeaderPos() {
		double wfactor = 2.461538462;
		double hfactor = 12.0;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getQVBodyPos() {
		double wfactor = 2.461538462;
		double hfactor = 9.0;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getTimePeriodSize() {
		double wfactor = 10.0;
		double hfactor = 23.4375;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getTimePeriodPos() {
		double wfactor = 3.428571429;
		double hfactor = 8.307692308;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getSelectionPanel1Pos() {
		double wfactor = 27.42857143;
		double hfactor = 3.176470588;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getSelectionPanel1Size() {
		double wfactor = 12.8;
		double hfactor = 1.521126761;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getSelectionPanel2Pos() {
		double wfactor = 6.68989547;
		double hfactor = 3.176470588;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getSelectionPanel2Size() {
		double wfactor = 12.8;
		double hfactor = 1.521126761;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getSelectionPanel3Pos() {
		double wfactor = 3.80952381;
		double hfactor = 3.176470588;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getSelectionPanel3Size() {
		double wfactor = 12.8;
		double hfactor = 1.521126761;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getLoggerSize() {
		double wfactor = 22.06896552;
		double hfactor = 9.557522124;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getLoggerPos() {
		double wfactor = 1.99376947;
		double hfactor = 1.147715197;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getSaveDBBSize() {
		double wfactor = 16.47806739;
		double hfactor = 15.84507042;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getSaveDBBPos() {
		double wfactor = 47.47252747;
		double hfactor = 8.7890625;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getLoadDBBSize() {
		double wfactor = 16.47806739;
		double hfactor = 15.84507042;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getLoadDBBPos() {
		double wfactor = 9.632107023;
		double hfactor = 8.7890625;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getSettingsBSize() {
		double wfactor = 22.06896552;
		double hfactor = 9.557522124;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getSettingsBPos() {
		double wfactor = 1.99376947;
		double hfactor = 1.147715197;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getTableSize() {
		double wfactor = 1.733779264;
		double hfactor = 1.906779661;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public Dimension getTablePos() {
		double wfactor = 2.572704715;
		double hfactor = 3.242074928;
		return (new Dimension((int) (_GUIGlobals.screenWidth / wfactor), (int) (_GUIGlobals.screenHeight / hfactor)));
	}

	public void setBackground(JLabel background) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(_GUIGlobals.directory + "\\Raw Materials\\Templates\\main.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(_GUIGlobals.screenWidth, _GUIGlobals.screenHeight, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		background.setIcon(imageIcon);
	}

	/**
	 * Creates new form Main
	 */
	public Main() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		selectionBG1 = new javax.swing.ButtonGroup();
		selectionBG2 = new javax.swing.ButtonGroup();
		selectionBG3 = new javax.swing.ButtonGroup();
		timePeriodCB = new javax.swing.JComboBox<>();
		QVHeader = new javax.swing.JPanel();
		qvHeader1 = new javax.swing.JLabel();
		qvHeader2 = new javax.swing.JLabel();
		qvHeader3 = new javax.swing.JLabel();
		qvHeader4 = new javax.swing.JLabel();
		qvHeader5 = new javax.swing.JLabel();
		QVBody = new javax.swing.JPanel();
		qvBody1 = new javax.swing.JLabel();
		qvBody2 = new javax.swing.JLabel();
		qvBody3 = new javax.swing.JLabel();
		qvBody4 = new javax.swing.JLabel();
		qvBody5 = new javax.swing.JLabel();
		selectionSP1 = new javax.swing.JScrollPane();
		selectionP1 = new javax.swing.JPanel();
		selectionSP2 = new javax.swing.JScrollPane();
		selectionP2 = new javax.swing.JPanel();
		selectionSP3 = new javax.swing.JScrollPane();
		selectionP3 = new javax.swing.JPanel();
		loggerB = new RectangularButton();
		tableSP = new javax.swing.JScrollPane();
		tableT = new javax.swing.JTable();
		loadDBB = new RectangularButton();
		saveDBB = new RectangularButton();
		settingsB = new CircularButton();
		backgroundL = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Panther");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setUndecorated(true);
		setPreferredSize(new Dimension(_GUIGlobals.screenWidth, _GUIGlobals.screenHeight));
		setResizable(false);
		setSize(new java.awt.Dimension(_GUIGlobals.screenWidth, _GUIGlobals.screenHeight));
		getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		timePeriodCB.setFont(new java.awt.Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		timePeriodCB.setForeground(GUI._GUIGlobals.fontColor);
		timePeriodCB.setMaximumRowCount(6);
		timePeriodCB.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "This Month", "1 Month", "2 Months", "3 Months", "6 Months", "1 Year" }));
		timePeriodCB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

			}
		});
		getContentPane().add(timePeriodCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(getTimePeriodPos().width,
				getTimePeriodPos().height, getTimePeriodSize().width, getTimePeriodSize().height));

		QVHeader.setOpaque(false);
		QVHeader.setLayout(new java.awt.GridLayout(1, 5));

		qvHeader1.setFont(new java.awt.Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		qvHeader1.setForeground(new java.awt.Color(166, 130, 52));
		qvHeader1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		QVHeader.add(qvHeader1);

		qvHeader2.setFont(new java.awt.Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		qvHeader2.setForeground(new java.awt.Color(166, 130, 52));
		qvHeader2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		QVHeader.add(qvHeader2);

		qvHeader3.setFont(new java.awt.Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		qvHeader3.setForeground(new java.awt.Color(166, 130, 52));
		qvHeader3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		QVHeader.add(qvHeader3);

		qvHeader4.setFont(new java.awt.Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		qvHeader4.setForeground(new java.awt.Color(166, 130, 52));
		qvHeader4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		QVHeader.add(qvHeader4);

		qvHeader5.setFont(new java.awt.Font("Century Gothic", 0, _GUIGlobals.getFontSize_18())); // NOI18N
		qvHeader5.setForeground(new java.awt.Color(166, 130, 52));
		qvHeader5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		QVHeader.add(qvHeader5);
		getContentPane().add(QVHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(getQVHeaderPos().width,
				getQVHeaderPos().height, getQVHeaderSize().width, getQVHeaderSize().height));

		QVBody.setOpaque(false);
		QVBody.setLayout(new java.awt.GridLayout(1, 5));

		qvBody1.setFont(new java.awt.Font("Century Gothic", 0, _GUIGlobals.getFontSize_24())); // NOI18N
		qvBody1.setForeground(new java.awt.Color(255, 255, 255));
		qvBody1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		QVBody.add(qvBody1);

		qvBody2.setFont(new java.awt.Font("Century Gothic", 0, _GUIGlobals.getFontSize_24())); // NOI18N
		qvBody2.setForeground(new java.awt.Color(255, 255, 255));
		qvBody2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		QVBody.add(qvBody2);

		qvBody3.setFont(new java.awt.Font("Century Gothic", 0, _GUIGlobals.getFontSize_24())); // NOI18N
		qvBody3.setForeground(new java.awt.Color(255, 255, 255));
		qvBody3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		QVBody.add(qvBody3);

		qvBody4.setFont(new java.awt.Font("Century Gothic", 0, _GUIGlobals.getFontSize_24())); // NOI18N
		qvBody4.setForeground(new java.awt.Color(255, 255, 255));
		qvBody4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		QVBody.add(qvBody4);

		qvBody5.setFont(new java.awt.Font("Century Gothic", 0, _GUIGlobals.getFontSize_24())); // NOI18N
		qvBody5.setForeground(new java.awt.Color(255, 255, 255));
		qvBody5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		QVBody.add(qvBody5);
		getContentPane().add(QVBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(getQVBodyPos().width,
				getQVBodyPos().height, getQVBodySize().width, getQVBodySize().height));

		selectionSP1.setBackground(new java.awt.Color(0, 0, 0));
		selectionSP1.setBorder(null);
		selectionSP1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		selectionSP1.setOpaque(false);
		selectionSP1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		selectionSP1.getViewport().setBorder(null);
		selectionSP1.setViewportBorder(null);
		selectionP1.setBackground(new java.awt.Color(0, 0, 0, 0));
		selectionP1.setLayout(new java.awt.GridLayout(10, 0, 0, 4));
		selectionSP1.setViewportView(selectionP1);
		getContentPane().add(selectionSP1,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(getSelectionPanel1Pos().width,
						getSelectionPanel1Pos().height, getSelectionPanel1Size().width,
						getSelectionPanel1Size().height));

		selectionSP2.setBackground(new java.awt.Color(0, 0, 0));
		selectionSP2.setBorder(null);
		selectionSP2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		selectionSP2.setOpaque(false);
		selectionSP1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		selectionSP1.getViewport().setBorder(null);
		selectionSP1.setViewportBorder(null);
		selectionP2.setBackground(new java.awt.Color(0, 0, 0, 0));
		selectionP2.setLayout(new java.awt.GridLayout(10, 0, 0, 4));
		selectionSP2.setViewportView(selectionP2);
		getContentPane().add(selectionSP2,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(getSelectionPanel2Pos().width,
						getSelectionPanel2Pos().height, getSelectionPanel2Size().width,
						getSelectionPanel2Size().height));

		selectionSP3.setBackground(new java.awt.Color(0, 0, 0));
		selectionSP3.setBorder(null);
		selectionSP3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		selectionSP3.setOpaque(false);
		selectionSP1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		selectionSP1.getViewport().setBorder(null);
		selectionSP1.setViewportBorder(null);
		selectionP3.setBackground(new java.awt.Color(0, 0, 0, 0));
		selectionP3.setLayout(new java.awt.GridLayout(10, 0, 0, 4));
		selectionSP3.setViewportView(selectionP3);
		getContentPane().add(selectionSP3,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(getSelectionPanel3Pos().width,
						getSelectionPanel3Pos().height, getSelectionPanel3Size().width,
						getSelectionPanel3Size().height));

		loggerB.setPreferredSize(new java.awt.Dimension(getLoggerSize().width, getLoggerSize().height));
		loggerB.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				File file = new File(Logger.getFileDirectory());
				if (file.exists())
					if (Desktop.isDesktopSupported())
						try {
							Desktop.getDesktop().edit(file);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}

		});
		getContentPane().add(loggerB,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(getLoggerPos().width, getLoggerPos().height, -1, -1));

		loadDBB.setPreferredSize(new java.awt.Dimension(getLoadDBBSize().width, getLoadDBBSize().height));
		loadDBB.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (_DBMSGlobals.loadDatabases()) {
					new Notification("Databases successfully loaded.");
					TableViewer.update();
					QuickViewer.update();
				} else
					new Notification(
							"Database server is not currently running. Please switch on the server from SQL Server 2016 Configuration Manager.");
			}

		});
		getContentPane().add(loadDBB, new org.netbeans.lib.awtextra.AbsoluteConstraints(getLoadDBBPos().width,
				getLoadDBBPos().height, -1, -1));

		saveDBB.setPreferredSize(new java.awt.Dimension(getSaveDBBSize().width, getSaveDBBSize().height));
		saveDBB.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (_DBMSGlobals.saveDatabases())
					new Notification("Databases successfully saved.");
				else
					new Notification("An error occurred. Databases could not be saved.");
			}

		});
		getContentPane().add(saveDBB, new org.netbeans.lib.awtextra.AbsoluteConstraints(getSaveDBBPos().width,
				getSaveDBBPos().height, -1, -1));

		getContentPane().add(settingsB, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, -1, -1, -1));

		tableT.setBorder(null);
		tableT.setOpaque(false);
		((javax.swing.table.DefaultTableCellRenderer) tableT.getDefaultRenderer(Object.class)).setOpaque(false);
		tableT.setBackground(new java.awt.Color(13, 13, 13));
		tableT.setForeground(_GUIGlobals.fontColor);
		tableT.setPreferredScrollableViewportSize(getTableSize());
		tableT.setFillsViewportHeight(true);
		tableT.setFont(new java.awt.Font("Century Gothic", 0, _GUIGlobals.getFontSize_18()));
		tableT.getTableHeader()
				.setFont(new java.awt.Font("Century Gothic", java.awt.Font.BOLD, _GUIGlobals.getFontSize_15()));
		tableT.getTableHeader().setBackground(_GUIGlobals.fontColor);
		tableT.setRowHeight(25);
		tableT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableT.setShowHorizontalLines(true);
		tableT.setShowVerticalLines(true);
		javax.swing.table.TableCellRenderer rendererFromHeader = tableT.getTableHeader().getDefaultRenderer();
		JLabel headerLabel = (JLabel) rendererFromHeader;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);

		tableSP.setPreferredSize(new java.awt.Dimension(getTableSize().width, getTableSize().height));
		tableSP.setBorder(null);
		tableSP.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tableSP.setOpaque(false);
		tableSP.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tableSP.getViewport().setBorder(null);
		tableSP.setViewportBorder(null);
		tableSP.getViewport().setOpaque(false);
		tableSP.getViewport().add(tableT);
		getContentPane().add(tableSP,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(getTablePos().width, getTablePos().height, -1, -1));

		backgroundL.setPreferredSize(getPreferredSize());
		setBackground(backgroundL);
		getContentPane().add(backgroundL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
	}// </editor-fold>//GEN-END:initComponents

	public static boolean writeConfigFile() {
		JFileChooser configFC = new JFileChooser();
		configFC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int choice = configFC.showOpenDialog(null);
		if (choice == JFileChooser.APPROVE_OPTION) {
			appDirectory = configFC.getSelectedFile().getAbsolutePath();
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(new File(sysDirectory)));
				writer.write("directory:" + appDirectory);
				writer.close();
				return verifyAppDirectory();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				new Notification("Configuration file cannot be found. Please restart Panther.");
			}
			return false;
		} else
			return false;
	}

	public static boolean verifyAppDirectory() {
		File rawMaterials = new File(appDirectory + "\\Raw Materials");
		return rawMaterials.exists();
	}

	public static boolean readConfigFile() {
		File file = new File(sysDirectory);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Notification n = new Notification("Configuration file cannot be created.");
				System.exit(0);
			}
		}
		List<String> lines = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null)
				lines.add(line);
			if (lines.isEmpty()) {
				reader.close();
				return false;
			}
			appDirectory = lines.get(0).substring(lines.get(0).indexOf(":") + 1);
			reader.close();
			return verifyAppDirectory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			new Notification("Configuration file cannot be read.");
		}
		return false;
	}

	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		if (!readConfigFile()) {
			JOptionPane.showMessageDialog(new JFrame(), "Please select Panther directory.", null,
					JOptionPane.INFORMATION_MESSAGE);
			if (writeConfigFile())
				new Notification("Panther setup successful! Please restart the software.");
			else {
				JOptionPane.showMessageDialog(new JFrame(),
						"Panther could not be located. Please restart the software.", "Operation Failed",
						JOptionPane.ERROR_MESSAGE);
				System.exit(0);
				}
		} else {
			if (!_DBMSGlobals.loadDatabases()) {
				if (verifyAppDirectory())
					new Notification(
							"Database server is not currently running. Please switch on the server from SQL Server 2016 Configuration Manager.");
				else
					JOptionPane.showMessageDialog(new JFrame(),
							"Database server is not currently running. Please switch on the server from SQL Server 2016 Configuration Manager.",
							"Operation Failed", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
							.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							javax.swing.UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (ClassNotFoundException ex) {
					java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null,
							ex);
				} catch (InstantiationException ex) {
					java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null,
							ex);
				} catch (IllegalAccessException ex) {
					java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null,
							ex);
				} catch (javax.swing.UnsupportedLookAndFeelException ex) {
					java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null,
							ex);
				}
				// </editor-fold>

				/* Create and display the form */
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						new Main().setVisible(true);
						new QuickViewer();
						new TableViewer();
						new LoadSelections();
						new _FacadeGlobals();
						new SelectionButton1("TRANSACTION");
						new SelectionButton1("INVENTORY");
						new SelectionButton1("ENTITY");
						new SelectionButton1("ITEM");
						new SelectionButton1("PRODUCTION");
						new SelectionButton1("BANK");
						new SelectionButton1("LICENSE");
						new SelectionButton1("VEHICLE");
						new SelectionButton1("FORM");
						new SelectionButton1("REPORT");
					}
				});
			}
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	public static String appDirectory = null;
	private static String sysDirectory = System.getenv("APPDATA") + "\\Panther\\config.txt";
	private static javax.swing.JPanel QVBody;
	private static javax.swing.JPanel QVHeader;
	private javax.swing.JLabel backgroundL;
	private javax.swing.JComboBox<String> timePeriodCB;
	private javax.swing.JLabel qvBody1;
	private javax.swing.JLabel qvBody2;
	private javax.swing.JLabel qvBody3;
	private javax.swing.JLabel qvBody4;
	private javax.swing.JLabel qvBody5;
	private javax.swing.JLabel qvHeader1;
	private javax.swing.JLabel qvHeader2;
	private javax.swing.JLabel qvHeader3;
	private javax.swing.JLabel qvHeader4;
	private javax.swing.JLabel qvHeader5;
	private static javax.swing.ButtonGroup selectionBG1;
	private static javax.swing.ButtonGroup selectionBG2;
	private static javax.swing.ButtonGroup selectionBG3;
	private static javax.swing.JPanel selectionP1;
	private static javax.swing.JPanel selectionP2;
	private static javax.swing.JPanel selectionP3;
	private static javax.swing.JScrollPane selectionSP1;
	private static javax.swing.JScrollPane selectionSP2;
	private static javax.swing.JScrollPane selectionSP3;
	private static RectangularButton loggerB;
	private static javax.swing.JScrollPane tableSP;
	private static javax.swing.JTable tableT;
	private static RectangularButton loadDBB;
	private static RectangularButton saveDBB;
	private static CircularButton settingsB;
	// End of variables declaration//GEN-END:variables
}
