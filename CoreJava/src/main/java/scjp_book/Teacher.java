package scjp_book;

public class Teacher extends Person1{
	
	
	public Teacher()
	{
		super();
	}
	
	public Teacher(int i)
	{
		System.out.println(i);
	}
	
	public void func()
	{
		System.out.print("2,");
	}

	public static void main(String[] args) {
		String a = "abc";
		System.out.println(a.length());
		System.out.println("-----------------------------");
		Teacher t1=new Teacher();
		Teacher t2=new Teacher(3);
	}

}

class Person1 {
	public Person1() {
		func();
	}

	public void func() {
		System.out.print("1");
	}
}
