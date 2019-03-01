package day18.exec;

public class EnumTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Adult:"+TicketType.Adult.priceoff);
		System.out.println("Baby:"+TicketType.Baby.priceoff);
		System.out.println("child:"+TicketType.Child.priceoff);
		System.out.println();
		System.out.println("FirstClass:"+CabinClass.FirstClass);
		System.out.println("OfficialClass:"+CabinClass.OfficialClass.priceOff);
		System.out.println("EconomyClass:"+CabinClass.EconomyClass.priceOff);
		System.out.println();
		System.out.print("corejava:");
				CourseType.CoreJava.teacherMothed();
		System.out.print("unix:");
				CourseType.Unix.teacherMothed();
		System.out.print("oracle:");
		CourseType.Oracle.teacherMothed();
	}
}
