package day19;
import java.util.*;
public class GenericTest3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//±àÒëÊ±¸ÅÄî
		ArrayList<String> arr = new ArrayList<String>();
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		if(arr.getClass()==arr1.getClass()){
			System.out.println("==");
		}
	}

}
