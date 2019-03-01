package day16;
import java.io.*;
import java.util.*;
public class PropertiesTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileInputStream fis = null;
		Properties p = new Properties();
		try {
			fis = new FileInputStream("src//day16//student.properties");
			//从输入流中读取属性列表（键和元素对）
			p.load(fis);
			Set set = p.keySet();
			//遍历
			for(Object obj:set){
				System.out.println(obj+": "+p.get(obj));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
