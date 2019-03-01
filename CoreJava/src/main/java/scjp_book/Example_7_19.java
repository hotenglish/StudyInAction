package scjp_book;

public class Example_7_19 implements Runnable{

	public void run()
	{
		System.out.println("run");
		throw new RuntimeException("Problem");
	}
	
	
	public static void main(String[] args) {
		Thread t =new Thread(new Example_7_19());
		t.start();
		System.out.println("End of method");
	}

}
