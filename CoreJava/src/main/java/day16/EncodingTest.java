package day16;
import java.nio.charset.*;
import java.util.*;
public class EncodingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//��ȡ��ǰJVM֧�ֵı��뼯 
		Map m = Charset.availableCharsets();
		Set set = m.keySet();
		Iterator it = set.iterator();
		while(it.hasNext()){
			System.out.println(m.get(it.next()));
		}

		System.out.println();

		//m.forEach((t,u)->System.out.println("key:"+t+" value:"+u));
		
		//��ȡ��ǰϵͳƽ̨�ı��뼯 
		String code = System.getProperty("file.encoding");
		System.out.println("encoding: "+code);
	}
	
	
}
