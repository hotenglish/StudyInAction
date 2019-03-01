package day11;
import java.util.EventListener;
import java.util.EventObject;
public class EmotionTest {
	public static void main(String args[]){
		Girl g = new Girl("神仙姐姐");
		EmotionListener bf = new Boy("张三");
		g.addEmotionListener(bf);
		g.fallInLove();
		g.removeEmotionListener(bf);
	}
}
class Girl{
	String name;
	EmotionListener bf;
	public Girl(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	//添加事件监听器
	public void addEmotionListener(EmotionListener bf){
		this.bf = bf;
		System.out.println("终于恋爱了，感觉真好！");
		System.out.println();
	}
	
	public void fallInLove(){
		EmotionEvent event = new EmotionEvent(this);
		//事件源发出事件对象，给事件监听器
		//就相当于用事件对象作为参数，调用事件监听器里的方法
		for(int i=0;i<=10;i++){
			if(i%2==0){
				System.out.println("我今天好开心！");
				bf.whatCanIDoWhenGirlHappy(event);
			}else{
				System.out.println("我今天不高兴，怎么办？");
				bf.whatCanIDoWhenGirlSad(event);
			}
			System.out.println();
		}
	}
	
	public void removeEmotionListener(EmotionListener bf){
		if(this.bf==bf){
			this.bf = null;
			System.out.println();
			System.out.println("分手了，Over了，没有情感监听器了！");
		}
	}
}

class Boy implements EmotionListener{
	String name;
	public Boy(String name){
		this.name = name;
	}
	public void whatCanIDoWhenGirlHappy(EmotionEvent e) {
			Object o = e.getSource();
			Girl g = (Girl)o;
			String girlName = g.getName();
			System.out.println(name+" said: "+girlName+"，今天你这么开心，我们shopping去！");
	}

	public void whatCanIDoWhenGirlSad(EmotionEvent e) {
		Object o = e.getSource();
		Girl g = (Girl)o;
		String girlName = g.getName();	
		System.out.println(name+" said: "+girlName+"，今天你这么不高兴，我们....去！");
	}	
}

/**
 * 事件对象继承自EventObject类，并可以通过getSource方法获得事件源对象，
 * 当然需要在构造事件时将事件源对象传入，来区分是哪个事件源发出的事件
 */
class EmotionEvent extends EventObject{
	public EmotionEvent(Object source) {
		super(source);		
	}	
}


/**
 * 事件监听器中的方法，要能区分出来自哪个事件源。
 * 事件监听接口中的每一个方法都要以事件对象作为参数
 */
interface EmotionListener extends EventListener{
	void whatCanIDoWhenGirlSad(EmotionEvent e);
	void whatCanIDoWhenGirlHappy(EmotionEvent e);
}


