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
			//�����ڲ����name
			System.out.println("�ڲ���name: "+name);
			//�����ⲿ���name		
			System.out.println("�ⲿ��name: "+OuterA.name);	
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


