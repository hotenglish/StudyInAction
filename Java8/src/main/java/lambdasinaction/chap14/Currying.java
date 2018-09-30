package lambdasinaction.chap14;

import java.util.function.DoubleUnaryOperator;

public class Currying {

    public static void main(String args[]) {

        double f = converter(24, 9.0 / 5, 32);
        double gbp = converter(100, 0.6, 0);
        double mi = converter(20, 0.6214, 0);

        System.out.println(f);
        System.out.println(gbp);
        System.out.println(mi);

        DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

        System.out.println(convertCtoF.applyAsDouble(24));
        System.out.println(convertUSDtoGBP.applyAsDouble(100));
        System.out.println(convertKmtoMi.applyAsDouble(20));

        DoubleUnaryOperator convertFtoC = expandedCurriedConverter(-32, 5.0 / 9, 0);
        System.out.println(convertFtoC.applyAsDouble(98.6));
    }

    static double converter(double x, double y, double z) {
        return x * y + z;
    }

    static DoubleUnaryOperator curriedConverter(double y, double z) {
        return (double x) -> x * y + z;
    }

    static DoubleUnaryOperator expandedCurriedConverter(double w, double y, double z) {
        return (double x) -> (x + w) * y + z;
    }

}
