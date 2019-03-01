package day11;

import java.awt.*;

import javax.swing.*;
import javax.swing.JFrame;

public class BorderLayoutTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(500,600);		
		f.setLayout(new BorderLayout());
		f.add(new JButton("込込"),"North");
		f.add(new JButton("細細"),"South");
		f.add(new JButton("3"),"West");
		f.add(new JButton("4"),"East");
		
		
		JTextArea a = new JTextArea();
		f.add(a);
		//f.add(new JButton("6"));
		
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
