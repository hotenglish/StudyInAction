package day09;
class OuterD{
	 InterfaceD outerD(){
		//�ֲ��ڲ���ʵ�ֽӿ�InterfaceD
		class InnerD implements InterfaceD{
			public void study(){
				System.out.println("studying...");
			}
		}
		return new InnerD();
	}
}

public class LocalInnerClass2{
	public static void main(String args[]){
		OuterD d = new OuterD();
		InterfaceD in = d.outerD();
		in.study();
	}
}

interface InterfaceD{
	void study();
}