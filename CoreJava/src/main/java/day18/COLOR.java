package day18;

public enum COLOR {	
	RED("��ɫ"),//public static final Color RED = new Color();
	YELLOW("��ɫ"),
	GREEN("��ɫ"),
	WHILE("��ɫ");	
	String name;
	public String getName(){
		return name;
	}
	
	public String toString()
	{
		return name;
	}
	
	private COLOR(String name){
		this.name = name;
	}
}
