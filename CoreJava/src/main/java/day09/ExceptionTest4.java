package day09;
import java.io.*;
class ExceptionTest4{
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}


class Sub1 extends Super1{
	//���า�Ǹ���ķ������׳����쳣���ܱȸ�������
	void test() throws FileNotFoundException {
	}
}

class Super1{
	void test() throws IOException{
	}
}
