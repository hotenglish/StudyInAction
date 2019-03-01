package scjp_book;

public class Example_2_84 {

	
	public static void main(String[] args) {
	
		Example_2_84 x,y,z;
		long l=10;
		short s=10;
		x=new Example_2_84();
		y=new Example_2_84();
		z=y;
		System.out.println(y==z);
		System.out.println(l==10.0);
		System.out.println(l==s);
	}
}
