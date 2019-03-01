package day02;
public class LocalTest{
  static int c ;
	static boolean flag;
	public static void main(String args[]){
		int a =0;
		{
			int b = 10;
			//LocalTest t=new LocalTest();
			System.out.println("c: "+c);
			System.out.println("flag: "+flag);
		}
		//System.out.println("b:"+b);
		
	}
}