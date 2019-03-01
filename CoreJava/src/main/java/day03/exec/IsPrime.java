package day03.exec;

public class  IsPrime{
	public boolean isPrime(int number){
		boolean flag = false;
		for(int i=2;i<number;i++){
			System.out.println(i);
			if(number%i==0){
				flag = false;
				System.out.println("i = "+i);
				//return flag;
				break;
			}else{
				flag = true;
				break;
				//return flag;
			}
		}
		return flag;
	}

	public static void main(String[] args){
		
		//for(int i=0;i<10;i++){
			double d = Math.random()*1000;
			int number = (int)d;
			System.out.println("number: "+number);
			IsPrime is = new IsPrime();
			if(is.isPrime(45)){
				System.out.println("素数");
			}else{
				System.out.println("不是素数");
			}
			System.out.println();
		//}
	}
}
