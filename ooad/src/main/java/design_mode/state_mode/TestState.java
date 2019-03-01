package design_mode.state_mode;

class BBSUser{
	State state;
	public void setState(State s){
		this.state=s;
	}
	public void publish(){
		state.action();
	}
}

abstract class State{
	public abstract void action();
}
class GuestState extends State{
	public void action(){
		System.out.println("���ȵ�¼���ο���Ȩ����");
	}
}
class NormalState extends State{
	public void action(){
		System.out.println("���·���ɹ�");
	}
}
class BlockedState extends State{
	public void action(){
		System.out.println("�������ˣ���Ȩ���ԣ�����ϵHanlj���ϴ���");
	}
}
class NewState extends State{
	public void action(){
		System.out.println("�������֣���ʱ��Ȩ���ԣ�24Сʱ֮����˵");
	}
}

public class TestState {
	public static void main(String[] args) {
		BBSUser user=new BBSUser();
		user.setState(new GuestState());
		user.publish();
		user.setState(new NormalState());
		user.publish();
		user.setState(new BlockedState());
		user.publish();
		user.setState(new NewState());
		user.publish();
	}
}