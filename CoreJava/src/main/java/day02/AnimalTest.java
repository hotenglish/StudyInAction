package day02;
/**
	private��public���η�
*/
public class AnimalTest{
	public static void main(String args[]){
		Animal a = new Animal();
		System.out.println("name: "+a.name);
		//System.out.println("age: "+a.age);
	}	
}
class Animal{
	public String name;
	private int age;
}

