package day14;


import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.*;

public class JTableTest extends JFrame {
	private JTable table;
	private JScrollPane jsp;
	//ͨ��ArrayList�������װ��ѧ�����󣩣�
	//����JTableTestʵ��
	public JTableTest(ArrayList arr){
		//ͨ��StudentTableModel��ģ�ʹ���JTable����
		table=new JTable(new StudentTableModel(arr));

		//�ѱ�����ڻ��������
		jsp=new JScrollPane(table);

		//�ڶ�����������ӻ������
		this.add(jsp,BorderLayout.CENTER);
	}
	public void showMe(){
		this.setSize(800,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//��ArrayList�����ѧ������
		ArrayList arr=new ArrayList();
		arr.add(new Student("xiaoxiao",20,"male",90));
		arr.add(new Student("wangwu",22,"male",90));
		arr.add(new Student("lisi",20,"female",99));
		arr.add(new Student("zhangsan",20,"male",90));
		arr.add(new Student("xiaoxiao1",20,"male",90));
		arr.add(new Student("wangwu1",22,"male",90));
		arr.add(new Student("lisi1",20,"female",99));
		arr.add(new Student("zhangsan1",20,"male",90));
		arr.add(new Student("xiaoxiao3",20,"male",90));
		arr.add(new Student("wangwu3",22,"male",90));
		arr.add(new Student("lisi3",20,"female",99));
		arr.add(new Student("zhangsan3",20,"male",90));
		new JTableTest(arr).showMe();
	}

}
