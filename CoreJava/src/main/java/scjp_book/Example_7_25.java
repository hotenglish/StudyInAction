package scjp_book;

public class Example_7_25 extends Thread
{
//implements Runnable {

	public void run()
	{
		System.out.println("running");
	}
	
	public static void main(String[] args) {
		Example_7_25 t=new Example_7_25();
		//Thread t=new Thread(new Example_7_25());
		t.run();
		t.run();
		t.start();
		
	}	

}
