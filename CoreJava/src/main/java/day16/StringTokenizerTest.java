package day16;
import java.util.* ;
public class StringTokenizerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//获取以分号冒号分隔的各个字符串
		String str = "hello:hehe:welcome:to:tarena";
		//第一种方式，构造StringTokenizer对象，传入欲分割的字符串和采用的分隔符号
		StringTokenizer st = new StringTokenizer(str,":");
		//遍历，获取各个子字符串 
		while(st.hasMoreTokens()){
			System.out.println(st.nextToken());
		}
		
		System.out.println();
		//第二种方式，用split方法，传入采用的分隔符号
		String s[] = str.split(":");
		//遍历数组，获取各个子字符串
		for(String ss:s){
			System.out.println(ss);
		}
	}

}
