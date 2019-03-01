package day11;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Computer3 {
	private JFrame f;
	private JPanel pNorth;
	private JPanel pCenter;
	private JTextField jf;
	private JButton buttons[];
	
	public Computer3(){
		init();
		eventHandle();
		showMe();
	}
	
	/**
	 * ��ʼ��
	 */
	private void init(){
		f = new JFrame("������1.2");
		f.setLayout(new BorderLayout());
		
		pNorth = new JPanel();
		pNorth.setLayout(new FlowLayout());
		
		pCenter = new JPanel();
		pCenter.setLayout(new GridLayout(4,4));
		
		jf = new JTextField(30);
		jf.setFont(new Font("����",Font.BOLD,20));
		pNorth.add(jf);
		
		String str = "123+456-789*0.=/";
		buttons = new JButton[str.length()];
		//����¼��������
		for(int i=0;i<str.length();i++){
			 buttons[i] = new JButton(str.substring(i,i+1));
			 buttons[i].setFont(new Font("����",Font.BOLD,20));			
			 pCenter.add( buttons[i]);
		}
		f.add(pNorth,"North");
		f.add(pCenter);
	}
	
	/**
	 * �¼�����
	 */
	private void eventHandle(){
		for(int i=0;i<buttons.length;i++){
			//���ⲿ��ʵ��
			 buttons[i].addActionListener(new JButtonEventHandle(jf));
		}		
	}
	
	/**
	 * ��ʾ����
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
		new Computer3();		
	}

}


class JButtonEventHandle implements ActionListener{
	private JTextField jf;
	public JButtonEventHandle(JTextField jf){
		this.jf = jf;
	}
	public void actionPerformed(ActionEvent e) {
		jf.setText(jf.getText()+e.getActionCommand());		
	}	
}







