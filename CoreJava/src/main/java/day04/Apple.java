package day04;

public class Apple{
	private String color;
	private double price;
	public Apple(){
		System.out.println("无参构造方法");
	}
	public Apple(String c){
		this.color = c;
		System.out.println("color构造方法");
	}

	public Apple(String color,double price){
		this.color = color;
		this.price = price;
		System.out.println("color.price构造方法");
	}

	public static void main(String[] args) 
	{
		Apple a1 = new Apple();
		System.out.println(a1.color);
		System.out.println(a1.price);
		System.out.println(a1);
		System.out.println();

		Apple a2 = new Apple("red");
		System.out.println(a2.color);
		System.out.println(a2.price);
		System.out.println(a2);
		System.out.println();

		Apple a3 = new Apple("red",4);
		System.out.println(a3.color);
		System.out.println(a3.price);
		System.out.println(a3);
	}
}