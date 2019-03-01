package scjp_book;

public class Example_5_57 {

	static void test() throws Exception {
		if (true) {
			throw new Exception();
		}
		System.out.println("test");
	}

	public static void main(String[] args) {
		try {
			test();
		} catch (Exception ex) {
			System.out.println("Exception");
		}
		System.out.println("End");

	}

}
