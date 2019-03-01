package day18;
import java.util.*;
public class ForEachTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set set = new HashSet();
		set.add("hello");
		set.add(new Double(3.5));
		set.add(new Integer(8));
		for(Object obj:set){
			System.out.println(obj);
		}
	}

}








