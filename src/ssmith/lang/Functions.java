package ssmith.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;

import ssmith.io.TextFile;

public final class Functions {

	private static Random random = new Random();

	public static int biggest(int a, int b) {
		return Math.max(a, b);
	}

	
	public static int rnd(int a, int b) {
		int var = b + 1 - a;
		return random.nextInt(var) + a;
	}

	
	public static byte rndByte(int a, int b) {
		assert a <= 127 && b <= 127;
		int var = b + 1 - a;
		return (byte)(random.nextInt(var) + a);
	}

	
	public static float rndFloat(float a, float b) {
		return (random.nextFloat() * (b - a)) + a;
	}

	
	public static double rndDouble(double a, double b) {
		return (random.nextDouble() * (b - a)) + a;
	}

	
	public static int sign(int a) {
		if (a == 0) {
			return 0;
		}
		else if (a > 0) {
			return 1;
		}
		else {
			return -1;
		}
	}

	
	public static int sign(float a) {
		if (a == 0) {
			return 0;
		}
		else if (a > 0) {
			return 1;
		}
		else {
			return -1;
		}
	}

	
	public static int sign(double a) {
		if (a == 0) {
			return 0;
		}
		else if (a > 0) {
			return 1;
		}
		else {
			return -1;
		}
	}

	
	public static float MakeSameSignAs(float num, float s) {
		if (sign(num) != sign(s)) {
			return num * -1;
		} else {
			return num;
		}
	}

	
	public static int mod(int x) {
		if (x >= 0) {
			return x;
		}
		else {
			return x * -1;
		}
	}

	
	public static float mod(float x) {
		if (x >= 0) {
			return x;
		}
		else {
			return x * -1;
		}
	}

	
	public static double mod(double x) {
		if (x >= 0) {
			return x;
		}
		else {
			return x * -1;
		}
	}

	
	public int remainder(int a, int d) {
		int r = (int) Math.IEEEremainder( (double) a, (double) d);
		if (r < 0) {
			r = r + d;
		}
		return r;
	}

	
	public static void delay(int milliseconds) {
		if (milliseconds > 0) {
			try {
				Thread.sleep(milliseconds);
			}
			catch (InterruptedException e) {
			}
		}
	}

	
	public static void delay(long milliseconds) {
		if (milliseconds > 0) {
			try {
				Thread.sleep(milliseconds);
			}
			catch (InterruptedException e) {
			}
		}
	}

	
	public static String AppendSlash(String file) {
		if (file.endsWith("\\") || file.endsWith("/")) {
			return file;
		} else {
			return file + "/";
		}
	}

	
	public static String CheckApostraphes(String SQL) {
		return SQL.replaceAll("'", "''");
	}

	
	public static void CopyFile(String in, String out) throws Exception {
		FileChannel sourceChannel = new FileInputStream(new File(in)).getChannel();
		FileChannel destinationChannel = new FileOutputStream(new File(out)).getChannel();
		sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
		// or
		//  destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		sourceChannel.close();
		destinationChannel.close();
	}


	/*public static void LogStackTrace(Exception ex, String URL) {
		try {
			TextFile tf = new TextFile();
			tf.openFile(URL, TextFile.APPEND);
			tf.writeLine(ex.getMessage());
			StringBuffer str = new StringBuffer();
			for (int c = 0; c < ex.getStackTrace().length; c++) {
//				StackTraceElement el = ex.getStackTrace()[ex.getStackTrace().length-1];
				str.append(ex.getStackTrace()[c].getClassName());
				str.append(":" + ex.getStackTrace()[c].getLineNumber() + " - ");
				str.append(ex.getStackTrace()[c].getMethodName());
				tf.writeLine(str.toString());
				str.delete(0, str.length()-1);
			}
			tf.close();
		} catch (Exception e) {
			System.out.print("Error logging error: " + e.getMessage());
		}
	}*/

	
	public static Hashtable<String, String> ConfigFile(String url, String sep) throws FileNotFoundException, IOException {
		Hashtable<String, String> ht = new Hashtable<String, String>();

		TextFile tf = new TextFile();
		tf.openFile(url, TextFile.READ);
		while (tf.isEOF() == false) {
			String line = tf.readLine();
			//System.out.println(line);
			if (line.length() > 0) {
				if (line.startsWith("#") == false) {
					String csv[] = line.split(sep);
					String key = csv[0];
					String value = "";
					if (csv.length >= 2) {
						value = csv[1];
					}
					//System.out.println("Ext:" + ext + "  Ctype:" + ctype);
					ht.put(key, value);
				}
			}
		}
		tf.close();
		return ht;
	}

	
	public static boolean IsNumeric(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	
	public static String Prezero(int s, int len) {
		return Prezero("" + s, len);
	}


	public static String Prezero(String s, int len) {
		StringBuffer str = new StringBuffer();
		for(int i=0 ; i<len ; i++) {
			str.append("0");
		}
		str.append(s);
		String ns = ""+str.toString();
		return str.toString().substring(ns.length()-len, ns.length());
	}
	
	
	public static String Exception2String(Throwable ex, String cr) {
		StringBuffer str = new StringBuffer();
		for (int c = 0; c < ex.getStackTrace().length; c++) {
			str.append(" " + ex.getStackTrace()[c].getClassName());
			str.append(":" + ex.getStackTrace()[c].getLineNumber() + " - ");
			str.append(ex.getStackTrace()[c].getMethodName());
			str.append(cr);
		}
		return str.toString();
	}

	
	public static void LogError(Throwable ex, String URL) {
		LogError(ex, "", URL);
	}

	
	public static void LogError(Throwable ex, String extra_details, String URL) {
		try {
			TextFile tf = new TextFile();
			tf.openFile(URL, TextFile.APPEND);
			tf.writeLine("Error on " + new Date().toString());
			tf.writeLine("Type: " + ex.getClass().toString());
			if (ex.getMessage() != null) {
				tf.writeLine("Message: " + ex.getMessage());
			} else {
				tf.writeLine("No message.");
			}
			if (extra_details.length() > 0) {
				tf.writeLine(extra_details);
			}
			tf.writeLine("Stack trace: ");
			tf.writeLine(Exception2String(ex, "\n"));
			tf.writeLine("End of error details.");
			tf.writeLine("");
			tf.close();
		} catch (Exception e) {
			/*if (throw_error) {
				throw e;
			} else {*/
				System.out.print("Error logging error: " + e.getMessage());
				e.printStackTrace();
			//}
		}
	}
	
	
	public static int ParseInt(String s) {
		return ParseInt(s, true);
	}
	
	
	public static int ParseInt(String s, boolean error) {
		try {
			if (s == null && error == false) {
				s = "0";
			} else if (s.length() == 0) { // In case of "-1"
				s = "0";
			}
			return Integer.parseInt(s.trim());
		} catch (java.lang.NumberFormatException ex) {
			if (error) {
				throw ex;
			} else {
				return 0;
			}
		}
	}
	
	
	public static byte ParseByte(String s) {
		return ParseByte(s, true);
	}
	
	
	public static byte ParseByte(String s, boolean error) {
		try {
			if (s == null && error == false) {
				s = "0";
			} else if (s.length() == 0) { // In case of "-1"
				s = "0";
			}
			return Byte.parseByte(s.trim());
		} catch (java.lang.NumberFormatException ex) {
			if (error) {
				throw ex;
			} else {
				return 0;
			}
		}
	}
	
	
	public static short ParseShort(String s) {
		return ParseShort(s, true);
	}
	
	
	public static short ParseShort(String s, boolean error) {
		try {
			if (s == null && error == false) {
				s = "0";
			} else if (s.length() == 0) { // In case of "-1"
				s = "0";
			}
			return Short.parseShort(s.trim());
		} catch (java.lang.NumberFormatException ex) {
			if (error) {
				throw ex;
			} else {
				return 0;
			}
		}
	}
	
	
	public static String b2YesNo(boolean b) {
		if (b) {
			return "Yes";
		} else {
			return "No";
		}
	}
	
	
	public static String PadString(String s, int l) {
		while (s.length() < l) {
			s = s + " ";
		}
		return s;
	}

	
	public static boolean IsIn(int needle, String haystack) {
		haystack = "," + haystack.replaceAll(" ", "") + ",";
		String n2 = "," + needle + ",";
		return (haystack.indexOf(n2) > 0);
	}
	
	
}


