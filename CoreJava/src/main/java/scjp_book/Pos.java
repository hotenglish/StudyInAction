package scjp_book;

import java.util.ArrayList;

public class Pos {

	static int s;

	public void twice(int x) {
		x = x * 2;
		s = x;
	}

	public static void throwIt() {
		System.out.println("throw It");
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		Pos p = new Pos();
		Object o = p.test();
		System.out.println(o);

		System.out.println("-------------------------");
		try {
			throwIt();
		} catch (Exception e) {
			System.out.println("Caught");
		} finally {
			System.out.println("finally");
		}
		System.out.println("after");

		System.out.println("--------------------------");
		int x = 7;
		p.twice(x);
		System.out.print(x + " ");
		System.out.println(s);

		System.out.println("--------------------------");

		boolean f1 = true;
		boolean f2 = false;
		boolean f3 = true;

		if (f1 & f2 | f2 & f3 | f2)
			System.out.println("do");
		if (f1 & f2 | f2 & f3 | f2 | f3)
			System.out.println("doNkey");

	}

	Object test() {
		char[][] c = new char[2][2];
		ArrayList a = new ArrayList();
		return (Integer) 7;
	}

}
