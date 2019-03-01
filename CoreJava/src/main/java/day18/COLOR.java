package day18;

public enum COLOR {	
	RED("红色"),//public static final Color RED = new Color();
	YELLOW("黄色"),
	GREEN("绿色"),
	WHILE("白色");	
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
