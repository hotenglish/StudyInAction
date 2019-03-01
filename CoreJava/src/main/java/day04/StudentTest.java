package day04;

class Person {
	private int age;
	String name = "Person_name";
	public void eat(){
		System.out.println("person...eatting....");
	}
}

class Student extends Person{
	public String name;
	private int age;
	public Student(){
	}
	public Student(String name,int age){
		this.name = name;
		this.age = age;
	}
	//String name = "Student_name";
	public void eat(){
		System.out.println("student....eating");
	}

	public void study(){
		System.out.println("student....studying....");
	}
}

public class StudentTest{
	public static void main(String args[]){
		Person p = new Student("laims",21);
		p.eat();
		System.out.println("name: "+p.name);
		//p.study();

		Student s = (Student)p;
		System.out.println("Sname: "+s.name);
		s.study();
	}
}
