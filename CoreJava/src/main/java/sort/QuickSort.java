package sort;

public class QuickSort {

	int[] array;

	public QuickSort(int[] array, int start, int end) {
		this.array = array;
		this.sortExecute(start, end);
	}

	public int PartTition(int[] array, int l, int h) {

		int i=l,j=h,temp=array[i];
		do{			
			while((array[j]>=temp)&&(i<j))				
			{
				j--;
			}			
			if(i<j)
			{
			array[i++]=array[j];			
			}
			while((array[i]<=temp)&&(i<j))
				i++;
			if(i<j)
				array[j--]=array[i];			
		}while(i!=j);
			array[i]=temp;
		return i;
	}

	public void sortExecute(int start, int end) {
		int i;
		if (start < end) {
			i = this.PartTition(array, start, end);
			this.sortExecute(start, i - 1);
			this.sortExecute(i+1, end);
		}
	}

	public int[] getArray() {
		return array;
	}

	public static void main(String[] args) {
		int[] array = new int[] { 49, 13, 65, 97, 76, 38, 27, 49 };
		QuickSort ss = new QuickSort(array,0,array.length-1);
		int[] result = ss.getArray();
		for (int k : result) {
			System.out.print(k + " ");
		}
	}

}
