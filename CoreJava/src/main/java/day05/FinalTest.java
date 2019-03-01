package day05;
public  class FinalTest{
	//public static final int MAX_LENGTH= 100;
	public  final int MAX_LENGTH;
	public FinalTest(int length){
		MAX_LENGTH = length;
	}
	

	public void test1(final Animal a){
		//a = new Bird();
	}
	public static void main(String[] args) 
	{
		FinalTest t = new FinalTest(6);
		System.out.println("t.MAX_LENGTH: "+t.MAX_LENGTH);
		FinalTest t1 = new FinalTest(7);
		System.out.println("t1.MAX_LENGTH: "+t1.MAX_LENGTH);
		//t1.MAX_LENGTH ++;
		//t.test1();
	}
}

 
