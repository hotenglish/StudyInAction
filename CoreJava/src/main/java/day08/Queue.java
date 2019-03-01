package day08;
import java.util.*;

public class Queue implements Iterable{
	private LinkedList list;
	public Queue(){
		list = new LinkedList();//实例化list
	}	
	
	//添加
	public boolean in(Object o){
		return list.add(o);
	}
	
	//取（移）出元素
	public Object out(){
		if(list.size()>0){
			return list.remove(0);
		}else
			return null;
	}

	//覆盖toString方法
	public String toString(){
		/*StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int i=0;i<list.size();i++){
			sb.append(list.get(i)+",");
		}
		sb.append("]");
		return sb.toString();*/
		//直接调用ArrayList的toString方法
		return list.toString();
	}
	public static void main(String args[]){
		Queue q = new Queue();
		q.in("haha");
		q.in("xixi");
		q.in("hehe");
		q.in("hello");
		q.in("nihao");
		System.out.println(q);
		Iterator it = q.iterator();
		while(it.hasNext()){
			Object o = it.next();
			System.out.println(o);
		}

		q.out();
		q.out();
		System.out.println(q);
	}

	public Iterator iterator(){
		//直接调用ArrayList的iterator方法
		//return list.iterator();

		//自己写Iterator的实现类，实现可迭代
		return new IteratorTest(list);
	}
}

class IteratorTest implements Iterator{
	//相当于指针（辅助变量）
	int i=0;
	private LinkedList list;
	//用于Queue里的list传递过来
	public IteratorTest(LinkedList list){
		this.list = list;
	}
	
	//是否有下一个元素
	public boolean hasNext(){
		return i<list.size();		
	}
	
	//返回跳过的元素
	public Object next(){
		i++;
		return list.get(i-1);		
	}
	
	//删除跳过的元素
	public void remove(){
		list.remove(i-1);		
	}
}
