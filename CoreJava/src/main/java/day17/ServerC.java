package day17;
import java.io.*;
import java.net.*;
/**
 * TCP/IPЭ���µ�˫��ͨ��
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
			System.out.println("����������������8888�˿�...");
			while(true){
				s = ss.accept();				
				System.out.println("�������˽��յ���������" + s.getInetAddress());
				//��ȡ���������õ��ͻ��˷��͵�����
				InputStream is = s.getInputStream();
				br = new BufferedReader(new InputStreamReader(is));
				String str = br.readLine();
				str = str.toUpperCase();
				
				//��ȡ����������ͻ���д����
				OutputStream os = s.getOutputStream();
				
				ps = new PrintStream(os);
				ps.println(str);
				ps.flush();
				System.out.println("���������ѳɹ��������ݣ�");
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







