package day14;
/**
 * 	join方法就是阻塞调用线程,直到调用join方法的线程终止时为止。
 * 	如本例，其中调用线程，是指Mother线程
 * 	调用join方法的线程，是指Son线程对象t
 *  @author new
 *
 */
public class Mother extends Thread {
	public void run(){
		System.out.println("妈妈开始做饭了...");
		System.out.println("发现没有酱油了...");
		Thread t = new Son();
		t.start();		
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("儿子买回来酱油了!");
		System.out.println("妈妈继续做饭...");
		System.out.println("饭做好了！");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Mother().start();
	}
}


class Son extends Thread{
	public void run(){
		System.out.println("儿子开始去买酱油了!");
		for(int i=1;i<=5;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i+"分钟.....");
		}
		System.out.println("儿子把酱油买回来了！");
	}
}














