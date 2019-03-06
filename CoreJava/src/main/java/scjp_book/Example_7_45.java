package scjp_book;

public class Example_7_45 {

    public static void main(String[] args) {
        final Foo f = new Foo();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                f.doStuff();
            }
        });
        Thread g = new Thread(new Runnable() {
            @Override
            public void run() {
                f.doStuff();
            }
        });
        t.start();
        g.start();
    }
}

class Foo {
    int x = 5;
    public void doStuff() {
        if (x < 10) {
            try {
                wait();
            } catch (InterruptedException ex) {

            }
        } else {
            System.out.println("x is" + x++);
            if (x >= 10) {
                notify();
            }
        }
    }
}
