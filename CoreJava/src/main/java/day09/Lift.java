package day09;
public class  Lift{
	private final int MAX_LOAD = 1000;//���������
	private int weight;//��ǰ������
	private String name;//����
	public Lift(String name){
		this.name = name;
	}

	public void load(Person p){
		if((weight + p.getWeight())>MAX_LOAD){
			throw new OverloadException("�����ˣ���"+p.getName()+"��ȥ��");
		}
		this.weight+=p.getWeight();
		System.out.println(p.getName()+"�����ˣ�");
	}

	public void run() throws SteelWireException{
		int num = (int)(Math.random()*10000);
		if(num%13==0){
			throw new SteelWireException(name+"����ֹͣ���뱣���򾲣�");
		}
		System.out.println(name+"�ɹ�����һ�Σ�");
	}
	public static void main(String args[]){
		Lift t1 = new Lift("����1�ŵ���");
		try{
			t1.load(new Person("������",120));
			t1.load(new Person("�����",130));
			t1.load(new Person("����",115));
			t1.load(new Person("����ʦ",125));
			t1.load(new Person("����",300));
			t1.load(new Person("��־��",130));
			//t1.load(new Person("����־",130));
			//t1.load(new Person("����",130));
			t1.run();
		}catch(OverloadException o){
			o.printStackTrace();
		}catch(SteelWireException o){
			o.printStackTrace();
		}
	}

}

class Person{
	private String name;
	private int weight;
	public Person(String name,int weight){
		this.name = name;
		this.weight = weight;
	}
	public String getName(){
		return name;
	}
	public int getWeight(){
		return weight;
	}	
}

/**
	�����쳣��
*/
class OverloadException extends RuntimeException{
	public OverloadException(String message){
		super(message);
	}
}

/**
	���¶ϵ��쳣��
*/
class SteelWireException extends Exception{
	public SteelWireException(String message){
		super(message);
	}
}