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
	���κθ������ǿת��������󣬱����������ᱨ��
	ǿ��ת����ǰ�᣺Ҫת���ı�������������
	Animal a = new Bird();
	Bird b = (Bird)a;���Ե�
	Fish f = (Fish)a;����
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
