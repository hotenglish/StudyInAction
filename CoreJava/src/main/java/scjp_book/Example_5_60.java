package scjp_book;

public class Example_5_60 {
	
	public static void main(String[] args) {	
		byte a=1;
		byte b=2;
		int i=a+b;
		switch(i)
		{
		case 3:
		System.out.println("three");
		break;
		default:
	    System.out.println("other");
		break;		
		}
	}
}
