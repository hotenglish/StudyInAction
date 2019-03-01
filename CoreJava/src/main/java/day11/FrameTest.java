package day11;
import javax.swing.*;
import java.awt.*;
public class FrameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("My JFrme");
		frame.setSize(400,300);
		frame.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setSize(100,200);
		panel.setBackground(Color.BLUE);
		
		frame.add(panel);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(arg0)
	}

}
