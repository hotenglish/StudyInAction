package io.github.viscent.mtpattern.ch1;

public class JavaThreadAnywhere {

    public static void main(String args[]){
        System.out.println("The main method was executed by thread:"
                + Thread.currentThread().getName());
        Help help=new Help("Java Thread AnyWhere");
        help.run();
    }

    static class Help implements Runnable{
        private final String message;

        public Help(String message) {
            this.message = message;
        }

        private void doSomething(String message){
            System.out.println("The doSomething method was executed by thread:"
                    + Thread.currentThread().getName());
            System.out.println("Do something with " + message);
        }

        @Override
        public void run() {
            this.doSomething(message);
        }
    }
}
