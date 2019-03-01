package answer;

import java.util.*;
import java.math.*;

public class ArrayReverse {
	private static int[] array;
	int temp;

	public ArrayReverse() {
		array = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (int i = 0; i < array.length / 2; i++) {
			temp = array[i];
			array[i] = array[array.length - i - 1];
			array[array.length - i - 1] = temp;
		}
	}

	public static void main(String args[]) {
		ArrayReverse tt = new ArrayReverse();
		for (int i = 0; i <= 10; i++)
			System.out.print(array[i] + " ");
	}
}