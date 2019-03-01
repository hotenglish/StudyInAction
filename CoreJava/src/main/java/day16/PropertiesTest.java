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
			//���������ж�ȡ�����б�����Ԫ�ضԣ�
			p.load(fis);
			Set set = p.keySet();
			//����
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
