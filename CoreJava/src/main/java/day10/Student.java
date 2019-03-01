package day10;


public final class Student extends Person implements Comparable{
	private  String name ;
	public  int age ;
	public Student(){		
	}
	public Student(String name,int age) {
		this.name = name;
		this.age = age;
	}
	
	public String playGame(String name) throws RuntimeException{
		return "CS";
	}
	
	public void move(){
		System.out.println("moving.....");
	}
	
	public int compareTo(Object o) {		
		return 0;
	}

}

class Person{
	
}
