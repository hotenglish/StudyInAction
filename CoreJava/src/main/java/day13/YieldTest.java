package day13;
/**
 * 不共享代码，也不共享数据的情况
 * @author new
 *
 */
public class YieldTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread5 t5 = new Thread5();		
		Thread6 t6 = new Thread6();
		t5.setName("t5");
		t6.setName("t6");
		t5.start();
		t6.start();
	}
}

class Thread5 extends Thread{	
	private int i =0;
	public void run(){
		while(i<100){
			i++;
			//System.out.println(this.getName()+"...."+i);
			System.out.println(Thread.currentThread().getName()+"...."+i);
			if(i%10==0){
				Thread.yield();
			}
		}		
	}	
}

class Thread6 extends Thread{
	private char c = 'a';
	public void run(){
		while(c<='z'){	
			System.out.println(this.getName()+"...."+c);
			c++;
			for(int j=0;j<4000000;j++){
				;
			}
		}
	}
}











