package chapter14;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

public class TopNGroupInJavaWay {

    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf()
                .setAppName("JavaTopNGroup")
                .setMaster("local");
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);

        JavaRDD<String> lines = ctx.textFile("src/TopNGroup.txt");

        JavaPairRDD<String, Integer> pairs = lines.mapToPair(line -> {
            String[] splitedLine = line.split(" ");
            return new Tuple2<>(splitedLine[0], Integer.parseInt(splitedLine[1]));
        });

        JavaPairRDD<String, Iterable<Integer>> groupedPairs = pairs.groupByKey();

        final JavaPairRDD<String, Iterable<Integer>> top5 = groupedPairs.mapToPair(groupedData -> {
            Integer[] topList5 = new Integer[5];
            String groupKey = groupedData._1();
            Iterator<Integer> groupValue = groupedData._2().iterator();
            while (groupValue.hasNext()) {
                Integer value = groupValue.next();
                for (int i = 0; i < 5; i++) {
                    if (topList5[i] == null) {
                        topList5[i] = value;
                        break;
                    } else if (value > topList5[i]) {
                        for (int j = 4; j > 1; j--) {
                            topList5[j] = topList5[j - 1];
                        }
                        topList5[i] = value;
                        break;
                    }
                }
            }
            return new Tuple2<>(groupKey, Arrays.asList(topList5));
        });

        top5.foreach(topped -> {
            System.out.println("Group key:" + topped._1());
            Iterator<Integer> toppedValue = topped._2().iterator();
            while (toppedValue.hasNext()) {
                Integer value = toppedValue.next();
                System.out.println(value);
            }
            System.out.println("*******************************************");
        });

        ctx.stop();

    }

}
