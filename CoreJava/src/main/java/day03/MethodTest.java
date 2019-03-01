package day03;

public class MethodTest{
	public int getCount(int n,String s){
		//......
		return 4;
	}
	public static void main(String args[]){
		MethodTest test = new MethodTest();
		int s = test.getCount(3,"hello");
		System.out.println(s);
	}
}