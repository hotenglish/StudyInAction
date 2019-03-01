package day08;
import java.util.*;

public class Queue implements Iterable{
	private LinkedList list;
	public Queue(){
		list = new LinkedList();//ʵ����list
	}	
	
	//���
	public boolean in(Object o){
		return list.add(o);
	}
	
	//ȡ���ƣ���Ԫ��
	public Object out(){
		if(list.size()>0){
			return list.remove(0);
		}else
			return null;
	}

	//����toString����
	public String toString(){
		/*StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int i=0;i<list.size();i++){
			sb.append(list.get(i)+",");
		}
		sb.append("]");
		return sb.toString();*/
		//ֱ�ӵ���ArrayList��toString����
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
		//ֱ�ӵ���ArrayList��iterator����
		//return list.iterator();

		//�Լ�дIterator��ʵ���࣬ʵ�ֿɵ���
		return new IteratorTest(list);
	}
}

class IteratorTest implements Iterator{
	//�൱��ָ�루����������
	int i=0;
	private LinkedList list;
	//����Queue���list���ݹ���
	public IteratorTest(LinkedList list){
		this.list = list;
	}
	
	//�Ƿ�����һ��Ԫ��
	public boolean hasNext(){
		return i<list.size();		
	}
	
	//����������Ԫ��
	public Object next(){
		i++;
		return list.get(i-1);		
	}
	
	//ɾ��������Ԫ��
	public void remove(){
		list.remove(i-1);		
	}
}
