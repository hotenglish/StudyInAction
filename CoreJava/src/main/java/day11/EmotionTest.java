package day11;
import java.util.EventListener;
import java.util.EventObject;
public class EmotionTest {
	public static void main(String args[]){
		Girl g = new Girl("���ɽ��");
		EmotionListener bf = new Boy("����");
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
	
	//����¼�������
	public void addEmotionListener(EmotionListener bf){
		this.bf = bf;
		System.out.println("���������ˣ��о���ã�");
		System.out.println();
	}
	
	public void fallInLove(){
		EmotionEvent event = new EmotionEvent(this);
		//�¼�Դ�����¼����󣬸��¼�������
		//���൱�����¼�������Ϊ�����������¼���������ķ���
		for(int i=0;i<=10;i++){
			if(i%2==0){
				System.out.println("�ҽ���ÿ��ģ�");
				bf.whatCanIDoWhenGirlHappy(event);
			}else{
				System.out.println("�ҽ��첻���ˣ���ô�죿");
				bf.whatCanIDoWhenGirlSad(event);
			}
			System.out.println();
		}
	}
	
	public void removeEmotionListener(EmotionListener bf){
		if(this.bf==bf){
			this.bf = null;
			System.out.println();
			System.out.println("�����ˣ�Over�ˣ�û����м������ˣ�");
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
			System.out.println(name+" said: "+girlName+"����������ô���ģ�����shoppingȥ��");
	}

	public void whatCanIDoWhenGirlSad(EmotionEvent e) {
		Object o = e.getSource();
		Girl g = (Girl)o;
		String girlName = g.getName();	
		System.out.println(name+" said: "+girlName+"����������ô�����ˣ�����....ȥ��");
	}	
}

/**
 * �¼�����̳���EventObject�࣬������ͨ��getSource��������¼�Դ����
 * ��Ȼ��Ҫ�ڹ����¼�ʱ���¼�Դ�����룬���������ĸ��¼�Դ�������¼�
 */
class EmotionEvent extends EventObject{
	public EmotionEvent(Object source) {
		super(source);		
	}	
}


/**
 * �¼��������еķ�����Ҫ�����ֳ������ĸ��¼�Դ��
 * �¼������ӿ��е�ÿһ��������Ҫ���¼�������Ϊ����
 */
interface EmotionListener extends EventListener{
	void whatCanIDoWhenGirlSad(EmotionEvent e);
	void whatCanIDoWhenGirlHappy(EmotionEvent e);
}


