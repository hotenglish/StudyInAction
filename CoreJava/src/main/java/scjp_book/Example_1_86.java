package scjp_book;

public class Example_1_86 {

	
	public static void main(String[] args) {	
		Example_1_86 e=new Example_1_86();
		int i=0,v;
		e.fermin(i);
		i=i++;
		v=i;
		System.out.println(v);		
	}
	
	void fermin(int i)
	{
		i++;
	}
}
