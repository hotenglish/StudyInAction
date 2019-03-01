package scjp_book;

public class Example_3_38 extends Use{

	@Override
	void amethod() {		
		
	}
	int[] ar;
	
	//Integer c;
	
    boolean c;

	public static void main(String[] args) {
	 //int[] ar=new int[5];
		Example_3_38 e=new Example_3_38();
		System.out.println(e.c);
		print("myname");
//	 for(i=0;i<e.ar.length;i++)
//	 {
//		 System.out.println(e.ar[i]);
//	 }
	}
	
	public static void print(String n)
	{
	 String name=n;
	 System.out.print("name:"+name);
	}
}

abstract class Use{
	abstract void amethod();
	static int i;	
}
