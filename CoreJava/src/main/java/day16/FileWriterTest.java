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
			//д�ļ�������ֱ��д�ַ���
			fw.write("haha\nhehe\n����");
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
