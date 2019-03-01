package day17;
import java.net.*;
import java.io.*;
/**
 * TCP/IP协议下的单向通信
 * @author new
 *
 */
public class ClientB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
            //1、创建Socket，指定服务器IP地址和端口号
			Socket s = new Socket("127.0.0.1",8888);
			
			//2、获取输入流
			InputStream is = s.getInputStream();
			
			//3、对输入流包装 
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			//4、读取数据
			String str = br.readLine();			
			System.out.println("接收到服务器端的数据："+str);
			
			//关闭资源
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







