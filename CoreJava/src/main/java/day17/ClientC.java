package day17;

import java.io.*;
import java.net.*;

/**
 * TCP/IPЭ���µ�˫��ͨ��
 * 
 * @author new
 * 
 */
public class ClientC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1", 8888);
			//�Ӽ��̻�ȡҪ��������˵�����
			BufferedReader brIn = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("������Ҫ�����������˵����ݣ�");
			String str = brIn.readLine();
			
			//��ȡ�����������������д����
			OutputStream os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
			ps.println(str);
			ps.flush();
			
			//��ȡ���������õ��������˷��ص�����
			BufferedReader br = new BufferedReader(
					new InputStreamReader(s.getInputStream()));
			System.out.println("���յ����ݣ�"+br.readLine());
			
			br.close();
			brIn.close();
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
