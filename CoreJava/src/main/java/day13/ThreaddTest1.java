package day13;
/**
 * 不共享代码，也不共享数据的情况
 * @author new
 *
 */
public class ThreaddTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t3 = new Thread(new Thread3());
		Thread t4 = new Thread(new Thread4());
		t3.setPriority(1);
		t4.setPriority(7);
		System.out.println("t3.Priority: "+t3.getPriority());
		System.out.println("t4.Priority: "+t4.getPriority());
		t3.start();
		t4.start();
	}
}
class Thread3 implements Runnable{
	private int i=0;
	public void run(){
		while(i<100){
			i++;
			//System.out.println(i);
			//System.out.println(this.getName()+"...."+i);
			System.out.println(Thread.currentThread().getName()+"...."+i);
		}
	}
}

class Thread4 implements Runnable{
	private char c = 'a';
	public void run(){
		while(c<='z'){
			System.out.println(c);
			c++;
		}
	}
}










