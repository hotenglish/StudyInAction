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
			//����д��ʱ��Ҫ��һ���ı��뷽ʽ�������������롣
			//���û��ָ�����뷽ʽ����д��ʱ����õ���ϵͳĬ�ϵı��뼯
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.write("haha\nhehe\n����");
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
