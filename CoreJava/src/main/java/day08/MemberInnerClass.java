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
			//�����ڲ����name
			System.out.println("�ڲ���name: "+name);
			//�����ⲿ���name
			System.out.println("�ⲿ��name: "+OuterB.name);	
			//�����ڲ����age
			System.out.println("�ڲ���age: "+age);	
			//�����ⲿ���age
			System.out.println("�ⲿ��age: "+OuterB.this.age);	
		
			eat();
			playGame();
		}
	}
}


public class MemberInnerClass{
	public static void main(String args[]){
		OuterB out  = new OuterB();
		//һ���ⲿ����󣬿������ɶ���ڲ������
		//һ���ڲ������ֻ��Ӧһ�����������ⲿ�����
		OuterB.InnerB inner1 = out.new InnerB("chensheng",3);
		OuterB.InnerB inner2 = out.new InnerB("huangling",9);
		inner1.innerTest();
		System.out.println("size: "+inner1.age);
	}
}
