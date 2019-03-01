package day07;
import java.util.*;

public class SetTest{
	public static void main(String[] args)	{
		//����HashSet����
		HashSet set = new HashSet();

		//��Ӳ�ͬ���͵Ķ���
		set.add(new Integer(5));
		set.add("hello");
		set.add(new Double(6.7));

		//��������ظ��Ķ���
		set.add(new String("hello"));
		set.add(new	Boolean(true));
		set.add(new Student("libo",20));
		set.add(new Student("Mary",20));

		
		//����������
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
