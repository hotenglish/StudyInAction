package day07;
import java.util.*;

public class ArrayListTest{
	public static void main(String[] args) {
		//����ArrayList����
		ArrayList list = new ArrayList();

		//��Ӳ�ͬ���͵�Ԫ��
		list.add(new Integer(4));
		list.add("hello");		
		list.add(new Double(3.14));

		//��������ظ���Ԫ��
		list.add("hello");
		list.add(new Boolean(true));
		
		//ʹ�ô�ͳ��forѭ������
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
		//ʹ��Iterator����������
		Iterator iter = list.iterator();
		System.out.println(iter);
		while(iter.hasNext()){
			Object o = iter.next();
			System.out.println(o);
		}

	}
}

