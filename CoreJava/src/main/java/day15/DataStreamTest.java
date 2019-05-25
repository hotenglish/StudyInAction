package day15;
import java.io.*;
public class DataStreamTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataOutputStream dos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("/home/oracle/Downloads/temp.dat");
			dos = new DataOutputStream(fos);
			
			int i=0;
			System.out.println("写到文件中的值：");
			while(i<10){
				double d = Math.random();
				System.out.println(d);
				dos.writeDouble(d);
				i++;
			}
			dos.flush();
			System.out.println();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(dos!=null){
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		DataInputStream dis = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("/home/oracle/Downloads/temp.dat");
			dis = new DataInputStream(fis);
			int i=0;
			System.out.println("从文件中读出的值：");
			while(i<10){
				double d = dis.readDouble();
				System.out.println(d);
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(dis!=null){
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
