package day17;

import java.net.*;
import java.io.*;
public class ClientE{
	public static void main(String[] args) {
		Socket s=null;
		PrintStream ps=null;
		BufferedReader brin=null;
		try {
			s=new Socket("127.0.0.1",8888);
			ps=new PrintStream(s.getOutputStream());
			brin=new BufferedReader(new InputStreamReader(s.getInputStream())); 
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			while(true){
				System.out.println("请输入发往服务器的内容：");
				String str=br.readLine();
				ps.println(str);
				ps.flush();
				if(str.equals("quit"))
					break;
				System.out.println("服务器端发回的数据："+brin.readLine());
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(ps!=null)ps.close();
			if(s!=null)try{s.close();}catch(IOException e){}
		}

	}

}

