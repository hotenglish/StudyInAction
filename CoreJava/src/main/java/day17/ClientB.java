package day17;
import java.net.*;
import java.io.*;
/**
 * TCP/IPЭ���µĵ���ͨ��
 * @author new
 *
 */
public class ClientB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
            //1������Socket��ָ��������IP��ַ�Ͷ˿ں�
			Socket s = new Socket("127.0.0.1",8888);
			
			//2����ȡ������
			InputStream is = s.getInputStream();
			
			//3������������װ 
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			//4����ȡ����
			String str = br.readLine();			
			System.out.println("���յ��������˵����ݣ�"+str);
			
			//�ر���Դ
			br.close();
			is.close();
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}







