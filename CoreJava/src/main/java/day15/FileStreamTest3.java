package day15;
import java.io.*;
/**
 * ʵ�ּ򵥵��ļ���������
 * @author new
 *
 */
public class FileStreamTest3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			//�����ļ�����������
			fis = new FileInputStream("/home/oracle/Downloads/temp.dat");
			//�����ļ����������
			fos = new FileOutputStream("/home/oracle/Downloads/haha.txt");
			while(fis.available()>0){
				fos.write(fis.read());
			}
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(fis!=null){
					fis.close();
				}
				if(fos!=null){
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
