package io.github.viscent.mtpattern.ch9.threadpool.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolSize4IOIntensiveTask {

    public static void main(String args[]) throws InterruptedException {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(//核心线程池大小为
                 1,
                //最大线程池大小为2*Ncpu
                Runtime.getRuntime().availableProcessors()*2,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(200));
        executor.submit(new IOIntensiveTask());
        executor.submit(new IOIntensiveTask());
        //Thread.sleep(1000);
        executor.shutdown();
    }

    // 某个I/O密集型任务
    private static class IOIntensiveTask implements Runnable {

        @Override
        public void run() {
            // 执行大量的I/O操作
            try {
                System.out.println("IOIntensiveTask!");
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
