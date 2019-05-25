package day16;
import java.io.*;
public class FileReraderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileReader fr = null;
		try {
			fr = new FileReader("/home/oracle/Downloads/abc.txt");
			int c = 0;
			while((c=fr.read())!=-1){
				System.out.print((char)c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(fr!=null){
					fr.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
