package day17;
import java.io.IOException;
import java.net.*;
import java.io.*;
/**
 * TCP/IPЭ���µĵ���ͨ��
 * @author new
 *
 */
public class ServerA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//1������ServerSocket��ָ���˿�8888
			ServerSocket ss = new ServerSocket(8888);
			System.out.println("����������������8888�˿�...");
			
			//2���������󣬽��յ�����֮�󣬷���Socket����
			Socket s = ss.accept();
			System.out.println("�������˽��յ���������"+s.getInetAddress());
			
			//3����ȡ�����
			OutputStream os = s.getOutputStream();
			
			//4�������������װ
			PrintStream ps = new PrintStream(os);
			
			//5��д����
			ps.println("hello,ClientA!");
			ps.flush();
			System.out.println("���������ѷ������ݣ�");
			
			//6���ر���Դ
			ps.close();
			s.close();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}








