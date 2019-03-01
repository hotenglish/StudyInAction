package day03;
public class OverloadTest{

//    public double test1(byte b,short s){
//		System.out.println("double test1...");
//		return 0;
//	}

	public void test1(byte b,short s){
		//.......
		System.out.println("void test1");
	}

//	public void test1(short s,byte d){
//		System.out.println("short....short...");		
//	}

	public static void main(String args[]){
		OverloadTest t = new OverloadTest();
		byte b1 = 2;
		byte b2 = 3;	
		t.test1(b1,b2);
	}
}