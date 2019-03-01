package day02;

/**
	演示构造方法
*/
public class Student{
	private String name;
	private int age;
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getAge(){
		return age;
	}
	public void setAge(int age){		
		this.age = age;
	}
	
	
	public Student()
	{
		this.name="myname";
		this.age=0;
	}
	
	public Student(String name,int age){
		this.name = name;
		this.age = age;
	}

	
	public  void sleep(){
	    int age = 34;
		System.out.println(this.age);
		System.out.println(age);
	}
	public void playGame(){
	}

	public static void main(String args[]){
		Student s = new Student();
		s.sleep();
		Student s1 = new Student("liling",20);
		Student s2 = new Student("xuecan",18);
		System.out.println("name: "+s1.name);
		System.out.println("age: "+s1.age);		
	}
}

