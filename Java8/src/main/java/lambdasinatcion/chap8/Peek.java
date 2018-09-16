package lambdasinatcion.chap8;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Peek {

    public static void main(String args[]) {

        List<Integer> result = Arrays.asList(2, 3, 4, 5);
        result.stream().peek(x -> System.out.println("taking from stream: " + x)).map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x)).filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter: " + x)).limit(3)
                .peek(x -> System.out.println("after limit: " + x)).collect(toList());
    }
}
