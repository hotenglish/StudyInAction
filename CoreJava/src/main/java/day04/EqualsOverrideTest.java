package day04;

public class EqualsOverrideTest{
	private String color="";  //null
	private double price;
	
	public boolean equals(Object obj){
		boolean flag = false;
		if(obj==this){
			System.out.println("obj==this");
			flag = true;
			return flag;
		}
		if(obj==null){
			flag = false;
			return flag;
		}
		if(!(obj instanceof EqualsOverrideTest)){
			flag = false;
			return flag;
		}
		EqualsOverrideTest test = 
			(EqualsOverrideTest)obj;
		
		//String color;colorÎª¿Õ£¬¿ÕÖ¸Õë´íÎó
		//if(test.color.equals(this.color) &&
		//	test.price == this.price){
		//	flag = true;
		//	return flag;
		//}
		if(this.color.equals(test.color) &&
			test.price == this.price){
			flag = true;
			return flag;
		}
		return flag;
	}
	public static void main(String[] args) 
	{
		EqualsOverrideTest test1 = new EqualsOverrideTest();
		EqualsOverrideTest test2 = new EqualsOverrideTest();
		if(test1.equals(test1)){
			System.out.println("test1 equals test1");
		}else{
			System.out.println("test1 not equals test1");
		}
		
		if(test1.equals(test2)){
			System.out.println("test1 equals test2");
		}else{
			System.out.println("test1 not equals test2");
		}
	}
}
