package day16;
import java.io.*;
/**
 * ��ȡ��������
 * @author new
 *
 */
public class SystemIn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader br = null;
		InputStreamReader isr = null;
		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
		int age = 0;
		do{
			System.out.println("������������䣺");
			try {
				age = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}while(age<20);
		try {
			if(br!=null){
				br.close();
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(isr!=null){
				isr.close();
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}




