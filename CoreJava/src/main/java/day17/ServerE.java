package day17;
import java.net.*;
import java.util.HashSet;
import java.io.*;
public class ServerE {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ServerSocket ss = null;
		Socket s = null;
		HashSet allSockets = new HashSet();
		try {
			ss = new ServerSocket(8888);
			while(true){
				s = ss.accept();
				allSockets.add(s);
				new ServerThread2(s,allSockets).start();
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			ss.close();
			s.close();
		}

	}

}
class ServerThread2 extends Thread{
	Socket s ;
	HashSet allSockets;
	public ServerThread2(Socket s,HashSet allSockets){
		this.s = s;
		this.allSockets = allSockets;
	}
	public void run(){
//		PrintStream ps = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			while(true){
				String str = br.readLine();
				//����ͻ���Ҫ�˳���������ѭ���������ˡ�
				if(str.equalsIgnoreCase("quit")){
					System.out.println(s.getInetAddress()+"�˳����ӣ�");
					allSockets.remove(s);
					s.close();
					break;
				}
				System.out.println(s.getInetAddress()+":"+str);
				//���˳����������еĿͻ���Sockets
				for(Object obj :allSockets){
					Socket soc = (Socket)obj;
					PrintStream ps = new PrintStream(soc.getOutputStream());
					ps.println(s.getInetAddress()+":"+str);
					ps.flush();
				}				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
//			ps.close();
		}
	}
}




















