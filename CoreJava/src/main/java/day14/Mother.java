package day14;
/**
 * 	join�����������������߳�,ֱ������join�������߳���ֹʱΪֹ��
 * 	�籾�������е����̣߳���ָMother�߳�
 * 	����join�������̣߳���ָSon�̶߳���t
 *  @author new
 *
 */
public class Mother extends Thread {
	public void run(){
		System.out.println("���迪ʼ������...");
		System.out.println("����û�н�����...");
		Thread t = new Son();
		t.start();		
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("���������������!");
		System.out.println("�����������...");
		System.out.println("�������ˣ�");
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
		System.out.println("���ӿ�ʼȥ������!");
		for(int i=1;i<=5;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i+"����.....");
		}
		System.out.println("���Ӱѽ���������ˣ�");
	}
}














