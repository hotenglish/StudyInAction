package day08;
import java.util.*;

public class  SchoolClassTest{
	public static void main(String[] args)	{
		//�����༶����
		SchoolClass sc = new SchoolClass("SD0706");

		//�����γ�����
		Course c[] = {
					new Course("Java"),
					new Course("C++"),
					new Course("JDBC"),
					new Course("Oracle"),
					new Course("XML"),
					new Course("EJB")};
		
		//ѭ��30�Σ��༶���30��ѧ��
		for(int i=0;i<30;i++){
			Student s = new Student("Student"+i);
			//ÿ��ѧ�������ӿγ̣�������c.length�ſγ�
			for(int j=0;j<c.length;j++){
				//����һ��0����c.length-1��������
				int num = (int)(Math.random()*c.length);
				s.addCourse(c[num]);
			}
			sc.addStudent(s);
			System.out.println(s);
		}
		
		//ȡ��ͳ�ƽ��
		HashMap map = sc.account();
		Iterator it = (map.keySet()).iterator();
		while(it.hasNext()){
			Object o = it.next();
			System.out.println(o+": "+map.get(o));
		}
	}
}


