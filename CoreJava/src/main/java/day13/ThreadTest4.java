package day13;
/**
 * 共享代码，并且共享数据
 * @author new
 *
 */
public class ThreadTest4 {
	public static void main(String args[]){
		Thread9 t1 = new Thread9();
		Thread9 t2 = new Thread9();
		t1.start();
		t2.start();
	}
	
}

class Thread9 extends Thread{
	static int i=0;
	static Object obj = new Object() ;
	
	public synchronized void print(){	
		i++;					
		for(int j=0;j<3000000;j++){				
		}
		System.out.println(this.getName()+"...."+i);
	}
	public void run(){
		while(i<30){			
			print();
		}
	}
}










