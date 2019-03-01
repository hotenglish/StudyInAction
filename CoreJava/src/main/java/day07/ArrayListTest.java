package day07;
import java.util.*;

public class ArrayListTest{
	public static void main(String[] args) {
		//构建ArrayList对象
		ArrayList list = new ArrayList();

		//添加不同类型的元素
		list.add(new Integer(4));
		list.add("hello");		
		list.add(new Double(3.14));

		//可以添加重复的元素
		list.add("hello");
		list.add(new Boolean(true));
		
		//使用传统的for循环遍历
		for(int i=0;i<list.size();i++){
			Object o = list.get(i);
			System.out.println(o);
		}
		System.out.println();
		list.remove("hello");
		for(int i=0;i<list.size();i++){
			Object o = list.get(i);
			System.out.println(o);
		}

		System.out.println();
		//使用Iterator迭代器遍历
		Iterator iter = list.iterator();
		System.out.println(iter);
		while(iter.hasNext()){
			Object o = iter.next();
			System.out.println(o);
		}

	}
}

