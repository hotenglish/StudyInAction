package day09;
class Animal{
	String name;
	int legs;
	public Animal(String name,int legs) throws  WrongLegsException {
		if(legs<0 ||(legs%2!=0)){
			//不能阻止创建对象的事实
			//System.out.println("wrong legs!");
			//return;
			//抛出自定义异常（已检查异常）
			throw new WrongLegsException("错误的腿的条数");
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
