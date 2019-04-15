package day16;

import java.io.*;

public class BufferedReaderTest {
	public static void main(String[] args) {
		BufferedWriter bw = null;
		OutputStreamWriter osw = null;
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("/home/oracle/Downloads/abc.txt");
			//����д��ʱ��Ҫ��һ���ı��뷽ʽ�������������롣
			//���û��ָ�����뷽ʽ����д��ʱ����õ���ϵͳĬ�ϵı��뼯
			osw = new OutputStreamWriter(fos,"UTF-8");
			bw = new BufferedWriter(osw);
			//�����װ
			//bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\0706\\abc.txt")));
			bw.write("haha\nhehe\n����");
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(bw!=null){
					bw.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		
		BufferedReader br = null;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {
			fis = new FileInputStream("/home/oracle/Downloads/abc.txt");
			isr = new InputStreamReader(fis,"UTF-8");
			br = new BufferedReader(isr);
			//�����װ
			//br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\0706\\abc.txt")));
			//����һ��һ���ַ����ˣ���readLine������ȡ
			/*int c = 0;
			while((c=isr.read())!=-1){
				System.out.print((char)c);
			}*/
			String str = null;
			while((str=br.readLine())!=null){
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br!=null){
					br.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
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
