package day08;
import java.util.*;
/*
	班级类
*/
public class SchoolClass{
	private String name;
	private HashSet allStudents;
	public SchoolClass(String name){
		this.name = name;
		allStudents = new HashSet();
	}

	public boolean addStudent(Student s){
		return allStudents.add(s);
	}

	public void removeStudent(String name){
		if(allStudents.size()<=0){
			System.out.println("没有学生，不能删除");
			return;
		}
		Student s = new Student(name);
		allStudents.remove(s);
	}
	
	//统计出每门课程的选课人数
	public HashMap account(){
		HashMap map = new HashMap();
		//对班级内所有学生进行遍历
		for(Object oS:allStudents){
			Student s = (Student)oS;
			//对每个学生选修的所有课程进行遍历
			for(Object oC: s.getSelectedCourse()){
				Course c = (Course)oC;
				//如果统计结果已经有了该门课程，
				//取出选修这门课的人数，再加上1
				if(map.containsKey(c)){
					Object ob = map.get(c);
					Integer i = (Integer)ob;
					map.put(c,new Integer(i+1));
				}else{
					//统计结果中还没有这门课，
					//我一个人选
					map.put(c,new Integer(1));
				}				
			}
		}
		return map;
	}
}