package day15;
import java.io.*;
public class DataStreamTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataOutputStream dos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("D:\\temp.dat");
			dos = new DataOutputStream(fos);			
			System.out.println("写到文件中的值：");
			byte b = 5;
			short s = 123;
			int i = 123454567;
			long l = 1234556701234L;
			float f = 1.23F;
			double d = 3.14;
			String str = "hello";
			dos.writeByte(b);
			dos.writeShort(s);
			dos.writeInt(i);
			dos.writeLong(l);
			dos.writeFloat(f);
			dos.writeDouble(d);
			dos.writeUTF(str);
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
			fis = new FileInputStream("D:\\temp.dat");
			dis = new DataInputStream(fis);		
			System.out.println("从文件中读出的值：");
			System.out.println(dis.readByte());
			System.out.println(dis.readShort());
			System.out.println(dis.readInt());
			System.out.println(dis.readLong());
			System.out.println(dis.readFloat());
			System.out.println(dis.readDouble());
			System.out.println(dis.readUTF());
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
