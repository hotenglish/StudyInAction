package day18;

public class Season {
	String name;
	public static final Season SPRING = new Season("����");
	public static final Season SUMMER = new Season("����");
	public static final Season AUTUMN = new Season("����");
	public static final Season WINTER = new Season("����");
	private Season(String name){	
		this.name = name;
	}
	public String toString(){
		return name;
	}
}


