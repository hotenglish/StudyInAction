package scjp_book;

import java.io.IOException;

class Two {

	public Two() throws Exception {
		System.out.println("Excuting class A Constructor!");
		throw new IOException();
	}
}

public class One extends Two {

	public One() throws Exception {
		super();
		System.out.println("Excuting class A Constructor!");
	}

	public static void main(String[] args) {
		Two a;
		try {
			a = new One();
			System.out.println(a.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
