package day16;
import java.nio.charset.*;
import java.util.*;
public class EncodingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//获取当前JVM支持的编码集 
		Map m = Charset.availableCharsets();
		Set set = m.keySet();
		Iterator it = set.iterator();
		while(it.hasNext()){
			System.out.println(m.get(it.next()));
		}

		System.out.println();

		//m.forEach((t,u)->System.out.println("key:"+t+" value:"+u));
		
		//获取当前系统平台的编码集 
		String code = System.getProperty("file.encoding");
		System.out.println("encoding: "+code);
	}
	
	
}
