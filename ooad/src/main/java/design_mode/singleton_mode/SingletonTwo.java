package design_mode.singleton_mode;

public class SingletonTwo {
	
	public static void main(String[] args) {
		// Recyclebin rb=new Recyclebin(); �������
		RecycleLazy rb1 = RecycleLazy.getInstance();
		RecycleLazy rb2 = RecycleLazy.getInstance();
		System.out.println(rb1 == rb2);

	}
}

// ����ʽ������
class RecycleLazy {
	private static RecycleLazy _instance=null;

	/**
	 * ˽�е�Ĭ�Ϲ�����
	 */
	private RecycleLazy() {
	}

	/**
	 * ��̬��������
	 */
	public synchronized static RecycleLazy getInstance() {
		if (_instance == null) {
			_instance = new RecycleLazy();
		}
		System.out.println("Create RecycleLazyInstance");
		return _instance;
	}
}