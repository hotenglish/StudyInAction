package day16;
import java.io.*;
/**
 * 获取键盘数据
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
			System.out.println("请输入你的年龄：");
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




