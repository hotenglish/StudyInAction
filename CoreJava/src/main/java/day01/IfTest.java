package day01;

public class IfTest{
	
	public static void main(String args[]){
		double a = 2.0;
		double b = 1.1;
		double c = 0.9;
		if(a-b==c){
			System.out.println("a-b=c");
		}else if(a-b>c){
			System.out.println("a-b>c");
		}else {
			System.out.println("a-b<c");
		}
	}
}
