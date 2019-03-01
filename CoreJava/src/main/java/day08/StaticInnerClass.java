package day08;
class OuterA{
	static String name = "zhanghuaqiang";
	int age;
	static void eat(){
		System.out.println("eatting....");
	}
	void playGame(){
		InnerA inner = new InnerA("Mary",24);
		inner.innerTest();
	}

	static class InnerA{
		String name;
		int size;
		public InnerA(String name,int size){
			this.name = name;
			this.size = size;
		}
		public void innerTest(){
			//访问内部类的name
			System.out.println("内部类name: "+name);
			//访问外部类的name		
			System.out.println("外部类name: "+OuterA.name);	
			eat();			
		}
	}
}

public class StaticInnerClass{
	public static void main(String args[]){
		OuterA.InnerA inner = new OuterA.InnerA("donghang",8);
		inner.innerTest();
		System.out.println(inner.size);
		System.out.println(inner.name);

		System.out.println("*****************");
		OuterA out = new OuterA();
		out.playGame();
	}
}


