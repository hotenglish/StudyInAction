package day15;
import java.io.*;
public class FileStreamTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("D:\\temp.dat");
			while(fis.available()>0){
				System.out.print((char)fis.read());
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(fis!=null){
					fis.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
