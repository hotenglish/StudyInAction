package day18;

import java.net.*;
import java.io.*;

public class UDPClient {
	public static void main(String[] args) {
		byte[] buffer=null;
		byte[] bufferIn=new byte[128];
		DatagramSocket ds=null;
		DatagramPacket packetOut;
		DatagramPacket packetIn;
		try {
            //1、创建信箱
			ds=new DatagramSocket(9090);
			//获取要发送的数据
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String str=br.readLine();
			buffer=str.getBytes();
			//2、创建发信封
			packetOut=new DatagramPacket(buffer,buffer.length,InetAddress.getByName("127.0.0.1"),8080);
			//3、发信
			ds.send(packetOut);
			//4、创建收信封
			packetIn=new DatagramPacket(bufferIn,buffer.length);
			//5、接收数据
			ds.receive(packetIn);
			System.out.println(new String(bufferIn,0,buffer.length));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(ds!=null)ds.close();
		}
	}
}

