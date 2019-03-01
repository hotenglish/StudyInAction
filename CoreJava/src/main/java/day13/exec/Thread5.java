package day13.exec;


public class Thread5 {
    public Thread5() {
    }

    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        MyThread1 t2 = new MyThread1();
        t1.start();
        t2.start();
    }
}


class MyThread1 extends Thread {

    public void run() {
        for (int i = 1; i <= 2600; i++) {
            System.out.print(i + " ");
        }
    }
}


class MyThread2 extends Thread {
    public void run() {
        for (char i = 'A'; i <= 'Z'; i++) {
            System.out.print(i + " ");
        }
    }
}
