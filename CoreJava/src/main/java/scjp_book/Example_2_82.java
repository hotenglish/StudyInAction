package scjp_book;

public class Example_2_82 {

    public static void main(String args[]) {
        int i = 1;
        int j = i++;
        if (i == (++j) & ((i++) == j)) {
            i += j;
        }
        System.out.println(i);
    }
}
