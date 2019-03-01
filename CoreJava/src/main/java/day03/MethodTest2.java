package day03;
public class MethodTest2{
	public static void main(String args[]){
		int number = 3;
		MethodTest2 test = new MethodTest2();
		test.change(number);
		System.out.println("number£º"+number);
	}
	public void change(int n){
		n=6;
	}
}
