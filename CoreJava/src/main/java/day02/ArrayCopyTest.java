package day02;

public class ArrayCopyTest{
	public static void main(String args[]){
		int srcArray[] = {1,2,3,4};
		int destArray[] = {6,7,8,9};
		System.arraycopy(srcArray,2,destArray,0,2);
		//2����Դ����Ҫ������Ԫ��������0����Ҫ����Ŀ�������Ԫ�ص�����λ��
		//2����Դ����Ҫ������Ԫ�ظ���
		for(int i=0;i<destArray.length;i++){
			System.out.print(destArray[i]+" ");
		}
		
	}
}
