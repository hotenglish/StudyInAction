package day07;
import java.util.*; 

public class  TreeSetTest{
	public static void main(String[] args) 	{
		TreeSet set = new TreeSet();
		set.add(new Student("wenwl",20));
		set.add(new Student("libo",21));
		set.add(new Student("zuohy",18));
		set.add(new Student("mengwm",20));

		Iterator iter = set.iterator();
		while(iter.hasNext()){
			Object o = iter.next();
			System.out.println(o);
		}
	}
}
