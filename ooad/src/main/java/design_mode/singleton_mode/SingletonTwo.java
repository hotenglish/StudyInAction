package design_mode.singleton_mode;

public class SingletonTwo {
	
	public static void main(String[] args) {
		// Recyclebin rb=new Recyclebin(); 编译出错！
		RecycleLazy rb1 = RecycleLazy.getInstance();
		RecycleLazy rb2 = RecycleLazy.getInstance();
		System.out.println(rb1 == rb2);

	}
}

// 懒汉式单例类
class RecycleLazy {
	private static RecycleLazy _instance=null;

	/**
	 * 私有的默认构造子
	 */
	private RecycleLazy() {
	}

	/**
	 * 静态工厂方法
	 */
	public synchronized static RecycleLazy getInstance() {
		if (_instance == null) {
			_instance = new RecycleLazy();
		}
		System.out.println("Create RecycleLazyInstance");
		return _instance;
	}
}