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
		//��̬�ڲ���
		return new InterfaceE(){
			//ʵ�ֽӿ�InterfaceE�еķ���
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