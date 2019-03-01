package design_mode.singleton_mode;

//����ʽ������
class Recyclebin {
	private static final Recyclebin _instance = new Recyclebin();

	/**
	 * ˽�е�Ĭ�Ϲ�����
	 */
	private Recyclebin() {
	}

	/**
	 * ��̬��������
	 */
	public static Recyclebin getInstance() {
		System.out.println("Create RecyclebinInstance!");
		return _instance;
	}

}

public class SingletonOne {
	
	public static void main(String[] args) {
		// Recyclebin rb=new Recyclebin(); �������		
		Recyclebin rb1 = Recyclebin.getInstance();
		Recyclebin rb2 = Recyclebin.getInstance();
		System.out.println(rb1 == rb2);
	}
}
