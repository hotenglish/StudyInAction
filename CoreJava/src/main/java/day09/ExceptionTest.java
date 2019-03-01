package day09;
/**
	常见的未检查异常
*/
public class  ExceptionTest{
	public static void main(String[] args){
		int num = Integer.parseInt(args[0]);
		//java.lang.ArithmeticException 5/0
		int n = 5/num;

		//java.lang.NullPointerException
		OuterE out = null;
		//out.outerE();//

		//java.lang.ArrayIndexOutOfBoundsException
		int arr[] = new int[5];
		//System.out.println(arr[5]);

		//Java.lang.NegativeArraySizeException 
		//int a[] = new int[-4];	
		
		//java.lang.ClassCastException
		Super s = new Bird();
		//Fish f = (Fish)s;

	}
}

class Bird extends Super{
}

class Fish extends Super{
}