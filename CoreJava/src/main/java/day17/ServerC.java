package day17;
import java.io.*;
import java.net.*;
/**
 * TCP/IP协议下的双向通信
 * @author new
 *
 */
public class ServerC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket s = null;
		BufferedReader br=null;
		PrintStream ps =null;
		try {
			ss = new ServerSocket(8888);
			System.out.println("服务器已启动，在8888端口...");
			while(true){
				s = ss.accept();				
				System.out.println("服务器端接收到请求，来自" + s.getInetAddress());
				//获取输入流，得到客户端发送的数据
				InputStream is = s.getInputStream();
				br = new BufferedReader(new InputStreamReader(is));
				String str = br.readLine();
				str = str.toUpperCase();
				
				//获取输出流，往客户端写数据
				OutputStream os = s.getOutputStream();
				
				ps = new PrintStream(os);
				ps.println(str);
				ps.flush();
				System.out.println("服务器端已成功发送数据！");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(br!=null){
					br.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(s!=null){
					s.close();
				}
				if(ss!=null){
					ss.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}







