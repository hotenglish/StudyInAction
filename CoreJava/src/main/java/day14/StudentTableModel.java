package day14;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class StudentTableModel  extends AbstractTableModel {
	private ArrayList students;
	//ͨ��ArrayList�������װ��ѧ������
	//������ʾѧ����Ϣ�ı��
	public StudentTableModel(ArrayList students){
		this.students=students;
	}
	
	//��������
	public int getColumnCount() {		
		return 4;
	}
	
	//����ÿһ�е�����
	public String getColumnName(int col) {
		switch(col){
		case 0:return "����";
		case 1:return "age";
		case 2:return "gender";
		case 3:return "score";
		default:return null;
		}
	}
	
	//��������
	public int getRowCount() {
		return students.size();
	}
	
	//ͨ���кź��кţ���ȡÿһ����ʾ������
	public Object getValueAt(int row, int col) {
		//�к�С��0�����ߴ���ArrayList-1�����ݲ�������
		if(row<0||row>students.size()-1){
			return null;
		}

		//�ܵ������У��������С��0���ߴ���3�����ݲ�������
		if(col<0||col>3){
			return null;
		}

		//ͨ���кŻ�ȡһ��ѧ������
		Student stu=(Student)students.get(row);
		//ͨ����������ÿһ�������
		switch(col){
		case 0: return stu.getName();
		case 1: return stu.getAge();
		case 2: return stu.getGender();
		case 3: return stu.getScore();
		default:return null;
		}
	}	
}
