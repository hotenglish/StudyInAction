package day05;
public class Test5 implements InterfaceC {
	
	public static void main(String args[]){	
		Test5 t = new Test5();
		t.test1();
	}	
	public void test3(){
		System.out.println("test in Test5");
	}

	public void test1(){
		System.out.println("test1 in Test5");
	}

	public void test2(int a){
		System.out.println("test2 in Test5");
	}

}