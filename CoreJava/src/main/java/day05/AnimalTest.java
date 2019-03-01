package day05;
class Animal{
	//7152438
	String name;
	static{
		System.out.println("1 Animal...static block..");//1
	}
	public Animal(String name){
		this.name = name;
		System.out.println("2 Animal...costractor..");//2
	}
	public static void addCount(){
		System.out.println("3 Animal...addCount..");//3
	}	
}

class Bird extends Animal{
	public Bird(String name){
		super(name);
		System.out.println("4 Bird...costractor..");//4
	}
	static{
		System.out.println("5 Bird...static block..");//5
	}
	public static void addCount(){
		System.out.println("6 Bird....addCount.");//6
	}

	
}

public class AnimalTest{
	public static void main(String args[]){
		System.out.println("7 start main......");//7
		Bird a = new Bird("iop");		
		a.addCount();
		System.out.println("8 after main......");//8
	}
}
