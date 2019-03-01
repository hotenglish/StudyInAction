package day13;
/**
 * 共享代码，不共享数据
 * @author new
 *
 */
public class ThreadTest2 {
	public static void main(String args[]){
		Thread7 t1 = new Thread7();
		Thread7 t2 = new Thread7();
		t1.start();
		t2.start();
	}
	
}

class Thread7 extends Thread{
	 int i=0;
	public void run(){
		while(i<100){
			i++;
			System.out.println(this.getName()+"...."+i);
		}
	}
}