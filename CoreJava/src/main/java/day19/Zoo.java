package day19;
import java.util.*;
public class Zoo<A> {
	//private static A a;静态属性或者静态方法不能使用泛型标记
	private  A a;
	private ArrayList<A> arr;
	
	public Zoo(){
		arr = new ArrayList<A>();
		//a = new A();
	}
	
	public A getA(){
		return a;
	}
	
	public void setA(A a){
		this.a = a;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Zoo<String> z1 = new Zoo<String>();
		z1.setA("hello");
		String str = z1.getA();
		
		Zoo<Integer> z2 = new Zoo<Integer>();
		z2.setA(3);
		int i = z2.getA();
		
		Zoo z3 = new Zoo();
		z3.setA("bb");		
		String str1 = z3.getA().toString();
		
		System.out.print(str1);
		
	}

}
