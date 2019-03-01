package day13.exec;

import java.util.*;

public class CarStore {
	private ArrayList CarArray;
	private boolean drivable = true;
	private int CarNo, sort = 0;

	public CarStore() {
		CarArray = new ArrayList();
	}

	public synchronized Object driveOut() {
		while (this.CarArray.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		Object o = this.CarArray.get(0);
		// this.CarNo=Integer.parseInt(o);
		this.CarArray.remove(0);
		notifyAll();
		return o;
	}

	public synchronized int driveIn(int num) {
		if (this.CarArray.size() < 4) {
			this.drivable = true;
		} else {
			this.drivable = false;
		}
		while (!drivable || sort % 3 + 1 != num) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		sort = sort + 1;
		this.CarArray.add(new Integer(num));
		this.CarNo = num;
		notifyAll();
		return this.CarNo;
	}

	public int getCarNO() {
		return this.CarNo;
	}

	public static void main(String[] args) {
		CarStore da = new CarStore();
		DriveOut dro = new DriveOut(da);
		DriveIn r1 = new DriveIn(da, 1);
		DriveIn r2 = new DriveIn(da, 2);
		DriveIn r3 = new DriveIn(da, 3);
		DriveIn r4 = new DriveIn(da, 4);
		dro.start();
		r1.start();
		r2.start();
		r3.start();
		r4.start();
	}
}

class DriveOut extends Thread {
	CarStore da;

	public DriveOut(CarStore d) {
		this.da = d;
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
				System.out.println("Drive out CarNo " + i + " :"
						+ da.driveOut());
			}
		}
	}
}

class DriveIn extends Thread {
	CarStore da;
	int num;

	public DriveIn(CarStore da, int n) {
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
			System.out
					.println("Number" + num + " get CarNO:" + da.driveIn(num));
		}
	}
}
