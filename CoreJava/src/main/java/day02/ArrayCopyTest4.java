package day02;

public class ArrayCopyTest4{
	public static void main(String args[]){
		int arr1[] = {4,3,5,8};
		int arr2[] = arr1;
		arr2[0]=1;
		//System.out.println(arr1);
		//System.out.println(arr2);
		arr1 = null;
		System.out.println(arr1);
		System.out.println(arr2);
	}
}