package scjp_book;

public class Example_5_83 {

	public static void main(String[] args) {
		System.out.println(new Example_5_83().fun());

	}

	public int fun() {
		int i;
		try {
			i = System.in.read();
			System.out.println("Location 1");
			return 0;
		} catch (Exception e) {
			System.out.println("Location 2");
			return 1;
		} finally {
			System.out.println("Location 3");	
			return 2;
		}		
//		System.out.println("Location 4");
//		return 2;
		
	}
}
