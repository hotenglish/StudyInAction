package day18;

import java.net.*;

public class UDPServer {
	public static void main(String[] args) {
		DatagramSocket ds=null;
		DatagramPacket packetIn;
		DatagramPacket packetOut;
		byte[] bufferIn=new byte[128];
		try {
			//1、创建信箱 
			ds=new DatagramSocket(8080);
			while(true){
				//2、创建收信封
				packetIn=new DatagramPacket(bufferIn,bufferIn.length);
				//3、收信
				ds.receive(packetIn);
				//4、对收到的数据做处理
				String str=new String(bufferIn);
				str=str.toUpperCase();
				//5、创建发信封
				packetOut=new DatagramPacket(str.getBytes(),str.getBytes().length,packetIn.getAddress(),packetIn.getPort());
				//6、发信
				ds.send(packetOut);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(ds!=null)ds.close();
		}

	}

}
