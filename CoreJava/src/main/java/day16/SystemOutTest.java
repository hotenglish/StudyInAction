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
			//���ñ�׼�����
			System.setOut(ps);
			System.out.println("hello!");
			for(int i=0;i<10;i++){
				System.out.println("Student"+i);
			}
			
			//���ñ�׼������
			System.setErr(ps);
			int i = 5;
			if(i==5){
				throw new RuntimeException("����������ˣ�");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
