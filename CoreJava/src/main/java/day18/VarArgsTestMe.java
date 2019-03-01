package day18;

public class VarArgsTestMe {

	public static void m(String s1,String s2)
	{
		System.out.println("m1");		
	}
	
	
	public static void m(String s1,String...s2)
	{
		System.out.println("m2");
		
	}
	public static void main(String[] args) {
		
		m("dd","dd");
		m("dd","dd","dd");
		
	}

}
