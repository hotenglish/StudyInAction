package day03;
public class NumberTest{
	
	public void judge(){
	
		double number = 0.0/0.0;//NaN Not a number
		System.out.println(number);
		if(number==number){
			System.out.println("==");
		}else{			
			System.out.println("!=");
		}
		int number2 = 4/0;
		System.out.println("number2: "+number2);
	}
	public static void main(String args[]){
		NumberTest test = new NumberTest();
		test.judge();
	}
}