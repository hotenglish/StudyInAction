package scjp_book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example_9_67 {

    public static void main(String[] args) {
        Integer[] array = {123, 23458, 1, 12, 123456};

/*     ArrayList<Integer> input = Arrays.asList(array);
     ArrayList<Integer> output = process(input);*/

/*        ArrayList<Integer> input = Arrays.asList(array);
        List<Integer> output = process(input);*/

/*       ArrayList<Integer> input = Arrays.asList(array);
        List<Number> output = process(input);*/

/*        List<Number> input = Arrays.asList(array);
        ArrayList<Integer> output = process(input);*/

        List<Number> input = Arrays.asList(array);
        List<Number> output = process(input);

/*        List<Integer> input = Arrays.asList(array);
        List<Integer> output = process(input);*/

        for (Number item : output) {
            System.out.println(item);
        }
    }

    public static <E extends Number> List<E> process(List<E> nums) {
        List<E> output = new ArrayList<E>();
        for (E word : nums) {
            if (word.intValue() > 12) {
                output.add(word);
            }
        }
        return output;
    }
}
