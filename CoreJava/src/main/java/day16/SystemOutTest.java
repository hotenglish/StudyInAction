package day16;
import java.io.*;
public class SystemOutTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream ps = null;
		try {
			ps = new PrintStream("/home/oracle/Downloads/temp.dat");
			//设置标准输出流
			System.setOut(ps);
			System.out.println("hello!");
			for(int i=0;i<10;i++){
				System.out.println("Student"+i);
			}
			
			//设置标准出错流
			System.setErr(ps);
			int i = 5;
			if(i==5){
				throw new RuntimeException("哈哈，你错了！");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
