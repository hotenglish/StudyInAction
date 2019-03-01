package day08;
import java.util.*;
/**
	ѧ����
*/
public class  Student{
	private String name ;//ѧ������
	private HashSet selectedCourse;//��ѧ����ѡ�����пγ�
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
			System.out.println("û�пγ̣�����ɾ��");
			return;
		}
		//��һ�ַ�ʽ
		//for(Object o:selectedCourse){
		//	Course c = (Course)o;
		//	if(c.name.equals(name)){
		//		selectedCourse.remove(o);
		//	}
		//}

		//�ڶ��ַ�ʽ
		Course c = new Course(name);
		selectedCourse.remove(c);
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
