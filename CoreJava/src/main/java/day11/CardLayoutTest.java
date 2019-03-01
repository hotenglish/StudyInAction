package day11;
import java.awt.*;
import javax.swing.*;
public class CardLayoutTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		JFrame f = new JFrame("CardLayoutTest");
		f.setSize(400,300);
		
		JPanel p1 = new JPanel();
		p1.setBackground(Color.BLACK);		
		JPanel p2 = new JPanel();
		p2.setBackground(Color.BLUE);		
		JPanel p3 = new JPanel();
		p3.setBackground(Color.GREEN);
		JPanel p4 = new JPanel();
		p4.setBackground(Color.ORANGE);
		JPanel p5 = new JPanel();
		p5.setBackground(Color.RED);
		
		CardLayout c = new CardLayout();
		f.setLayout(c);
		f.add(p1,"p1");
		f.add(p2,"p2");
		f.add(p3,"p3");
		f.add(p4,"p4");
		f.add(p5,"p5");
		f.setVisible(true);
		for(int i=0;i<=10;i++){
			Thread.sleep(1000);
			c.next(f.getContentPane());
		}	
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}









