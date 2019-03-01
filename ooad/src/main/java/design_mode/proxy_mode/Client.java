package design_mode.proxy_mode;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Subject subject = new ProxySubject();
		subject.request();
	}

}

interface Subject {
	public void request();
}

class RealSubject implements Subject {

	public void request() {
		System.out.println("要真正做好这件事情是很难的.");
	}
}

class ProxySubject implements Subject {
	private RealSubject realSubject;

	public void request() {
		preRequest();

		if (realSubject == null) {
			realSubject = new RealSubject();
		}

		realSubject.request();

		postRequest();
	}

	private void preRequest() {
		System.out.println("在做这件事情之前，要做一些准备工作！");
	}

	private void postRequest() {
		System.out.println("一件事情完成后，还要做一些收尾工作哦！");
	}
}
