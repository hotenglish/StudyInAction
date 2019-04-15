package day17;
import java.io.*;
import java.net.*;
/**
 * TCP/IP协议下的双向通信
 * @author new
 *
 */
public class ServerD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket s = null;
		//BufferedReader br=null;
		//PrintStream ps =null;
		try {
			ss = new ServerSocket(8888);
			System.out.println("服务器已启动，在8888端口...");
			while(true){
				s = ss.accept();				
				System.out.println("服务器端接收到请求，来自" + s.getInetAddress());
				new ServerThread(s).start();				
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
/*				if(br!=null){
					br.close();
				}
				if(ps!=null){
					ps.close();
				}*/
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

class ServerThread extends Thread{
	Socket s;
	BufferedReader br;
	PrintStream ps;
	public ServerThread(Socket s){
		this.s = s;
		InputStream is;
		try {
			is = s.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			OutputStream os = s.getOutputStream();		
			ps = new PrintStream(os);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	public void run(){		
		String str;
		try {
			str = br.readLine();
			str = str.toUpperCase();	
			
			ps.println(str);
			ps.flush();
			System.out.println("服务器端已成功发送数据！");
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(s!=null){
					s.close();
				}
				if(br!=null){
					br.close();
				}
				if(ps!=null){
					ps.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}





