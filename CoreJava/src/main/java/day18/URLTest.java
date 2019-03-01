package day18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String urlName = "http://news.sina.com.cn";
		if(args.length>0){
			urlName = args[0];
		}		
		
		try {
			//1������URL
			URL url = new URL(urlName);
			
			//2����������
			URLConnection con = url.openConnection();
			con.connect();
			
			//
			int n=1;
			String key;
			while((key=con.getHeaderFieldKey(n))!=null){
				String value = con.getHeaderField(key);
				System.out.println(value);
				n++;
			}
			
			System.out.println("-------------------------------");
			//�õ���������
			System.out.println("ContentType��"+con.getContentType());
			//�õ���Դλ�ó���
			System.out.println("ContentLength��"+con.getContentLength());
			//���뷽ʽ
			System.out.println("ContentEncoding��"+con.getContentEncoding());
			//ʱ�������
			System.out.println("Date: "+con.getDate());
			//���һ���޸�ʱ��
			System.out.println("LastModified: "+con.getLastModified());
			
			//�õ�����������ʵ���Բ���ô���ӣ�����ֱ����openStream������ֻ��Ϊ����ʾ 
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			n=1;
			//���ѭ��������30�У������Դ����30�У�ֻ��30�У��������
			while((line=br.readLine())!=null && n<=30){
				System.out.println(line);
				n++;
			}
			if(line!=null){//˵�����滹��
				System.out.println(".....");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

}

