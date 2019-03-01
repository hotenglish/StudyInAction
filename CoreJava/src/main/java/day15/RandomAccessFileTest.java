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
            //��������
			int code = Integer.parseInt(args[0]);
			while(data!=-1){
				//�����ֽ������������������
				data = data^code;
				//�ı�ָ��λ�ã����ղŶ��ĵط�
				raf.seek(raf.getFilePointer()-1);
				//�Ѽ��ܺ���ֽ�д��ָ������λ��
				raf.write(data);
				//�������¶���ָ���Զ�Ų
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
