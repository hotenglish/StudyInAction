package day16;

import java.io.*;

public class BufferedReaderTest {
	public static void main(String[] args) {
		BufferedWriter bw = null;
		OutputStreamWriter osw = null;
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("/home/oracle/Downloads/abc.txt");
			//读和写的时候要用一样的编码方式，否则会出现乱码。
			//如果没有指定编码方式，读写的时候采用的是系统默认的编码集
			osw = new OutputStreamWriter(fos,"UTF-8");
			bw = new BufferedWriter(osw);
			//三层包装
			//bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\0706\\abc.txt")));
			bw.write("haha\nhehe\n哈哈");
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
			//三层包装
			//br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\0706\\abc.txt")));
			//不用一个一个字符读了，用readLine方法读取
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
