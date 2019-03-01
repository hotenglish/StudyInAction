package sort;

public class BubbleSort {

	int[] array;

	public BubbleSort(int[] array) {
		super();
		this.array = array;
	}

	public int[] sortExecute() {
		if (array == null)
			return null;
		boolean noswap = true;
		int temp;
		for (int i = 0; i < array.length - 1; i++) {
			noswap = true;
			for (int j = array.length - 2; j >= i; j--)
				if (array[j + 1] < array[j]) {
					temp = array[j + 1];
					array[j + 1] = array[j];
					array[j] = temp;
					noswap = false;
				}
			if (noswap) {
				break;
			}
		}
		return array;
	}

	public static void main(String[] args) {

		int[] array = new int[] { 49, 13, 65, 97, 76, 38, 27, 49 };
		BubbleSort ss = new BubbleSort(array);
		int[] result = ss.sortExecute();
		for (int k : result) {
			System.out.print(k + " ");
		}
	}

}
