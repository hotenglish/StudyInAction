
package day03;
/**
	this()��ʾ���ñ����������췽��
*/
public class Person1{

	private String name;
	private int age;
	private String personId;

	public Person1(String name,int age){
		this.name = name;
		this.age = age;
		System.out.println("2������");
	}
	public Person1(String name,int age,String personId){
		//this.name = name;
		//this.age = age;
		this(name,age);
		this.personId = personId;
		System.out.println("3������");
	}

	public static void main(String args[]){
		Person1 p = new Person1("lili",18,"2267");
	}
}