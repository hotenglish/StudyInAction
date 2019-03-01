package day16;
import java.io.*;
public class OutputStreamWriterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OutputStreamWriter osw = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("D:\\0706\\abc.txt");
			//读和写的时候要用一样的编码方式，否则会出现乱码。
			//如果没有指定编码方式，读写的时候采用的是系统默认的编码集
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.write("haha\nhehe\n哈哈");
			osw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(osw!=null){
					osw.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(fos!=null){
					fos.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {
			fis = new FileInputStream("D:\\0706\\abc.txt");
			isr = new InputStreamReader(fis,"UTF-8");
			int c = 0;
			while((c=isr.read())!=-1){
				System.out.print((char)c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(isr!=null){
					isr.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
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
