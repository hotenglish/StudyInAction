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
 * 抽奖
 * @author new
 *
 */
public class StudentLot extends JFrame {
	private JPanel panel = new JPanel();
	private JButton start = new JButton("开始");
	private JButton end = new JButton("结束");
	JButton button = new JButton("             ");
	JPanel tpMain = new JPanel();
	String student[];

	public StudentLot() {
		ButtonMonitor monitor = new ButtonMonitor();
		//添加事件监听
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
			if (command.equals("开始")) {
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
					//设置显示的姓名
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
	 * 读取文件，获取显示在Field中的数据，存放在数组中
	 * @return
	 */
	public String[] getStudentSet() {
		Properties p = new Properties();
		FileInputStream fis = null;
		try {
			//读取文件
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
			//设置key，小于10，则前面加0，否则，直接等于i
			String key = "";
			if (i < 10) {
				key = "0" + i;
			} else {
				key = String.valueOf(i);
			}
			//根据 key得到value
			String value = p.getProperty(key);
			//把姓名设置到student数组中
			student[i - 1] = isoToGBK(value);
		}
		return student;
	}

	/**
	 * 字符串的字符集类型转换
	 * @param src          需要转换的字符串
	 * @param fromCharSet  字符串当前的字符集类型，如"iso-8859-1","GBK"等
	 * @param toCharSet    目标字符集类型，如"iso-8859-1","GBK"等
	 * @return             转换后的字符串,失败返回null
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
	 * 将iso8859的字符集转换成UTF-8字符集
	 * @param src iso8859字符串
	 * @return 转化后的字符串,失败返回null
	 */
	public static String isoToUTF8(String src) {
		return charSetConvert(src, "iso-8859-1", "UTF-8");
	}

	/**
	 * 将iso8859的字符集转换成GBK字符集
	 * @param src iso8859字符串
	 * @return 转化后的字符串,失败返回null
	 */
	public static String isoToGBK(String src) {
		return charSetConvert(src, "iso-8859-1", "GBK");
	}

	/**
	 * 将GBK的字符集转换成iso8859字符集
	 * @param src GBK字符串
	 * @return 转化后的字符串,失败返回null
	 */
	public static String gbkToISO(String src) {
		return charSetConvert(src, "GBK", "iso-8859-1");
	}

}
