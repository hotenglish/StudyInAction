package day07;
import java.util.*;

public class SetTest{
	public static void main(String[] args)	{
		//构建HashSet对象
		HashSet set = new HashSet();

		//添加不同类型的对象
		set.add(new Integer(5));
		set.add("hello");
		set.add(new Double(6.7));

		//不能添加重复的对象
		set.add(new String("hello"));
		set.add(new	Boolean(true));
		set.add(new Student("libo",20));
		set.add(new Student("Mary",20));

		
		//迭代器遍历
		Iterator it = set.iterator();
		while(it.hasNext()){
			Object o = it.next();
			System.out.println(o);
		}
		System.out.println();
		set.remove("hello");
		Iterator iter = set.iterator();
		while(iter.hasNext()){
			Object o = iter.next();
			System.out.println(o);
		}
		
		Student s = new Student();
		System.out.println("hashCode: "+s.hashCode());
	}
}
