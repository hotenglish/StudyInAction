package day01;

public class SwitchTest{
	public static void main(String args[]){
		String arg = args[0];
		int number = Integer.parseInt(arg);
		switch(number){
			case 20:{
				System.out.println("20");
				break;
			}
			case 40:{
				System.out.println("40");
				break;
			}
			case 60:{
				System.out.println("60");
				break;
			}
			default:
				System.out.println("100");
		}
	}
}

