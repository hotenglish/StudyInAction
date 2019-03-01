package day19;

import java.util.ArrayList;
import java.util.Iterator;


public class MyStack<E> implements Iterable<E> {
	private ArrayList<E> list;
	public MyStack(){
		list = new ArrayList<E>();
	}
	
	public boolean push(E e){
		return list.add(e);
	}
	
	public E pop(){
		return list.remove(list.size()-1);//移出最后添加到元素
	}
	
	public Iterator<E> iterator(){
		return list.iterator();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyStack<String> ms = new MyStack<String>();
		ms.push("hello");
		ms.push("hehe");
		ms.push("haha");
		Iterator<String> it = ms.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		//ms.push(5);
		System.out.println(ms.pop());
	}

}

