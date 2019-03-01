package day17;

import java.io.IOException;
import java.net.*;
import java.io.*;

/**
 * TCP/IPЭ���µĵ���ͨ��
 * �������˿ɽ��ն������
 * @author new
 * 
 */
public class ServerB {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket s = null;
		PrintStream ps = null;
		try {
			// 1������ServerSocket��ָ���˿�8888
			ss = new ServerSocket(8888);
			System.out.println("����������������8888�˿�...");

			// 2���������󣬽��յ�����֮�󣬷���Socket����
			while (true) {
				s = ss.accept();
				System.out.println("�������˽��յ���������" + s.getInetAddress());

				// 3����ȡ�����
				OutputStream os = s.getOutputStream();

				// 4�������������װ
				ps = new PrintStream(os);

				// 5��д����
				ps.println("hello,ClientA!");
				ps.flush();
				System.out.println("���������ѷ������ݣ�");

				// 6���ر���Դ				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(ps!=null){
				ps.close();
			}
			if(s!=null){
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(ss!=null){
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}

	}

}
