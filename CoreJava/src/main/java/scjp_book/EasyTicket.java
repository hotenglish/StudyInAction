package scjp_book;

public class EasyTicket {

    static String str1 = "Hello";

    static String str2 = "He" + new String("llo");

    public static void main(String args[]) {
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));
    }

}
