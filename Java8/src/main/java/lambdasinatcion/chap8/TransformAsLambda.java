package lambdasinatcion.chap8;

public class TransformAsLambda {

    interface Task {
        void execute();
    }

    public static void doSomeThing(Runnable r) {
        r.run();
    }

    public static void doSomeThing(Task a) {
        a.execute();
    }

    public static void main(String args[]) {
        TransformAsLambda.doSomeThing((Task) () -> System.out.println("Danger danger!!"));
    }

}
