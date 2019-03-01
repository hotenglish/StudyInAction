package day19;
import java.util.*;

public class GenericTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//基类型支持多态
		List<String> list = new ArrayList<String>();
		//ArrayList<Number> list1 = new ArrayList<Integer>();
		ArrayList<? extends Number> list1 = new ArrayList<Integer>();
		//list1.add("hello");
		//list1.add(new Integer(3));
		//list1.add(new Long(3));
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("hello");
		arr.add("Lost");
		m1(arr);
		
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		arr2.add(new Integer(444444444));
		m2(arr2);
		//m2(m3());
	}
	
	
	public static void m1(List<String> list){
		for(String str:list){
			System.out.println(str);
		}
	}
	
	public static void m2(List<? extends Number> list){
		System.out.println("m2:"+list.get(0));
		
	}
	
	public static List<? extends Number> m3(){
		int i = ((int)(Math.random()*12345))%4;
		switch(i){
			case(0): return new ArrayList<Integer>();
			case(1): return new ArrayList<Double>();
			case(2): return new ArrayList<Short>();
			case(3): return new ArrayList<Long>();
		}
		return null;
	}

}












