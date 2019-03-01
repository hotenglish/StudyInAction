package scjp_book;

class Alien{
	
	String invade(short ships)
	{
		return "a few";
	}
	String invade(short...ships){
		return "many";
	}
	
}

public class Example_347_51 {

	
	public static void main(String[] args) {
		//System.out.println(new Alien().invade(7));  //if not comments ,then compile error
	}

}
