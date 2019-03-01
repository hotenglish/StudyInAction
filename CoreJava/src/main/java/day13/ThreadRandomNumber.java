package day13;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ThreadRandomNumber extends JFrame {
	private JTextField tf;

	private JPanel panel;

	private JButton start;

	private JButton end;

	public ThreadRandomNumber() {
		tf = new JTextField(20);
		panel = new JPanel();
		start = new JButton("Start");
		end = new JButton("End");

		panel.add(start);
		panel.add(end);
		add(tf, "North");
		add(panel);
		eventHandle();
		showMe();
	}

	private void eventHandle() {
		start.addActionListener(new ButtonEvent());
		end.addActionListener(new ButtonEvent());
	}

	public static void main(String[] args) {
		new ThreadRandomNumber();
	}

	public void showMe() {
		setSize(200, 100);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class ButtonEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("Start")) {
				/*
				while (true) {									
					int result = (int) (Math.random() * 50) + 1;
					tf.setText(result + "");					
				}*/
				ShowNumberThread s = new ShowNumberThread();
				s.start();
			}
		}
	}

	class ShowNumberThread extends Thread {
		public void run() {
			while (true) {	
				synchronized(tf){
					int result = (int) (Math.random() * 50) + 1;
					tf.setText(result + "");
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
