package day03;

class Person{
	private String name;
	private int age;	
	
	public Person()
	{
		
	}
	
	public Person(String name,int age){
		this.name = name;
		this.age = age;
	}	
	
	
	public String getName()
	{
		return name;
	}
}

public class Student extends Person{
	private String name;
	private int age;
	
	public Student(String name){
		super("  k",20);
		this.name = name;
	}
	public Student(String name,int age){	
		this(name);
		this.age = age;	
	}
	
	public String getName()
	{
		return name;
	}
	
	public static void main(String args[])
	{
		Student s=new Student("laims");
		System.out.println("Name:"+s.getName());		
	}
}