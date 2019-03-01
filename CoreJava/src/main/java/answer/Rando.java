package answer;
import java.util.*;

public class Rando {
    private static int[] num;
    public Rando() {
        num = new int[100];
        for (int i = 0; i < 100; i++) {
            num[i] = (int) (Math.random() * 999);
        }
    }


    public static boolean preme(int n) {
        boolean flag = true;
        for (int j = 2; j < n / 2 && flag; j++) {
            if (n % j == 0) {
                flag = false;
            }
        }
        return flag;
    }


    public static void main(String args[]) {
        Rando R = new Rando();
        for (int i = 0; i < 100; i++) {
            if (preme(num[i])) {
                System.out.println(num[i]);
            }
        }
    }
}