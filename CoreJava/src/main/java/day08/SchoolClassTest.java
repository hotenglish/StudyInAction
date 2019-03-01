package day08;
import java.util.*;

public class  SchoolClassTest{
	public static void main(String[] args)	{
		//创建班级对象
		SchoolClass sc = new SchoolClass("SD0706");

		//声明课程数组
		Course c[] = {
					new Course("Java"),
					new Course("C++"),
					new Course("JDBC"),
					new Course("Oracle"),
					new Course("XML"),
					new Course("EJB")};
		
		//循环30次，班级添加30个学生
		for(int i=0;i<30;i++){
			Student s = new Student("Student"+i);
			//每个学生随机添加课程，最多添加c.length门课程
			for(int j=0;j<c.length;j++){
				//产生一个0到（c.length-1）的整数
				int num = (int)(Math.random()*c.length);
				s.addCourse(c[num]);
			}
			sc.addStudent(s);
			System.out.println(s);
		}
		
		//取出统计结果
		HashMap map = sc.account();
		Iterator it = (map.keySet()).iterator();
		while(it.hasNext()){
			Object o = it.next();
			System.out.println(o+": "+map.get(o));
		}
	}
}


