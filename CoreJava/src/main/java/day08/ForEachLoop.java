package day08;
import java.util.*;
public class  ForEachLoop{
	public static void main(String[] args) {
		HashSet set = new HashSet();
		set.add("hello");
		set.add(new Integer(7));
		set.add("world");
		set.add(new Double(9.8));
		//for each—≠ª∑
		for(Object o:set){
			System.out.println(o);
		}
	}
}
