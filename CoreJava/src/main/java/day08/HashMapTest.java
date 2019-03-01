package day08;
import java.util.*;

public class  HashMapTest{
	public static void main(String[] args)	{
		HashMap map = new HashMap();
		map.put("guanyc","13022334466");
		map.put("guandp","13456780923");
		map.put("yangxj","13877552310");
		map.put("shangren","13734098866");
		//map里放重复的key时，会用新的value覆盖原来的value
		map.put("guanyc","13888888888");
		/*	shangren:13022334466
			yangxj:13456780923
		*/
		/*
		shangren:13734098866
		guanyc:13022334466
		yangxj:13877552310
		guandp:13456780923
		*/
		//获取key的集合
		Set set = map.keySet();
		//遍历
		Iterator it = set.iterator();
		while(it.hasNext()){
			Object o = it.next();
			//下面这种是错误的方式
			//System.out.println(it.next()+
			//	":"+map.get(it.next()));
			System.out.println(o+":"+map.get(o));
		}		
	}
}
