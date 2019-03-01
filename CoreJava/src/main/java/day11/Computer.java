package day11;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Computer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame("¼ÆËãÆ÷1.0");
		f.setSize(400,300);
		f.setLayout(new BorderLayout());
		
		JPanel pNorth = new JPanel();
		pNorth.setLayout(new FlowLayout());
		
		JPanel pCenter = new JPanel();
		pCenter.setLayout(new GridLayout(4,4));
		
		final JTextField jf = new JTextField(30);
		jf.setFont(new Font("ËÎÌå",Font.BOLD,20));
		pNorth.add(jf);
		
		String str = "123+456-789*0.=/";		
		for(int i=0;i<str.length();i++){
			JButton button = new JButton(str.substring(i,i+1));
			button.setFont(new Font("¿¬Ìå",Font.BOLD,20));
			//button.setBackground(Color.LIGHT_GRAY);
			//button.setForeground(Color.BLUE);
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					/*
					JButton b = (JButton)e.getSource();
					jf.setText(jf.getText()+b.getText());*/
					jf.setText(jf.getText()+e.getActionCommand());
				}				
			});
			pCenter.add(button);
		}
		
		f.add(pNorth,"North");
		f.add(pCenter);
		f.setVisible(true);	
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}







