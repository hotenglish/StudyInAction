package day08;
import java.util.*;

public class  TreeMapTest{
	public static void main(String[] args){
		TreeMap map = new TreeMap();
		
		map.put("shangren","13734098866");
		map.put("guanyc","13022334466");		
		map.put("yangxj","13877552310");
		map.put("guandp","13456780923");
		//��ȡkey�ļ���
		Set set = map.keySet();
		
		//����
		Iterator it = set.iterator();
		System.out.println(set.iterator());
		while(it.hasNext()){
			Object o = it.next();
			//���������Ǵ���ķ�ʽ
			//System.out.println(it.next()+
			//	":"+map.get(it.next()));
			System.out.println(o+":"+map.get(o));
		}		
	}
}
