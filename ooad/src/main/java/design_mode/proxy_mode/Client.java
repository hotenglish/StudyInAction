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
		System.out.println("Ҫ����������������Ǻ��ѵ�.");
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
		System.out.println("�����������֮ǰ��Ҫ��һЩ׼��������");
	}

	private void postRequest() {
		System.out.println("һ��������ɺ󣬻�Ҫ��һЩ��β����Ŷ��");
	}
}
