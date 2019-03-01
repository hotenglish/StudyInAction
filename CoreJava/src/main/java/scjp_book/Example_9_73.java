package scjp_book;

public class Example_9_73 {

	
	public static void main(String[] args) {
		Boolean b1=new Boolean(true);
		Boolean b2=new Boolean(true);
		boolean b3=true;
		boolean b4=true;
		System.out.println(b1==b2);
		System.out.println(b1==b3);
		System.out.println(b3==b4);
		System.out.println(b1==b4);
	}

}
