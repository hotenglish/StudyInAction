package day05;
class  Test1{
	public void test(Animal1 a){
		System.out.println("test(Animal a)");//1
	}
	public void test(Bird1 b){
		System.out.println("test(Bird b)");//2
	}
	public static void main(String[] args) {
		Animal1 a = new Bird1();
		Bird1 b = new Bird1();
		Animal1 c = new Animal1();
		Test1 t = new Test1();
		t.test(a);
	}
}

class Animal1 {
	public int age;
}

class Bird1 extends Animal1{	

}
