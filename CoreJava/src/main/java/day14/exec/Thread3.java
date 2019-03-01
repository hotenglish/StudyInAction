package day14.exec;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Thread3 {
    private ArrayList Numbers;
    private int sort = 0;
    private boolean writeable = true;
    static int Num = 0;

    public Thread3() {
        Numbers = new ArrayList();

    }

    public synchronized Object getNumber() {
        while (this.Numbers.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.print(e);
            }
        }

        Object o = Numbers.get(0);
        Numbers.remove(0);
        notify();
        return o;
    }

    public int getTotal() {
        return Numbers.size() - 1;
    }

    public synchronized int genernateNumber() {
        Num = Num + 1;
        this.Numbers.add(new Long(Num));
        notify();
        return Num;
    }


    public static void main(String[] args) {
        Thread3 thread3 = new Thread3();
        for (int i = 1; i < 4; i++) {
            String name = "����" + String.valueOf(i);
            windows win = new windows("����" + i, thread3);
            win.setVisible(true);
        }
        window w = new window("ȡ�Ŵ���", thread3);
        w.setVisible(true);
    }
}


class windows extends JFrame implements ActionListener {
    JButton b = new JButton("���Ű�ť");
    JTextField tf = new JTextField(25);
    JPanel p = new JPanel();
    Thread3 thread3;
    String name;
    public windows(String name, Thread3 thread3) {
        this.thread3 = thread3;
        this.name = name;
        this.setTitle(name);
        this.setSize(300, 200);
        p = (JPanel)this.getContentPane();
        p.setLayout(new FlowLayout());
        b.addActionListener(this);
        p.add(b);
        p.add(tf);
    }

    public void actionPerformed(ActionEvent e) {
        Get g = new Get(thread3, this);
        g.start();
    }
}


class window extends JFrame implements ActionListener {
    JButton b = new JButton("ȡ�Ű�ť");
    JTextField tf = new JTextField(25);
    JPanel p = new JPanel();
    Thread3 thread3;
    String name;
    public window(String name, Thread3 thread3) {
        this.thread3 = thread3;
        this.name = name;
        this.setTitle(name);
        this.setSize(300, 200);
        p = (JPanel)this.getContentPane();
        p.setLayout(new FlowLayout());
        b.addActionListener(this);
        p.add(b);
        p.add(tf);
    }

    public void actionPerformed(ActionEvent e) {
        Genernate genernate = new Genernate(thread3,this);
        genernate.start();
    }
}


class Get extends Thread {
    private Thread3 t;
    private windows win;
    public Get(Thread3 d, windows win) {
        this.t = d;
        this.win = win;
    }

    public void run() {
        int i;
        synchronized (t) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            win.tf.setText("");
            this.win.tf.setText("��" + t.getNumber() + "�ŵ�" + win.name);
        }
    }
}


class Genernate extends Thread {
    private Thread3 t;
    private window w;
    public Genernate(Thread3 t, window w) {
        this.t = t;
        this.w = w;
    }

    public void run() {
        synchronized (t) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            w.tf.setText("");
            w.tf.setText("���ĺ�����" + t.genernateNumber() + "��ǰ�滹��" +
                         t.getTotal() + "���ڵȴ�");
        }
    }
}
