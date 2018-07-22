package io.github.viscent.mtpattern.ch7.pc;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class WorkStealingChannel<T> implements WorkStealingEnabledChannel<T> {
    // 受管队列
    private final BlockingDeque<T>[] managedQueues;


    public WorkStealingChannel(BlockingDeque<T>[] managedQueues) {
        this.managedQueues = managedQueues;
    }

    @Override
    public T take(BlockingDeque<T> preferredQueue) throws InterruptedException {
        return null;
    }

    @Override
    public void put(T product) throws InterruptedException {
        int targetIndex=(product.hashCode() % managedQueues.length);
        BlockingQueue<T> targetQueue=managedQueues[targetIndex];
        targetQueue.put(product);
    }

    @Override
    public T take() throws InterruptedException {
        return take(null);
    }
}
