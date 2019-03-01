package day05;
public class  Zoo{
	public static int count = 5;
	public String name = "haha";
	public void move(){ System.out.println("my lost!");
	}

	public static void addCount(){
	}

	public static void main(String[] args) 
	{
		System.out.println("count : "+Zoo.count);
		Zoo.addCount();
		//move();
		Zoo z = new Zoo();
		System.out.println("name£º"+z.name);
		z.move();

	}
}
