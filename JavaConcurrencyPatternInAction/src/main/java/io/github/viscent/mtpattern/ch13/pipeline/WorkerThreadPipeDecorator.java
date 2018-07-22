package io.github.viscent.mtpattern.ch13.pipeline;

import io.github.viscent.mtpattern.ch5.tpt.AbstractTerminatableThread;
import io.github.viscent.mtpattern.ch5.tpt.TerminationToken;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 基于工作者线程的Pipe实现类。
 * 提交到该Pipe的任务由指定个数的工作者线程共同处理。
 * 该类使用了Two-phase Termination模式（参见第5章）。
 *
 * @author Viscent Huang
 *
 * @param <IN>
 *          输入类型
 * @param <OUT>
 *          输出类型
 */
public class WorkerThreadPipeDecorator<IN, OUT> implements Pipe<IN, OUT> {
    protected final BlockingQueue<IN> workQueue;
    private final Set<AbstractTerminatableThread> wordThreads=new HashSet<AbstractTerminatableThread>();
    private final TerminationToken terminationToken = new TerminationToken();

    private final Pipe<IN, OUT> delegate;

    public WorkerThreadPipeDecorator(Pipe<IN, OUT> delegate, int workerCount) {
        this(new SynchronousQueue<IN>(), delegate, workerCount);
    }

    public WorkerThreadPipeDecorator(BlockingQueue<IN> workQueue,
        Pipe<IN, OUT> delegate, int workerCount) {
        if(workerCount <= 0) {
            throw new IllegalArgumentException("workerCount should be positive!");
        }

        this.workQueue = workQueue;

        this.delegate = delegate;
        for(int i = 0;i < workerCount; i++){
            wordThreads.add(new AbstractTerminatableThread(terminationToken) {
                @Override
                protected void doRun() throws Exception {
                    try {
                        dispatch();
                    }finally {
                        terminationToken.reservations.decrementAndGet();
                    }

                }
            });
        }

    }

    protected void dispatch() throws InterruptedException {
        IN input = workQueue.take();
        delegate.process(input);
    }

    @Override
    public void init(PipeContext pipeCtx){
        delegate.init(pipeCtx);
        for(AbstractTerminatableThread thread : wordThreads){
            thread.start();
        }
    }

    @Override
    public void process(IN input) throws InterruptedException {
        workQueue.put(input);
        terminationToken.reservations.incrementAndGet();
    }

    @Override
    public void shutdown(long timeout, TimeUnit unit){
        for(AbstractTerminatableThread thread : wordThreads) {
            thread.terminate();
            try{
                thread.join(TimeUnit.MILLISECONDS.convert(timeout, unit));
            }catch (InterruptedException e){
            }
        }
        delegate.shutdown(timeout, unit);
    }

    @Override
    public void setNextPipe(Pipe<?, ?> nextPipe){
        delegate.setNextPipe(nextPipe);
    }

}
