package day08;
import java.util.*;
/**
	学生类
*/
public class  Student{
	private String name ;//学生姓名
	private HashSet selectedCourse;//该学生所选的所有课程
	public Student(String name){
		this.name = name;
		selectedCourse = new HashSet();
	}

	public HashSet getSelectedCourse(){
		return selectedCourse;
	}

	public boolean addCourse(Course c){
		return selectedCourse.add(c);
	}

	public String toString(){
		return name+": "+selectedCourse.toString();
	}

	public void removeCourse(String name){
		if(selectedCourse.size()<=0){
			System.out.println("没有课程，不能删除");
			return;
		}
		//第一种方式
		//for(Object o:selectedCourse){
		//	Course c = (Course)o;
		//	if(c.name.equals(name)){
		//		selectedCourse.remove(o);
		//	}
		//}

		//第二种方式
		Course c = new Course(name);
		selectedCourse.remove(c);
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
		if(!(o instanceof Student)){
			return false;
		}
		Student c = (Student)o;
		if(this.name.equals(c.name)){
			return true;
		}
		return false;
	}
}
