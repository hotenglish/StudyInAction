package scjp_book;

public class Example_9_40 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		float f = 4.2f;
		Float g = new Float(4.2f);
		Double d = new Double(4.2);
		System.out.println(g.equals(f));
		System.out.println(d.equals(f));
		System.out.println(f==g);
	}
}
