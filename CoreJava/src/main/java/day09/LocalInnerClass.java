package day09;
class OuterC {
	String name;
	Super outerTest(){
		//int age;
		final int size =100;
		class InnerC extends Super{
			void innerTest(){
				LocalInnerClass tt=new LocalInnerClass();
				tt.toString();				
			}
			void playGame(){
				System.out.println("palying....");
				//System.out.println("age: "+age);
				System.out.println("size: "+size);
			}
		}
		return new InnerC();
	}
}


public class LocalInnerClass{
	public static void main(String args[]){
		OuterC out = new OuterC();
		out.outerTest();
		//返回祖父类Object对象
		//Object o = new InnerC();
		//Object o = out.outerTest();
		//o.innerTest();//根据多态定理1，不能访问到innerTest方法
		
		//返回父类Super对象
		//Super s = new InnerC();
		Super s = out.outerTest();
		s.playGame();
	}
}

class Super{
	void playGame(){

	}
}
