package sort;

public class InsertSort {

	int[] array;

	public InsertSort(int[] array) {
		this.array = array;
	}

	public int[] sortExecute() {
		if (array == null)
			return null;
		int j;
		for (int i = 2; i < array.length; i++) {//依次插入array[2]、array[3].....array[n-1]
			array[0] = array[i];//array[0]是哨兵
			j = i - 1;			
			while (array[0] < array[j])//查找array[i]适合的位置
			{
				array[j + 1] = array[j];//将关键字大于array[i]的记录往后移
				j--;
			}
			array[j + 1] = array[0];//插入array[i];
		}
		return array;
	}

	public static void main(String[] args) {
		int[] array = new int[] { 49, 13, 65, 97, 76, 38, 27, 49 };
		InsertSort ss = new InsertSort(array);
		int[] result = ss.sortExecute();
		for (int k : result) {
			System.out.print(k + " ");
		}
	}

}
