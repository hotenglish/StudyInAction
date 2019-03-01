package scjp_book;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Example_8_23 {

	public static void main(String[] args) {

		Example_8_23 e = new Example_8_23();
		System.out.println(e.amethod());
	}

	public int amethod() {
		try {
			FileInputStream dis = new FileInputStream("hello.txt");
		} catch (FileNotFoundException fne) {
			System.out.println("No such file found!");
			return -1;
		} catch (IOException ioe) {
		} finally {
			System.out.println("Doing finally");
			//return -2;
		}
		return 0;
	}
}
