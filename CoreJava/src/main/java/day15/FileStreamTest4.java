package day15;

import java.io.*;
/**
 * 实现简单的文件加密解密
 * @author new
 *
 */
public class FileStreamTest4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//D:\0706\a.gif  D:\0706\b.gif  20
		if(args==null || args.length<3){
			throw new RuntimeException("请传入三个参数！");
		}
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(args[0]);
			fos = new FileOutputStream(args[1]);
			//获取加密算子
			int code = Integer.parseInt(args[2]);
			while(fis.available()>0){
				//所读的字节与加密算子进行异或，加密
				fos.write(fis.read()^code);
			}
			fos.flush();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
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






