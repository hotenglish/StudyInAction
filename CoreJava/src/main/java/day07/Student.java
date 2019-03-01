package day07;
public class Student implements Comparable{
	public String name="huangling" ;
	public int age ;
	public Student(){
	}
	public Student(String name,int age){
		this.name = name;
		this.age = age;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getAge(){
		return age;
	}
	public void setAge(int age){
		this.age = age;
	}

	//覆盖equals方法
	public boolean equals(Object o){
		if(o==this){
			return true;
		}
		if(o==null){
			return false;
		}
		if(!(o instanceof Student)){
			return false;
		}
		Student s = (Student)o;
		if(this.name.equals(s.name) &&
			this.age == s.age){
			return true;
		}
		return false;
	}

	//覆盖toString方法
	public String toString(){
		return name+": "+age;
	}

	//覆盖hashCode方法
	public int hashCode(){
		return name.hashCode()^age;
	}

	public int compareTo(Object o){
		Student s = (Student)o;
		if(this.age>s.age){
			return 1;
		}else if(this.age==s.age){
			return 0;
		}else 
			return -1;
	}
}


