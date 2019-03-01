package day08;
class  OuterB{
	static String name = "zhanghuaqiang";
	int age = 18;
	static void eat(){
		System.out.println("eatting....");
	}
	InnerB playGame(){
		return this.new InnerB("liuxiao",30);
	}

	class InnerB{
		int age;
		String name;
		public InnerB(String name,int age){
			this.name = name;
			this.age = age;
		}
		void innerTest(){
			//访问内部类的name
			System.out.println("内部类name: "+name);
			//访问外部类的name
			System.out.println("外部类name: "+OuterB.name);	
			//访问内部类的age
			System.out.println("内部类age: "+age);	
			//访问外部类的age
			System.out.println("外部类age: "+OuterB.this.age);	
		
			eat();
			playGame();
		}
	}
}


public class MemberInnerClass{
	public static void main(String args[]){
		OuterB out  = new OuterB();
		//一个外部类对象，可以生成多个内部类对象
		//一个内部类对象，只对应一个生成它的外部类对象
		OuterB.InnerB inner1 = out.new InnerB("chensheng",3);
		OuterB.InnerB inner2 = out.new InnerB("huangling",9);
		inner1.innerTest();
		System.out.println("size: "+inner1.age);
	}
}
