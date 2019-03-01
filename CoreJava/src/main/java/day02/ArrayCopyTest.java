package day02;

public class ArrayCopyTest{
	public static void main(String args[]){
		int srcArray[] = {1,2,3,4};
		int destArray[] = {6,7,8,9};
		System.arraycopy(srcArray,2,destArray,0,2);
		//2代表源数组要拷贝的元素索引，0代表要拷到目标数组的元素的索引位置
		//2代表源数组要拷贝的元素个数
		for(int i=0;i<destArray.length;i++){
			System.out.print(destArray[i]+" ");
		}
		
	}
}
