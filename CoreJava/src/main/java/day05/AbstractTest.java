package day05;
public abstract class AbstractTest{
	public abstract void move();
	public abstract void test();	
}


class Test4 extends AbstractTest{
	public void test(){
		System.out.println("test....in Test4.");
	}
	public void move(){
		System.out.println("move....in Test4.");
	}
}
