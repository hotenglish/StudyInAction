package day18;

public class Season {
	String name;
	public static final Season SPRING = new Season("春天");
	public static final Season SUMMER = new Season("夏天");
	public static final Season AUTUMN = new Season("秋天");
	public static final Season WINTER = new Season("冬天");
	private Season(String name){	
		this.name = name;
	}
	public String toString(){
		return name;
	}
}


