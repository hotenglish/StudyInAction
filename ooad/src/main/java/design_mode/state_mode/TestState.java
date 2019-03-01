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
		System.out.println("请先登录，游客无权发言");
	}
}
class NormalState extends State{
	public void action(){
		System.out.println("文章发表成功");
	}
}
class BlockedState extends State{
	public void action(){
		System.out.println("您被封了，无权发言，请联系Hanlj承认错误");
	}
}
class NewState extends State{
	public void action(){
		System.out.println("您是新手，暂时无权发言，24小时之后再说");
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