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
	

	//传递Animal a2 
	//实参赋给形参 Object o = a2;
	//还原
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
			System.out.println("o不是Animal实例");
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
		Animal a1 = new Animal("旺财",3,4);
		Animal a2 = new Animal("小强",1,8);
		//Student s = new Student();
		if(a1.equals(a2)){
			System.out.println("旺财 equals 小强");
		}else{
			System.out.println("旺财 not equals 小强");
		}
	}
}
