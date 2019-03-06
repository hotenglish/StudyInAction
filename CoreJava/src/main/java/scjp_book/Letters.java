package scjp_book;

public class Letters extends Thread {

    private String name;

    public Letters(String name) {
        this.name = name;
    }

    public void write() {
        System.out.print(name);
        System.out.print(name);
    }

    public void run() {

        synchronized (this){
            write();
        }
/*
        synchronized (Letters.class){
            write();
        }

        synchronized (System.out){
            write();
        }

        synchronized (System.out.getClass()) {
            write();
        }
 */
    }

    public static void main(String args[]) {
        new Letters("X").start();
        new Letters("Y").start();
    }
}
