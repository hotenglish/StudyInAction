package day11;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridLayoutTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(500,600);		
		f.setLayout(new GridLayout(3,3));
		f.add(new JButton("1"));
		f.add(new JButton("2"));
		f.add(new JButton("3"));
		f.add(new JButton("4"));
		f.add(new JButton("5"));
		f.add(new JButton("6"));
		
		
		f.setVisible(true);

	}

}
