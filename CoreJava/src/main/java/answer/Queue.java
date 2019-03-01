package answer;

public class Queue {
	private double sum50 = 0, sum = 0;

	public Queue() {
	}

	public double getSum50() {
		double a = 1;
		for (int n = 1; n <= 50; n++) {
			sum50 += Math.pow(-1.0, (n + 1)) / (a + (n - 1) * 2);
		}
		return sum50;
	}

	public double getSum() {
		double a = 1;
		for (int n = 1; n <= Integer.MAX_VALUE; n++) {
			if (Math.abs(Math.pow(-1.0, (n + 1)) / (a + (n - 1) * 2)) < 0.00001) {
				break;
			} else
				sum += Math.pow(-1.0, (n + 1)) / (a + (n - 1) * 2);
		}
		return sum;
	}

	public static void main(String args[]) {
		Queue Q = new Queue();
		System.out.println("The sum50 is " + Q.getSum50() + " and the sum is "
				+ Q.getSum());
	}
}