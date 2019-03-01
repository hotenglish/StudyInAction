package day03.exec;

public class  Person{
	private String name;
	private int age;
	private boolean gender;//true 男 false 女
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
			System.out.println("Sorry！同性不能结婚！");
			return;
		}
		
		if(this.gender && this.age <24){
			System.out.println("男士，您未达到结婚年龄");
			return;
		}
		if(!this.gender && this.age<22){
			System.out.println("女士，您未达到结婚年龄");
			return;
		}
		if(p.gender && p.age <24){
			System.out.println("您的女朋友未达到结婚年龄");
			return;
		}
		if(!p.gender && p.age<22){
			System.out.println("您的男朋友未达到结婚年龄");
			return;
		}

		if(this.partner!=null){
			System.out.println("您想犯重婚罪吗");
			return;
		}

		if(p.partner!=null){
			System.out.println("您男（女）朋友想犯重婚罪吗");
			return;
		}
		System.out.println(this.name+"和"+p.name+"，自愿申请结婚，经审查，给与批准。祝白头到老，早生贵子！");
		this.partner = p;
		p.partner = this;
	}

	public static void main(String[] args){
		Person p1 = new Person("章子怡",28,false);
		Person p2 = new Person("周润发",43,true);
		Person p3 = new Person("周星驰",35,true);
		p1.marry(p2);
		p1.marry(p3);
	}
}
