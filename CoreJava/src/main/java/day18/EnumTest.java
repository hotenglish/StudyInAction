package day18;

public class EnumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Season.AUTUMN);
		System.out.println(COLOR.RED.getName());
		System.out.println(COLOR.RED.ordinal());
		System.out.println("---------------------");
		printColor();
		
		COLOR c = Enum.valueOf(COLOR.class, "RED");
		System.out.println(c.YELLOW);
		
		System.out.println(Operator.ADD.getValue(1, 2)); 
		System.out.println(Operator.SUB.getValue(1, 2)); 
		System.out.println(Operator.TIMES.getValue(1, 2)); 
		System.out.println(Operator.MODIFY.getValue(1, 2)); 
	}
	
	public static void printColor(){
		COLOR c[] = COLOR.values();
		for(COLOR color:c){
			System.out.println(color.toString());
		}
	}

}




