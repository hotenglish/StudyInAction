package day15;
import java.io.*;
/**
 * 实现简单的文件拷贝功能
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
			//构造文件输入流对象
			fis = new FileInputStream("D:\\0706\\abc.txt");
			//构造文件输出流对象
			fos = new FileOutputStream("D:\\0706\\haha.txt");
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
