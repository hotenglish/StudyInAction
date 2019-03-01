package scjp_book;

public class Parent {

	public Parent() {
		super();
		System.out.println("initance Parent");
	}

	public static void main(String[] args) {
		Parent p = new Child();
		
		System.out.println("-----------------------------");
		for(int i=0;i<2;i++)
			System.out.println(i);
		System.out.println("-----------------------------");
		for(int i=0;i<2;++i)
			System.out.println(i);

	}

}

class Child extends Parent{

	public Child() {
		System.out.println("initance Child");
	}

}
