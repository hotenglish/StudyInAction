package day02;
import java.util.Arrays;
public class ArraySortTest{
	public static void main(String args[]){
		int arr[] = {9,3,6,1,5,4,2};
		
		Arrays.sort(arr,1,5);//第五个元素排除在外，不会参与排序！
		//Arrays.sort(arr,0,arr.length);
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
	}
}
