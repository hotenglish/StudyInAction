package scjp_book;

class Person
{
	String name="No name";
	public Person(String nm)
	{
		name=nm;
	}
}

public class Example_6_124 extends Person{
	
	String empid="0000";
	
	public Example_6_124(String id)
	{
		super(id);
		empid=id;
	}	

	public static void main(String[] args) {
		Example_6_124 e=new Example_6_124("4321");
		System.out.println("empid:"+e.empid+" name:"+e.name);
	}
}
