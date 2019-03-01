package day14;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class StudentTableModel  extends AbstractTableModel {
	private ArrayList students;
	//通过ArrayList（里面封装了学生对象）
	//构建显示学生信息的表格
	public StudentTableModel(ArrayList students){
		this.students=students;
	}
	
	//定义列数
	public int getColumnCount() {		
		return 4;
	}
	
	//定义每一列的名称
	public String getColumnName(int col) {
		switch(col){
		case 0:return "姓名";
		case 1:return "age";
		case 2:return "gender";
		case 3:return "score";
		default:return null;
		}
	}
	
	//定义行数
	public int getRowCount() {
		return students.size();
	}
	
	//通过行号和列号，获取每一格显示的内容
	public Object getValueAt(int row, int col) {
		//行号小于0，或者大于ArrayList-1，传递参数错误
		if(row<0||row>students.size()-1){
			return null;
		}

		//总的有四列，因此列数小于0或者大于3，传递参数错误
		if(col<0||col>3){
			return null;
		}

		//通过行号获取一个学生对象
		Student stu=(Student)students.get(row);
		//通过列数返回每一格的内容
		switch(col){
		case 0: return stu.getName();
		case 1: return stu.getAge();
		case 2: return stu.getGender();
		case 3: return stu.getScore();
		default:return null;
		}
	}	
}
