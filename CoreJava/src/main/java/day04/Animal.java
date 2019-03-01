package day04;

public class Animal{
	private String name="";
	private int age;
	private int legs;

	public Animal(String name,int age,int legs){
		this.name = name;
		this.age = age;
		this.legs = legs;
	}
	

	//����Animal a2 
	//ʵ�θ����β� Object o = a2;
	//��ԭ
	public boolean equals(Object o){	
		if(this==o){
			System.out.println("this==o");
			return true;
		}
		if(o==null){
			System.out.println("o==null");
			return false;
		}
		if(!(o instanceof Animal)){
			System.out.println("o����Animalʵ��");
			return false;
		}
		Animal a = (Animal)o;
		if(this.name.equals(a.name) &&
			this.age == a.age &&
			this.legs == a.legs){
			return true;
		}
		return false;
	}


	public static void main(String[] args) 
	{
		Animal a1 = new Animal("����",3,4);
		Animal a2 = new Animal("Сǿ",1,8);
		//Student s = new Student();
		if(a1.equals(a2)){
			System.out.println("���� equals Сǿ");
		}else{
			System.out.println("���� not equals Сǿ");
		}
	}
}
