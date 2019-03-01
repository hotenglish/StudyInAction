package day13.exec;


public class ThreadCarStore {
    private int CarNo;
    private boolean drivable = true;
    private int sort = 0;
    public ThreadCarStore() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public synchronized void driveOut() {
        while (!drivable) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        this.CarNo = ((int) Math.random() * 10);
        drivable = false;
        notifyAll();
    }


    public synchronized int driveIn(int num) {
        while (drivable || sort % 3 + 1 != num) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        sort = sort + 1;
        drivable = true;
        notifyAll();
        return CarNo;
    }

    public int getCarNo() {
        return CarNo;
    }

    public static void main(String[] args) {
        ThreadCarStore da = new ThreadCarStore();
        MyDriveOut dro = new MyDriveOut(da);
        MyDriveIn r1 = new MyDriveIn(da, 1);
        MyDriveIn r2 = new MyDriveIn(da, 2);
        MyDriveIn r3 = new MyDriveIn(da, 3);
        MyDriveIn r4 = new MyDriveIn(da, 4);
        dro.start();
        r1.start();
        r2.start();
        r3.start();
        r4.start();
    }

    private void jbInit() throws Exception {
    }
}


class MyDriveOut extends Thread {
    ThreadCarStore da;
    public MyDriveOut(ThreadCarStore d) {
        da = d;
    }

    public void run() {
        int i;
        synchronized (da) {
            for (i = 1; i < 4; i++) {
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                da.driveOut();
                System.out.println("Drive CarNo" + i + ":" + da.getCarNo());
            }
        }
    }
}


class MyDriveIn extends Thread {
    ThreadCarStore da;
    int num;
    public MyDriveIn(ThreadCarStore da, int n) {
        this.da = da;
        this.num = n;
    }

    public void run() {
        synchronized (da) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println("Number" + num + "get data:" + da.driveIn(num));
        }
    }
}
