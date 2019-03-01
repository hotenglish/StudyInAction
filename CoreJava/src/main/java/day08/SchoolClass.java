package day08;
import java.util.*;
/*
	�༶��
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
			System.out.println("û��ѧ��������ɾ��");
			return;
		}
		Student s = new Student(name);
		allStudents.remove(s);
	}
	
	//ͳ�Ƴ�ÿ�ſγ̵�ѡ������
	public HashMap account(){
		HashMap map = new HashMap();
		//�԰༶������ѧ�����б���
		for(Object oS:allStudents){
			Student s = (Student)oS;
			//��ÿ��ѧ��ѡ�޵����пγ̽��б���
			for(Object oC: s.getSelectedCourse()){
				Course c = (Course)oC;
				//���ͳ�ƽ���Ѿ����˸��ſγ̣�
				//ȡ��ѡ�����ſε��������ټ���1
				if(map.containsKey(c)){
					Object ob = map.get(c);
					Integer i = (Integer)ob;
					map.put(c,new Integer(i+1));
				}else{
					//ͳ�ƽ���л�û�����ſΣ�
					//��һ����ѡ
					map.put(c,new Integer(1));
				}				
			}
		}
		return map;
	}
}