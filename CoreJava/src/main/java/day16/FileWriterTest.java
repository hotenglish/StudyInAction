package day16;
import java.io.*;
public class FileWriterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("D:\\abc.txt");
			//写文件，可以直接写字符串
			fw.write("haha\nhehe\n哈哈");
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(fw!=null){
					fw.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
