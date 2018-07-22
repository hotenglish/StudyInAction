package io.github.viscent.mtpattern.ch1;

public class JavaThreadCreationAndRun {

    public static void main(String args[]){
        System.out.println("The main method was executed by thread:"
                + Thread.currentThread().getName());
        Help help=new Help("Java Thread AnyWhere");
        //创建一个线程
        Thread thread=new Thread(help);
        //设置线程名
        thread.setName("A-Worker-Thread");
        //启动线程
        thread.start();
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
    }//end of Helper
}
