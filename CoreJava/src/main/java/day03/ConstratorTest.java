package day03;

public class ConstratorTest{
	int age = 30;
	String name = null;
	public ConstratorTest(String name,int age){
		System.out.println("name...age...ConstractorTest");
		this.name = name;
		this.age = age;
	}
	public ConstratorTest(){
		System.out.println("call ConstratorTest.........");
	}
	public static void main(String args[]){
		ConstratorTest test1 = new ConstratorTest();
		int age= 20;
		ConstratorTest test2 = new ConstratorTest("Mary",20);
		ConstratorTest test3 = new ConstratorTest();
	}
}

class TestConstractor{
	ConstratorTest test = new ConstratorTest();
}