package day16;
import java.util.* ;
public class StringTokenizerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//��ȡ�Էֺ�ð�ŷָ��ĸ����ַ���
		String str = "hello:hehe:welcome:to:tarena";
		//��һ�ַ�ʽ������StringTokenizer���󣬴������ָ���ַ����Ͳ��õķָ�����
		StringTokenizer st = new StringTokenizer(str,":");
		//��������ȡ�������ַ��� 
		while(st.hasMoreTokens()){
			System.out.println(st.nextToken());
		}
		
		System.out.println();
		//�ڶ��ַ�ʽ����split������������õķָ�����
		String s[] = str.split(":");
		//�������飬��ȡ�������ַ���
		for(String ss:s){
			System.out.println(ss);
		}
	}

}
