package GUI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class Logger {
	private static String fileName = "log.txt";
	private static String fileDirectory = System.getenv("APPDATA") + "\\Panther\\" + fileName;

	public static String getFileDirectory() {
		return fileDirectory;
	}

	public static void add(Log log) {
		PrintWriter out = null;
		try {
			File file = new File(fileDirectory);
			if (!file.exists())
				file.getParentFile().mkdirs();
			out = new PrintWriter(new FileOutputStream(file, true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(new java.util.Date().getTime()));
		String time = new SimpleDateFormat("hh:mm:ss").format(new Time(new java.util.Date().getTime()));
		String string = "[" + date + " " + time + "] " + log.getClassName() + " [" + log.getVariableName1() + "=";
		if (log.getStringValue1() != null)
			string += log.getStringValue1();
		else
			string += log.getValue1();
		if (log.getVariableName2() != null) {
			string += ", " + log.getVariableName2() + "=";
			if (log.getStringValue2() != null)
				string += log.getStringValue2();
			else
				string += log.getValue2();
		}
		if (log.getVariableName3() != null) {
			string += ", " + log.getVariableName3() + "=";
			if (log.getStringValue3() != null)
				string += log.getStringValue3();
			else
				string += log.getValue3();
		}
		string += "] " + log.getAction() + ".\r\n";
		out.append(string);
		out.close();
	}

	public static void add(String log) {
		PrintWriter out = null;
		try {
			File file = new File(fileDirectory);
			if (!file.exists())
				file.getParentFile().mkdirs();
			out = new PrintWriter(new FileOutputStream(file, true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String date = new SimpleDateFormat("dd/mm/yyyy").format(new Date(new java.util.Date().getTime()));
		String time = new SimpleDateFormat("hh:mm:ss").format(new Time(new java.util.Date().getTime()));
		String string = "[" + date + " " + time + "] " + log + ".\r\n";
		out.append(string);
		out.close();
	}
}
