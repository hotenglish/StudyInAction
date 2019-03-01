package day08;

/**
	�γ���
*/
public class  Course{
	private String name;//�γ���
	public Course(String name){
		this.name = name;
	}

	//����hashCode����
	public int hashCode(){
		return name.hashCode();
	}
	
	//����equals����
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
