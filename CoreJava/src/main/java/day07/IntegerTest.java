package day07;
public class IntegerTest{
	public static void main(String args[]){
		Integer n1 = 8;//=new Integer(8)
		Integer n2 = 8;

		if(n1==n2){
			System.out.println("8==8");
		}else{
			System.out.println("8!=8");
		}


		Integer n3 = 200;//=new Integer(200)
		Integer n4 = 200;
		System.out.println(n3);
		if(n3==n4){
			System.out.println("n3==n4");
		}else{
			System.out.println("n3!=n4");
		}
	}
}


