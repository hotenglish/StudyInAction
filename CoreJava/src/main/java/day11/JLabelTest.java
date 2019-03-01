package day11;
import java.awt.*;
import javax.swing.*;
public class JLabelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame("JLabelTest");
		f.setSize(300,200);
		
		JLabel label = new JLabel("label");
		label.setFont(new Font("¿¬Ìå",Font.BOLD,20));
		
		JLabel label2 = new JLabel("label two");
		label2.setIcon(new ImageIcon("E:/workspace/0706/src/gui/46.gif"));
		label2.setFont(new Font("ºÚÌå",Font.BOLD ,40));
		
		f.setLayout(new BorderLayout());
		f.add(label,"North");
		f.add(label2);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//JTextArea

	}

}





