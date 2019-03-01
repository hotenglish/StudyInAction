package day15;
import java.io.*;
public class RandomAccessFileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("D:\\0706\\a.gif","rw");
			int data = raf.read();
            //加密算子
			int code = Integer.parseInt(args[0]);
			while(data!=-1){
				//所读字节与加密算子做异或操作
				data = data^code;
				//改变指针位置，到刚才读的地方
				raf.seek(raf.getFilePointer()-1);
				//把加密后的字节写到指针所在位置
				raf.write(data);
				//继续往下读，指针自动挪
				data = raf.read();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(raf!=null){
					raf.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
