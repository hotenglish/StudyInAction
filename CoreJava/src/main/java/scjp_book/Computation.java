package scjp_book;

public class Computation extends Thread {

    private int result;

    public Computation() {
    }

    public void run() {
        countPrint(this.result);
    }

    public synchronized void countPrint(int result) {
        result = result;
        result = result + 2;
        System.out.print(result + " ");
        notify();
    }

    public static void main(String[] args) {
        Thread t = new Thread(new Computation());
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }
}
