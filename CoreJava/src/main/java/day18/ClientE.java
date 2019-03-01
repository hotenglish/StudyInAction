package day18;

import java.net.*;
import java.io.*;

public class ClientE{
	static boolean isRun = true;
	public void witer(){
		
	}
	public static void main(String[] args) {
		Socket s=null;
		PrintStream ps=null;
		BufferedReader brin=null;
		try {
			s=new Socket("127.0.0.1",8888);
			ps=new PrintStream(s.getOutputStream());
			brin=new BufferedReader(new InputStreamReader(s.getInputStream())); 
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			Thread t=new ClientThread(brin);
			t.start();
			while(true){				
				System.out.println("请输入发往服务器的内容：");
				String str=br.readLine();
				ps.println(str);
				ps.flush();
				if(str.equals("quit")){
					isRun = false;
					break;
				}				
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(ps!=null)ps.close();
			if(s!=null)try{s.close();}catch(IOException e){}
		}

	}

}
class ClientThread extends Thread{
	BufferedReader br;
	public ClientThread(BufferedReader br){
		this.br=br;
	}
	public void run(){
		while(ClientE.isRun){
			try {			
				String str = br.readLine();
				if(str==null || str.equals("")){
					continue;
				}				
				System.out.println(str);
							
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

