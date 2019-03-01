package day05;
public class StaticTest {
	//132 .....
	static{
		System.out.println("in static block....");//1
	}

	public StaticTest(){
		System.out.println("in constractor.....");//2
	}
	public static void main(String[] args) 
	{
		System.out.println("in main method");//3
		StaticTest t = new StaticTest();
	}
}
