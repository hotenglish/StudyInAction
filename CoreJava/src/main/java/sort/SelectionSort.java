package sort;

public class SelectionSort {

	int[] array;

	public SelectionSort(int[] array) {
		this.array = array;
	}

	public int[] sortExecute() {
		
		if (this.array == null)
			return null;
		int k = 0, temp;
		for (int i = 0; i < array.length - 1; i++) {
			k = i;

			for (int j = i + 1; j < array.length; j++)
				if (array[j] < array[k])
					k = j;
			if (k != i) {
				temp = array[i];
				array[i] = array[k];
				array[k] = temp;
			}
		}
		return array;

	}

	public static void main(String[] args) {
		int[] array = new int[] { 49, 13, 65, 97, 76, 38, 27, 49 };
		SelectionSort ss = new SelectionSort(array);
		int[] result = ss.sortExecute();
		for (int k : result) {
			System.out.print(k + " ");
		}

	}
}
