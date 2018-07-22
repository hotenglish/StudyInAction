package io.github.viscent.mtpattern.ch1;

public class ThreadCreationViaSubclass {

    public static void main(String agrgs[]){
        Thread thread=new CustomThread();
        thread.start();
    }

    static class CustomThread extends Thread {
        @Override
        public void run(){
            System.out.println("Running...");
        }
    }
}
