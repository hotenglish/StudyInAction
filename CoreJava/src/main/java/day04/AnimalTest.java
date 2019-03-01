package day04;

class Animal3 {	
	 String name = "Animal";
}

class Bird extends Animal3{
	 String name = "Bird";
}

class Fish extends Animal3{

	public void move(){
		System.out.println("fish...swimming....");
	}
}

/**
	把任何父类对象强转成子类对象，编译器都不会报错
	强制转换的前提：要转换的本身就是这个对象
	Animal a = new Bird();
	Bird b = (Bird)a;可以的
	Fish f = (Fish)a;错误
*/
public class AnimalTest{
	public static void main(String[] args) 
	{
		Animal3 a = new Bird();
		System.out.println("a.name: "+a.name);
		if(a instanceof Fish){
			Fish f = (Fish)a;
			f.move();
			System.out.println("a instanceof Fish");
		}else{
			System.out.println("!a instanceof Fish");
		}
		
		if(a instanceof Bird){
			System.out.println("a instanceof Bird");
		}else{
			System.out.println("!a instanceof Bird");
		}

		if(a instanceof Animal3){
			System.out.println("a instanceof Animal");
		}else{
			System.out.println("!a instanceof Animal");
		}
	}
}
