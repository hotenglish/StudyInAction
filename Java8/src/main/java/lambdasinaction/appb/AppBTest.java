package lambdasinaction.appb;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class AppBTest {

    public static void main(String... args) {
        LongAdder adder = new LongAdder();
        adder.add(10);
        //...
        long sum = adder.sum();
        System.out.println("adder.sum()->" + sum);

        LongAccumulator acc = new LongAccumulator(Long::sum, 0);
        acc.accumulate(10);
        //...
        long result = acc.get();
        System.out.println("acc.get()->" + result);

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("max", new Integer(200));
        map.put("min", new Integer(2));
        Optional<Integer> maxValue = Optional.of(map.reduceValues(1, Integer::max));
        System.out.println("maxValue->" + maxValue.get());

        //setAll example
        int[] evenNumbers = new int[10];
        Arrays.setAll(evenNumbers, i -> i * 2);
        Arrays.stream(evenNumbers).forEach(System.out::print);
        System.out.println();

        //parallelPrefix
        int[] ones = new int[10];
        Arrays.fill(ones, 1);
        Arrays.parallelPrefix(ones, (a, b) -> a + b);
        Arrays.stream(ones).forEach(System.out::print);
        System.out.println();

        String authors = String.join(",", "Raoul", "Mario", "Alan");
        System.out.println(authors);
    }
}
