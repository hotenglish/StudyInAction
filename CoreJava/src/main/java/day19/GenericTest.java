package day19;
import java.util.*;
public class GenericTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> arr = new ArrayList<String>();
		//ֻ�ܴ��String����
		arr.add("hello");
		//arr.add(new Integer(3));
		
		for(int i=0;i<arr.size();i++){
			// ȡ��Ԫ��ʱ������Ҫǿת
			//Object o = arr.get(i);
			//String s = (String)o;
			String str = arr.get(i);
			System.out.println(arr);
		}
		
		for(String str:arr){
			System.out.println(str);
		}
		
		//��������Ҳ��Ҫ�����ͱ��
		Iterator<String> it = arr.iterator();
		while(it.hasNext()){
			String str = it.next();
			System.out.println(str);
		}
		
		
	}

}
