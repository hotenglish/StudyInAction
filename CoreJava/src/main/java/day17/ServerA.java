package day17;
import java.io.IOException;
import java.net.*;
import java.io.*;
/**
 * TCP/IP协议下的单向通信
 * @author new
 *
 */
public class ServerA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//1、创建ServerSocket，指定端口8888
			ServerSocket ss = new ServerSocket(8888);
			System.out.println("服务器已启动，在8888端口...");
			
			//2、监听请求，接收到请求之后，返回Socket对象
			Socket s = ss.accept();
			System.out.println("服务器端接收到请求，来自"+s.getInetAddress());
			
			//3、获取输出流
			OutputStream os = s.getOutputStream();
			
			//4、对输出流做包装
			PrintStream ps = new PrintStream(os);
			
			//5、写数据
			ps.println("hello,ClientA!");
			ps.flush();
			System.out.println("服务器端已发送数据！");
			
			//6、关闭资源
			ps.close();
			s.close();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}








