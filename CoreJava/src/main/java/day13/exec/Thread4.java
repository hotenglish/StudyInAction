package day13.exec;


import java.lang.*;

public class Thread4 {
    public Thread4() {
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new thread1());
        Thread t2 = new Thread(new thread2());
        t1.setPriority(2);
        t2.setPriority(1);
        t1.start();
        t2.start();
    }
}


class thread1 implements Runnable {
    public void run() {
        for (int i = 1; i <= 52; ) {
            System.out.print(i + " ");
            System.out.print(++i + " ");
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


class thread2 implements Runnable {
    public void run() {
        for (char i = 'A'; i <= 'Z'; i++) {
            System.out.print(i + " ");
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
