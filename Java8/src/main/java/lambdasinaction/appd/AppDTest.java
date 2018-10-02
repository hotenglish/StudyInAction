package lambdasinaction.appd;

public class AppDTest {

    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();
        System.out.println(innerClass.f.apply(new Integer(10)));
        Lambda lambda = new Lambda();
        System.out.println(lambda.f.apply(new Integer(10)));
    }

}
