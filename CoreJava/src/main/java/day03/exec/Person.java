package day03.exec;

public class  Person{
	private String name;
	private int age;
	private boolean gender;//true �� false Ů
	private Person partner;

	public Person(){
	}
	public Person(String name,int age,boolean gender){
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public void marry(Person p){
		if(this.gender && p.gender || !this.gender && !p.gender){
			System.out.println("Sorry��ͬ�Բ��ܽ�飡");
			return;
		}
		
		if(this.gender && this.age <24){
			System.out.println("��ʿ����δ�ﵽ�������");
			return;
		}
		if(!this.gender && this.age<22){
			System.out.println("Ůʿ����δ�ﵽ�������");
			return;
		}
		if(p.gender && p.age <24){
			System.out.println("����Ů����δ�ﵽ�������");
			return;
		}
		if(!p.gender && p.age<22){
			System.out.println("����������δ�ﵽ�������");
			return;
		}

		if(this.partner!=null){
			System.out.println("���뷸�ػ�����");
			return;
		}

		if(p.partner!=null){
			System.out.println("���У�Ů�������뷸�ػ�����");
			return;
		}
		System.out.println(this.name+"��"+p.name+"����Ը�����飬����飬������׼��ף��ͷ���ϣ��������ӣ�");
		this.partner = p;
		p.partner = this;
	}

	public static void main(String[] args){
		Person p1 = new Person("������",28,false);
		Person p2 = new Person("����",43,true);
		Person p3 = new Person("���ǳ�",35,true);
		p1.marry(p2);
		p1.marry(p3);
	}
}
