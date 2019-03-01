package day19;
import java.util.*;
public class GenericTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> arr = new ArrayList<String>();
		//只能存放String类型
		arr.add("hello");
		//arr.add(new Integer(3));
		
		for(int i=0;i<arr.size();i++){
			// 取出元素时，不需要强转
			//Object o = arr.get(i);
			//String s = (String)o;
			String str = arr.get(i);
			System.out.println(arr);
		}
		
		for(String str:arr){
			System.out.println(str);
		}
		
		//迭代器后也需要带泛型标记
		Iterator<String> it = arr.iterator();
		while(it.hasNext()){
			String str = it.next();
			System.out.println(str);
		}
		
		
	}

}
