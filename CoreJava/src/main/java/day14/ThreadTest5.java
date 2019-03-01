package day14;

public class ThreadTest5 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		Store s = new Store(20);
		Producer p1 = new Producer("������01",s);
		Producer p2 = new Producer("������02",s);
		
		Consumer c1 = new Consumer("������01",s);
		Consumer c2 = new Consumer("������02",s);
		Consumer c3 = new Consumer("������03",s);
		
		p1.start();
		p2.start();
		c1.start();
		c2.start();
		c3.start();
	}
}


class Producer extends Thread{
	private String name;
	private Store s ;
	public Producer(String name,Store s){
		this.name = name;
		this.s = s;
	}
	public void run(){
		while(true){
			s.push(name);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer extends Thread{
	private String name;
	private Store s;
	public Consumer(String name,Store s){
		this.name = name;
		this.s = s;
	}
	public void run(){
		while(true){
			s.pop(name);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Store{
	private final int MAX_SIZE;
	private int count;
	public Store(int num){
		MAX_SIZE = num;
	}
	
	public synchronized void push(String name){
		//��Ϊ�ӵȴ����ֻ���ִ����һ��ʱ��
		//�����ʱ���ڿ����Ѿ������������߰Ѳֿ�������
		//������while��������if
		while(count==MAX_SIZE){
			//��ʾ��ǰ����ִ�е��߳�ִֹͣ�У��������ĵȴ��صȴ�
			//ͬʱ�ͷŶ��������
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count++;
		System.out.println(name+" produce a data: "+count);
		this.notifyAll();
	}
	
	public synchronized void pop(String name){
		while(count==0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name+" consume a data: "+count);
		count--;
		this.notifyAll();
	}
	
}












