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
			//1、创建URL
			URL url = new URL(urlName);
			
			//2、建立连接
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
			//得到数据类型
			System.out.println("ContentType："+con.getContentType());
			//得到资源位置长度
			System.out.println("ContentLength："+con.getContentLength());
			//编码方式
			System.out.println("ContentEncoding："+con.getContentEncoding());
			//时间和日期
			System.out.println("Date: "+con.getDate());
			//最后一次修改时间
			System.out.println("LastModified: "+con.getLastModified());
			
			//得到输入流，其实可以不这么复杂，可以直接用openStream方法，只是为了演示 
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			n=1;
			//这个循环最多输出30行，如果资源多于30行，只读30行，输出看看
			while((line=br.readLine())!=null && n<=30){
				System.out.println(line);
				n++;
			}
			if(line!=null){//说明后面还有
				System.out.println(".....");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

}

