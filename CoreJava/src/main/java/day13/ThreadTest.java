package day13;
/**
 * 不共享代码，也不共享数据的情况
 * @author new
 *
 */
public class ThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Thread1 t1 = new Thread1();
		Thread1 t1 = new Thread1("t1");
		Thread2 t2 = new Thread2();
		//t1.setName("t1");//
		t2.setName("t2");
		t1.start();
		t2.start();
	}
}

class Thread1 extends Thread{
	String name;
	public Thread1(String name){
		super(name);
	}
	private int i =0;
	public void run(){
		while(i<100){
			i++;
			//System.out.println(this.getId()+"...."+i);
			System.out.println(this.getName()+"...."+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//for(int i=0;i<3000000;i++){				
			//}
		}		
	}	
}

class Thread2 extends Thread{
	private char c = 'a';
	public void run(){
		while(c<='z'){			
			//System.out.println(this.getId()+"...."+c);
			System.out.println(this.getName()+"...."+c);
			c++;
		}
	}
}











