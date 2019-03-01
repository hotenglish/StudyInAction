package day18;

import java.awt.event.*;
import javax.swing.*;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.*;
import java.util.*;

/**
 * �齱
 * @author new
 *
 */
public class StudentLot extends JFrame {
	private JPanel panel = new JPanel();
	private JButton start = new JButton("��ʼ");
	private JButton end = new JButton("����");
	JButton button = new JButton("             ");
	JPanel tpMain = new JPanel();
	String student[];

	public StudentLot() {
		ButtonMonitor monitor = new ButtonMonitor();
		//����¼�����
		start.addActionListener(monitor);
		end.addActionListener(monitor);
		panel.add(start);
		panel.add(end);
		student = getStudentSet();
		getContentPane().add(panel, "Center");
		getContentPane().add(button, "South");
		setSize(500, 300);
		this.setBackground(Color.WHITE);
		button.setFont(new Font("Serif", Font.BOLD, 40));
		start.setFont(new Font("Serif", 1, 25));
		end.setFont(new Font("Serif", 1, 25));
		this.setLocation(300, 150);
		setVisible(true);
	}

	public static void main(String[] args) {
		new StudentLot();
	}	

	class ButtonMonitor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("��ʼ")) {
				flag = true;
				Guess g = new Guess();
				g.start();
			} else {
				flag = false;
			}
		}
	}

	boolean flag = true;

	class Guess extends Thread {
		public void run() {
			while (flag) {
				synchronized (button) {
					int size = student.length;
					int index = (int) (Math.random() * size);
					//������ʾ������
					button.setText(student[index]);
				}
				try {
					Thread.sleep(50);
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * ��ȡ�ļ�����ȡ��ʾ��Field�е����ݣ������������
	 * @return
	 */
	public String[] getStudentSet() {
		Properties p = new Properties();
		FileInputStream fis = null;
		try {
			//��ȡ�ļ�
			fis = new FileInputStream("");
			p.load(fis);
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex1) {
			System.out.println(ex1.getMessage());
		} catch (Exception ex1) {
			System.out.println(ex1.getMessage());
		}
		int size = p.size();
		String student[] = new String[size];
		/**
		Set set = p.keySet();
		Iterator it = set.iterator();
		int num=0;
		while(it.hasNext()){
			String value = p.getProperty((String)it.next());
			student[num] = isoToGBK(value);
			num++;
		}*/
		for (int i = 1; i <= size; i++) {
			//����key��С��10����ǰ���0������ֱ�ӵ���i
			String key = "";
			if (i < 10) {
				key = "0" + i;
			} else {
				key = String.valueOf(i);
			}
			//���� key�õ�value
			String value = p.getProperty(key);
			//���������õ�student������
			student[i - 1] = isoToGBK(value);
		}
		return student;
	}

	/**
	 * �ַ������ַ�������ת��
	 * @param src          ��Ҫת�����ַ���
	 * @param fromCharSet  �ַ�����ǰ���ַ������ͣ���"iso-8859-1","GBK"��
	 * @param toCharSet    Ŀ���ַ������ͣ���"iso-8859-1","GBK"��
	 * @return             ת������ַ���,ʧ�ܷ���null
	 */
	public static String charSetConvert(String src, String fromCharSet,
			String toCharSet) {
		if (src == null) {
			return src;
		}
		try {
			return new String(src.getBytes(fromCharSet), toCharSet);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ��iso8859���ַ���ת����UTF-8�ַ���
	 * @param src iso8859�ַ���
	 * @return ת������ַ���,ʧ�ܷ���null
	 */
	public static String isoToUTF8(String src) {
		return charSetConvert(src, "iso-8859-1", "UTF-8");
	}

	/**
	 * ��iso8859���ַ���ת����GBK�ַ���
	 * @param src iso8859�ַ���
	 * @return ת������ַ���,ʧ�ܷ���null
	 */
	public static String isoToGBK(String src) {
		return charSetConvert(src, "iso-8859-1", "GBK");
	}

	/**
	 * ��GBK���ַ���ת����iso8859�ַ���
	 * @param src GBK�ַ���
	 * @return ת������ַ���,ʧ�ܷ���null
	 */
	public static String gbkToISO(String src) {
		return charSetConvert(src, "GBK", "iso-8859-1");
	}

}
