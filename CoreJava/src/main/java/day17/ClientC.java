package day17;

import java.io.*;
import java.net.*;

/**
 * TCP/IP协议下的双向通信
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
			//从键盘获取要发往服务端的数据
			BufferedReader brIn = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("请输入要发往服务器端的数据：");
			String str = brIn.readLine();
			
			//获取输出流，往服务器端写数据
			OutputStream os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
			ps.println(str);
			ps.flush();
			
			//获取输入流，得到服务器端返回的数据
			BufferedReader br = new BufferedReader(
					new InputStreamReader(s.getInputStream()));
			System.out.println("接收的数据："+br.readLine());
			
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
