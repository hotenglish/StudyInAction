package day08;

/**
	课程类
*/
public class  Course{
	private String name;//课程名
	public Course(String name){
		this.name = name;
	}

	//覆盖hashCode方法
	public int hashCode(){
		return name.hashCode();
	}
	
	//覆盖equals方法
	public boolean equals(Object o){
		if(o==this){
			return true;
		}
		if(o==null){
			return false;
		}
		if(!(o instanceof Course)){
			return false;
		}
		Course c = (Course)o;
		if(this.name.equals(c.name)){
			return true;
		}
		return false;
	}

	public String toString(){
		return name;
	}
}
