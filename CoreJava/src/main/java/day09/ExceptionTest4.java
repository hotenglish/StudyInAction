package day09;
import java.io.*;
class ExceptionTest4{
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}


class Sub1 extends Super1{
	//子类覆盖父类的方法，抛出的异常不能比父类更宽广
	void test() throws FileNotFoundException {
	}
}

class Super1{
	void test() throws IOException{
	}
}
