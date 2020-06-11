package day15;
import java.io.*;
public class FileStreamTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileInputStream fis = null;
		//��ȡ��ǰϵͳƽ̨��·���ָ��
		System.out.println(File.pathSeparator);
		//��ȡ��ǰϵͳƽ̨��Ŀ¼�ָ���
		System.out.println(File.separator);
		String pathName = "/home/oracle"+File.separator+"Downloads"+File.separator+"temp.dat";
		///home/oracle/Downloads/temp.dat
		try {
			fis = new FileInputStream(pathName);
			while(fis.available()>0){
				System.out.print((char)fis.read());
			}
		} catch (FileNotFoundException e) {
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