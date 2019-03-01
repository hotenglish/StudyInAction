package scjp_book;

class Example1 {
	public Example1 foo() {
		System.out.println("Example1 foo()");
		return this;
	}
}

class Example2 extends Example1 {
	public Example2 foo() {
		System.out.println("Example2 foo()");
		return this;
	}
}

class Example3 extends Example2 {
	public Example3 foo() {
		System.out.println("Example3 foo()");
		return this;
	}
}

public class Example_6_107 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Example3 e3=new Example3();
		e3.foo();
		Example2 e2=new Example2();
		e2.foo();
	}

}
