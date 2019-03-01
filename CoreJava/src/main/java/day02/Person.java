package day02;
/**
	值传递与引用传递
*/
public class Person{
	private static int age=22;
	public static void addAge(int age){		
		//Person.age++;
		age++;
	}
	public static void addAge(Person p){
		p.age++;
	}
	public static void main(String args[]){
		addAge(22);
		System.out.println("age1: "+age);//22
		Person p = new Person();
		addAge(p);
		System.out.println("age2: "+age);//23
	}
}



