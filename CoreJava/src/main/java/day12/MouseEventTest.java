package day12;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MouseEventTest {
	private JFrame f;
	private JTextArea area;
	private JScrollPane jsp;
	private JButton buttons[];
	
	public MouseEventTest(){
		f = new JFrame("MouseEventTest");
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
			buttons[i].addMouseListener(new MouseListenerTest());
		}
	}
	
	class MouseListenerTest implements MouseListener{
		public void mouseClicked(MouseEvent m) {
			area.append("Mouse clicked at :("+m.getX()+
					": "+m.getY()+")\n");
		}

		public void mouseEntered(MouseEvent arg0) {
			area.append("Mouse entered.....\n");
		}

		public void mouseExited(MouseEvent arg0) {
			area.append("Mouse Exited.....\n");
		}

		public void mousePressed(MouseEvent arg0) {
			area.append("Mouse  Pressed.....\n");
		}

		public void mouseReleased(MouseEvent arg0) {
			area.append("Mouse  Released.....\n");
		}
		
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
		new MouseEventTest();
	}

}

