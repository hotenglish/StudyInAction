package design_mode.singleton_mode;

//饿汉式单例类
class Recyclebin {
	private static final Recyclebin _instance = new Recyclebin();

	/**
	 * 私有的默认构造子
	 */
	private Recyclebin() {
	}

	/**
	 * 静态工厂方法
	 */
	public static Recyclebin getInstance() {
		System.out.println("Create RecyclebinInstance!");
		return _instance;
	}

}

public class SingletonOne {
	
	public static void main(String[] args) {
		// Recyclebin rb=new Recyclebin(); 编译出错！		
		Recyclebin rb1 = Recyclebin.getInstance();
		Recyclebin rb2 = Recyclebin.getInstance();
		System.out.println(rb1 == rb2);
	}
}
