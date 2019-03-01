package day11;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Computer4 {
	private JFrame f;
	private JPanel pNorth;
	private JPanel pCenter;
	private JTextField jf;
	private JButton buttons[];
	
	public Computer4(){
		init();
		eventHandle();
		showMe();
	}
	
	/**
	 * 初始化
	 */
	private void init(){
		f = new JFrame("计算器1.3");
		f.setLayout(new BorderLayout());
		
		pNorth = new JPanel();
		pNorth.setLayout(new FlowLayout());
		
		pCenter = new JPanel();
		pCenter.setLayout(new GridLayout(4,4));
		
		jf = new JTextField(30);
		jf.setFont(new Font("宋体",Font.BOLD,20));
		pNorth.add(jf);
		
		String str = "123+456-789*0.=/";
		buttons = new JButton[str.length()];
		for(int i=0;i<str.length();i++){
			 buttons[i] = new JButton(str.substring(i,i+1));
			 buttons[i].setFont(new Font("楷体",Font.BOLD,20));			
			 pCenter.add( buttons[i]);
		}
		f.add(pNorth,"North");
		f.add(pCenter);
	}
	
	/**
	 * 事件处理
	 */
	private void eventHandle(){
		for(int i=0;i<buttons.length;i++){
			//用成员内部类实现
			 buttons[i].addActionListener(new JButtonEventHandle2());
		}		
	}
	
	class JButtonEventHandle2 implements ActionListener{		
		public void actionPerformed(ActionEvent e) {
			jf.setText(jf.getText()+e.getActionCommand());		
		}	
	}
	
	/**
	 * 显示容器
	 */
	private void showMe(){
		f.setSize(400,300);
		f.setVisible(true);	
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Computer4();		
	}
	
	
}










