package day16;

import java.io.*;
import java.util.*;

public class PropertiesTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties p = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(
					"src\\day16\\student.properties");
			p.load(fis);
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex1) {
			System.out.println(ex1.getMessage());
		} catch (Exception ex1) {
			System.out.println(ex1.getMessage());
		}
		Set set = p.keySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			//System.out.println(o + ":" + p.get(o));
			String s = (String)p.get(o);
			System.out.println(o + ":" + isoToGBK(s));
		}
	}
	
	private static String isoToGBK(String str){
		String s = str;
		try {
			byte by[] = str.getBytes("ISO-8859-1");
			s = new String(by,"GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}
	

}










