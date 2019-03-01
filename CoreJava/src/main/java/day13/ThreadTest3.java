package day13;
/**
 * 共享代码，并且共享数据
 * @author new
 *
 */
public class ThreadTest3 {
	public static void main(String args[]){
		Thread8 t1 = new Thread8();
		Thread8 t2 = new Thread8();
		t1.start();
		t2.start();
	}
	
}

class Thread8 extends Thread{
	static int i=0;
	static Object obj = new Object() ;
	public void run(){
		while(i<30){			
			synchronized(obj){	
				i++;
				if(i>=31){
					break;
				}				
				for(int j=0;j<3000000;j++){				
				}
				System.out.println(this.getName()+"...."+i);
			}
		}
	}
}










