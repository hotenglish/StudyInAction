package day11;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class EventTest implements ActionListener{
	private JFrame f;
	private JTextArea area;
	private JScrollPane jsp;
	private JButton buttons[];
	
	public EventTest(){
		f = new JFrame();
		buttons = new JButton[4];
		buttons[0] = new JButton("North Button");
		buttons[1] = new JButton("South Button");
		buttons[2] = new JButton("West Button");
		buttons[3] = new JButton("East Button");
		area = new JTextArea();
		area.setFont(new Font("ËÎÌו",Font.BOLD | Font.ITALIC,20));
		area.setEditable(false);
		jsp = new JScrollPane(area);
		init();
		eventHandle();
		showMe();
	}
	
	private void init(){		
		f.setLayout(new BorderLayout());		
		f.add(buttons[0],"North");
		f.add(buttons[1],"South");
		f.add(buttons[2],"West");
		f.add(buttons[3],"East");		
		//f.add(area);
		f.add(jsp);
	}
	
	private void eventHandle(){
		for(int i=0;i<buttons.length;i++){
			 buttons[i].addActionListener(this);
		}		
	}
	
	public void actionPerformed(ActionEvent e) {
		area.append(e.getActionCommand()+" was cliked!\n");		
	}	
	
	private void showMe(){
		f.setSize(500,600);		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new EventTest();
	}

}
