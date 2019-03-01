package day13.exec;


public class ThreadTest1 {
    public ThreadTest1() {
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new MeThread1());
        Thread t2 = new Thread(new MeThread2());
        t1.start();
        t2.start();
    }
}


class MeThread1 implements Runnable {
    public void run() {
        for (int i = 1; i <= 2600; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
        }
    }

}


class MeThread2 implements Runnable {
    public void run() {
        for (char i = 'A'; i <= 'Z'; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
        }
    }

}
