package day11;
import javax.swing.*;

import java.awt.*;
public class FlowLayoutTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(500,600);		
		
		f.add(new JButton("1"));
		f.add(new JButton("2"));
		f.add(new JButton("3"));
		f.add(new JButton("4"));
		f.add(new JButton("5"));
		f.add(new JButton("6"));
		
		f.setLayout(new FlowLayout(FlowLayout.CENTER,20,40));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
