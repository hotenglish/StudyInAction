package io.github.viscent.mtpattern.ch5.tpt.example;

import io.github.viscent.mtpattern.ch5.tpt.AbstractTerminatableThread;
import io.github.viscent.util.Debug;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SomeService {

    private final BlockingQueue<String> queue=new ArrayBlockingQueue<String>(100);

    private final Producer producer=new Producer();

    private final Consumer comsumer=new Consumer();

    private class Producer extends AbstractTerminatableThread{

        private int i=0;

        @Override
        protected void doRun() throws Exception{
            queue.put(String.valueOf(i++));
            Debug.info(" ******* Produce i:"+(i-1));
            comsumer.terminationToken.reservations.incrementAndGet();
        }

    }

    private class Consumer extends AbstractTerminatableThread{

        @Override
        protected void doRun() throws Exception{
            String Product=queue.take();
            Debug.info(" ###### Consum:"+Product);
            // 模拟执行真正操作的时间消耗
            try{
                 Thread.sleep(new Random().nextInt(100));
            }catch(InterruptedException e){
                ;
            }
            terminationToken.reservations.decrementAndGet();
        }

    }

    public void shutdown(){
        //生产者线程停止后再停止消费者线程
        producer.terminate();
        comsumer.terminate(true);
    }

    public void init(){
        producer.start();
        comsumer.start();
    }

    public static void main(String args[]) throws Exception{
        SomeService someService=new SomeService();
        someService.init();
        Thread.sleep(100);
        someService.shutdown();
    }

}
