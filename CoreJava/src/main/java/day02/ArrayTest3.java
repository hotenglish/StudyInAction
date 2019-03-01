package day02;

public class ArrayTest3{
	public static void main(String args[]){
		int arr[][] = {{2,3},{5,6},{1,9,10}};
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}
