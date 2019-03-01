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
			System.out.println("Handle the Help!");// 这里是处理Help的具体代码
		} else {
			handler.handleRequest(request);// 传递到下一个
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
			System.out.println("Handle the Warning!");// 这里是处理Warning的具体代码
		} else {
			handler.handleRequest(request);// 传递到下一个
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
