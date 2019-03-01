package scjp_book;

public class Example_3_44 {

	String s="Hello";
	
	public static void main(String[] args) {
		Example_3_44 h=new Example_3_44();
		System.out.println(h.methodA("hello"));
		//h.methodB(s);
	    System.out.println(h.s);
	}
	
	public static String methodA(String s)
	{
		s.replace('e','a');
		return s+="World!!!";
	}
	
	public static void methodB(String s)
	{
		s.replace('e','a');
		s+="World!!!";
	}
}
