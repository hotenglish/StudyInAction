package day12;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class EventTest implements ActionListener{
	private JFrame frame;
	private JTextArea area;
	private JTextField field;
	private JScrollPane pane;
	
	public EventTest(){
		frame = new JFrame("EventTest");
		area = new JTextArea();
		area.setEditable(false);
		field = new JTextField(20);
		pane = new JScrollPane(area);
		init();
		eventHandle();
		showMe();
	}
	public void init(){
		frame.setLayout(new BorderLayout());
		frame.add(pane);
		frame.add(field,"South");		
	}
	
	public void showMe(){
		frame.setSize(400,300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void eventHandle(){
		field.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		JTextField f = (JTextField)o;
		area.append(f.getText()+"\n");		
		//area.append(field.getText()+"\n");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new EventTest();
	}
	

}
