package scjp_book;

class A {

	public A(int i) {

		System.out.println("A("+i+")");
	}

	public void fun(int i) {		
		System.out.println("fun(" + i + ")");
	}
}

public class B{

	static A a1;

	public B() {
		System.out.println("B()");
		a2.fun(2);
	}
	
	static {
		a1 = new A(1);
	}
	
	static A a2 = new A(2);	
	
	public void fun(int i)
	{
	  System.out.println("fun B("+i+")");	  	 
	}
	
	public static void main(String[] args) {
		new B().fun(2);
	}
}
