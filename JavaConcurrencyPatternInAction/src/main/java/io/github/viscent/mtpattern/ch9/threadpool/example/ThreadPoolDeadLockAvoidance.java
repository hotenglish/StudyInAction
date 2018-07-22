package io.github.viscent.mtpattern.ch9.threadpool.example;

import java.util.concurrent.*;

public class ThreadPoolDeadLockAvoidance {

    private final ThreadPoolExecutor threadPool=new ThreadPoolExecutor(
            1,
            //最大线程池大小为1（有限数值）：
            1,
            60,TimeUnit.SECONDS,
            //工作队列为SynchronousQueue：
             new SynchronousQueue<Runnable>(),
            //线程池饱和处理策略为CallerRunsPolicy：
            new ThreadPoolExecutor.CallerRunsPolicy());
            //new ReEnqueueRejectedExecutionHandler());  //用此机制会死锁

    public static void main(String args[]){
        ThreadPoolDeadLockAvoidance me = new ThreadPoolDeadLockAvoidance();
        me.test("<This will NOT deadlock>");
    }

    public void test(final String message){
        Runnable taskA=new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing TaskA...");
                Runnable taskB=new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("TaskB processes "+message);
                    }
                };

                Future<?> result=threadPool.submit(taskB);
                try{
                    // 等待TaskB执行结束才能继续执行TaskA，使TaskA和TaskB称为由依赖关系的两个任务
                    result.get();
                }catch (InterruptedException e){
                    ;
                }catch (ExecutionException e){
                    e.printStackTrace();
                }
                System.out.println("TaskA Done.");
            }
        };
        threadPool.submit(taskA);
    }
}
