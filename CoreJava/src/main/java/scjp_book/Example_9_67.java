package scjp_book;

import java.util.ArrayList;
import java.util.List;

public class Example_9_67 {

    public static void main(String[] args) {
      /*  ArrayList<Integer> input = null;
        ArrayList<Integer> output = null;
        ArrayList<Integer> input = null;
        List<Integer> output = null;


       ArrayList<Integer> input = null;
        List<Number> output = null;

        List<Number> input = null;
        ArrayList<Integer> output = null;
*/
        List<Number> input = null;
        List<Number> output = null;
/*
        List<Integer> input = null;
        List<Integer> output = null;
*/
        //output = process(input);


    }

    public static <E extends Number> List<? super E> process(List<E> nums) {
        List<Number> output=new ArrayList<Number>();
        return output;
    }
}
