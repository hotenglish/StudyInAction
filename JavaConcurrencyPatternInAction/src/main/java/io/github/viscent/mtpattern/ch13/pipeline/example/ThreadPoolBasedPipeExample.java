package io.github.viscent.mtpattern.ch13.pipeline.example;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.github.viscent.mtpattern.ch13.pipeline.AbstractPipe;
import io.github.viscent.mtpattern.ch13.pipeline.Pipe;
import io.github.viscent.mtpattern.ch13.pipeline.PipeException;
import io.github.viscent.mtpattern.ch13.pipeline.SimplePipeline;

public class ThreadPoolBasedPipeExample {

    public static void main(String args[]){
        final ThreadPoolExecutor executorService;
        executorService = new ThreadPoolExecutor(1,Runtime.getRuntime()
                .availableProcessors() * 2,60, TimeUnit.MINUTES,
                new SynchronousQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        final SimplePipeline<String, String> pipeline = new SimplePipeline<String, String>();
        Pipe<String, String> pipe = new AbstractPipe<String, String>() {

            @Override
            public String doProcess(String input) throws PipeException {
                String result= input + "->[pipe1," + Thread.currentThread().getName()
                        + "]";
                System.out.println(result);
                return result;
            }
        };

        pipeline.addAsThreadPoolBasedPipe(pipe, executorService);

        pipe = new AbstractPipe<String, String>() {

            @Override
            public String doProcess(String input) throws PipeException {
                String result = input + "->[pipe2," + Thread.currentThread().getName()
                        + "]";
                System.out.println(result);
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    ;
                }
                return result;
            }
        };

        pipeline.addAsThreadPoolBasedPipe(pipe, executorService);

        pipe = new AbstractPipe<String, String>() {

            @Override
            public String doProcess(String input) throws PipeException {
                String result = input + "->[pipe3," + Thread.currentThread().getName()
                        + "]";
                ;
                System.out.println(result);
                try {
                    Thread.sleep(new Random().nextInt(200));
                } catch (InterruptedException e) {
                    ;
                }
                return result;
            }

            @Override
            public void shutdown(long timeout, TimeUnit unit){

                // 在最后一个Pipe中关闭线程池
                executorService.shutdown();
                try {
                    executorService.awaitTermination(timeout, unit);
                }catch (InterruptedException e){
                    ;
                }
            }
        };

        pipeline.addAsThreadPoolBasedPipe(pipe, executorService);

        pipeline.init(pipeline.newDefaultPipelineContext());

        int N=100;
        try {
            for(int i = 0;i < N; i++) {
                pipeline.process("Task-" + i);
            }
        } catch (InterruptedException e){
            ;
        } catch (Exception e){
            ;
        }

        pipeline.shutdown(10,TimeUnit.SECONDS);

    }
}
