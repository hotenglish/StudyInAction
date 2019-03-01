package day04;

public class ObjectEqualsTest{
	public static void main(String args[]){
		Student s1 = new Student("libo",22);
		Student s2 = new Student("libo",22);
		System.out.println("s1 : "+s1);
		System.out.println("s2 : "+s2);
		if(s1==s2){
			System.out.println("s1==s2");
		}else{
			System.out.println("s1!=s2");
		}

		if(s1.equals(s2)){
			System.out.println("s1 equals s2 ");
		}else{
			System.out.println("s1 not equals s2 ");
		}
	}
}