package day18;

import java.net.*;

public class UDPServer {
	public static void main(String[] args) {
		DatagramSocket ds=null;
		DatagramPacket packetIn;
		DatagramPacket packetOut;
		byte[] bufferIn=new byte[128];
		try {
			//1���������� 
			ds=new DatagramSocket(8080);
			while(true){
				//2���������ŷ�
				packetIn=new DatagramPacket(bufferIn,bufferIn.length);
				//3������
				ds.receive(packetIn);
				//4�����յ�������������
				String str=new String(bufferIn);
				str=str.toUpperCase();
				//5���������ŷ�
				packetOut=new DatagramPacket(str.getBytes(),str.getBytes().length,packetIn.getAddress(),packetIn.getPort());
				//6������
				ds.send(packetOut);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(ds!=null)ds.close();
		}

	}

}
