package day09;
class OuterE{
	InterfaceE outerE(){
		/*
		class InnerE implements InterfaceE{
			public void study(){
				System.out.println("studying...");
			}
		}
		return new InnerE();*/
		//静态内部类
		return new InterfaceE(){
			//实现接口InterfaceE中的方法
			public void study(){
				System.out.println("studying...");
			}
		};
	}
}

public class AnonymousInnerClass{
	public static void main(String args[]){
		OuterE d = new OuterE();
		InterfaceE in = d.outerE();
		in.study();
	}
}

interface InterfaceE{
	void study();
}