package design_mode.chain_mode;

interface MyHandler {
	public void handleRequest(String request);
}

class ConcreteHandler implements MyHandler {
	private MyHandler handler;
	
	public ConcreteHandler(MyHandler handler) {
		this.handler = handler;
	}

	public void handleRequest(String request) {
		if (request.equals("Help")) {
			System.out.println("Handle the Help!");// �����Ǵ���Help�ľ������
		} else {
			handler.handleRequest(request);// ���ݵ���һ��
		}
	}
}

class Concrete2Handler implements MyHandler {
	
	public MyHandler handler;	

	public Concrete2Handler() {
		super();
	}

	public Concrete2Handler(MyHandler handler) {
		this.handler = handler;
	}
	
	public void handleRequest(String request) {
		if (request.equals("Warning")) {
			System.out.println("Handle the Warning!");// �����Ǵ���Warning�ľ������
		} else {
			handler.handleRequest(request);// ���ݵ���һ��
		}
	}
}

public class Client {

	public static void main(String[] args) {
		MyHandler h=new ConcreteHandler(new Concrete2Handler());
		System.out.println(new Concrete2Handler().handler);
		h.handleRequest("Help");
		h.handleRequest("Warning");
	}
}
