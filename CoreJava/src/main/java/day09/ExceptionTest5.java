package day09;
class Animal{
	String name;
	int legs;
	public Animal(String name,int legs) throws  WrongLegsException {
		if(legs<0 ||(legs%2!=0)){
			//������ֹ�����������ʵ
			//System.out.println("wrong legs!");
			//return;
			//�׳��Զ����쳣���Ѽ���쳣��
			throw new WrongLegsException("������ȵ�����");
		}
		this.name = name;
		this.legs = legs;
	}	
}

public class ExceptionTest5{
	public static void main(String[] args)	{
		try{
			Animal a = new Animal("xiaoqiang",-74);
			System.out.println(a);
		}catch(WrongLegsException e){
			e.printStackTrace();
		}
	}
}
