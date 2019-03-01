package day03;

public class Bird extends Animal{
	
	//private static String name=" kk";
	
	public static void main(String args[]){
		Bird d = new Bird();
		d.setName("d");
		Bird e = new Bird();	
		e.setName("e");
		d.sing();
		System.out.println("nameD: "+d.getName());//d
		System.out.println("nameE: "+e.getName());//abc
	}
	public void sing(){
		//Bird d = new Bird();
		String s1 = this.name;
		String s2 = super.name;
		System.out.println("s1: "+s1);
		System.out.println("s2: "+s2);
		System.out.println("singing...");
		dance();
	}

	public void dance(){
	}
}


class Animal{
    String name = "abc";
	private int age;
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public Animal(){
	}
	public Animal(String name,int age){
		this.name = name;
		this.age = age;
	}
}
