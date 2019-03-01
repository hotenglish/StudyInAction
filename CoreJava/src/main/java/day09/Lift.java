package day09;
public class  Lift{
	private final int MAX_LOAD = 1000;//最大载重量
	private int weight;//当前载重量
	private String name;//名字
	public Lift(String name){
		this.name = name;
	}

	public void load(Person p){
		if((weight + p.getWeight())>MAX_LOAD){
			throw new OverloadException("超载了，请"+p.getName()+"出去！");
		}
		this.weight+=p.getWeight();
		System.out.println(p.getName()+"进来了！");
	}

	public void run() throws SteelWireException{
		int num = (int)(Math.random()*10000);
		if(num%13==0){
			throw new SteelWireException(name+"紧急停止，请保持镇静！");
		}
		System.out.println(name+"成功运行一次！");
	}
	public static void main(String args[]){
		Lift t1 = new Lift("侨鑫1号电梯");
		try{
			t1.load(new Person("陈明敏",120));
			t1.load(new Person("邹铮荣",130));
			t1.load(new Person("黄凌",115));
			t1.load(new Person("蔡来师",125));
			t1.load(new Person("李凌",300));
			t1.load(new Person("何志湖",130));
			//t1.load(new Person("林云志",130));
			//t1.load(new Person("罗威",130));
			t1.run();
		}catch(OverloadException o){
			o.printStackTrace();
		}catch(SteelWireException o){
			o.printStackTrace();
		}
	}

}

class Person{
	private String name;
	private int weight;
	public Person(String name,int weight){
		this.name = name;
		this.weight = weight;
	}
	public String getName(){
		return name;
	}
	public int getWeight(){
		return weight;
	}	
}

/**
	超载异常类
*/
class OverloadException extends RuntimeException{
	public OverloadException(String message){
		super(message);
	}
}

/**
	钢缆断掉异常类
*/
class SteelWireException extends Exception{
	public SteelWireException(String message){
		super(message);
	}
}