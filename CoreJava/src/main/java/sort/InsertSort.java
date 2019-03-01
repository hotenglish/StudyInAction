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
		for (int i = 2; i < array.length; i++) {//���β���array[2]��array[3].....array[n-1]
			array[0] = array[i];//array[0]���ڱ�
			j = i - 1;			
			while (array[0] < array[j])//����array[i]�ʺϵ�λ��
			{
				array[j + 1] = array[j];//���ؼ��ִ���array[i]�ļ�¼������
				j--;
			}
			array[j + 1] = array[0];//����array[i];
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
