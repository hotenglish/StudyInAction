package scjp_book;

public class InOut {

	String s = new String("Between");
	final String MyString = "Test Access Variable";

	public void amethod(final int iArgs) {
		int iam;
		class Bicycle {
			int inner = 0;
			public void sayHello() {
				int methodInner = 0;
				// System.out.println(iam); 不可访问方法中的iam变量。
				System.out.println(methodInner);
				System.out.println(inner);
				System.out.println(s);
				System.out.println(iArgs);
				System.out.println(MyString);
			}
		}
		Bicycle b = new Bicycle();
		b.sayHello();
	}

	public static void main(String[] args) {
		InOut io = new InOut();
		io.amethod(10);
	}

}
