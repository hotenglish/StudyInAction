package io.github.viscent.mtpattern.ch13.pipeline;

import java.util.concurrent.*;

/**
 * 基于线程池的Pipe实现类。
 *
 * @param <IN>  输入类型
 * @param <OUT> 输出类型
 * @author Viscent Huang
 */
public class ThreadPoolPipeDecorator<IN, OUT> implements Pipe<IN, OUT> {
    private final Pipe<IN, OUT> delegate;
    private final ExecutorService executorService;

    //线程池停止标志
    private final TerminationToken terminationToken;
    private final CountDownLatch stageProcessDoneLatch = new CountDownLatch(1);

    public ThreadPoolPipeDecorator(Pipe<IN, OUT> delegate,
                                   ExecutorService executorService) {
        this.delegate = delegate;
        this.executorService = executorService;
        this.terminationToken = TerminationToken.newInstance(executorService);
    }

    @Override
    public void init(PipeContext pipeCtx) {
        delegate.init(pipeCtx);
    }

    @Override
    public void process(final IN input) throws InterruptedException {

        Runnable task = new Runnable() {
            @Override
            public void run() {
                int remainingReservations = -1;
                try {
                    delegate.process(input);
                } catch (InterruptedException e) {
                    ;
                } finally {
                    remainingReservations = terminationToken.reservations
                            .decrementAndGet();
                }

                if (terminationToken.isToShutdown() && 0 == remainingReservations) {
                    stageProcessDoneLatch.countDown();
                }
            }
        };

        executorService.submit(task);

        terminationToken.reservations.incrementAndGet();

    }

    @Override
    public void shutdown(long timeout, TimeUnit unit) {
        terminationToken.setIsToShutdown();

        if (terminationToken.reservations.get() > 0) {
            try {
                if (stageProcessDoneLatch.getCount() > 0) {
                    stageProcessDoneLatch.await(timeout, unit);
                }
            } catch (InterruptedException e) {
                ;
            }
        }

        delegate.shutdown(timeout, unit);
    }

    @Override
    public void setNextPipe(Pipe<?, ?> nextPipe) {
        delegate.setNextPipe(nextPipe);
    }

    /**
     * 线程池停止标志。
     * 每个ExecutorService实例对应唯一的一个TerminationToken实例。
     * 这里使用了Two-phase Termination模式（第5章）的思想来停止多个Pipe实例所共用的
     * 线程池实例。
     *
     * @author Viscent Huang
     */
    private static class TerminationToken extends
            io.github.viscent.mtpattern.ch5.tpt.TerminationToken {

        private final static ConcurrentMap<ExecutorService, TerminationToken>
                INSTANCES_MAP
                = new ConcurrentHashMap<ExecutorService, TerminationToken>();

        // 私有构造器
        private TerminationToken() {

        }

        void setIsToShutdown() {
            this.toShutdown = true;
        }

        static TerminationToken newInstance(ExecutorService executorService) {
            TerminationToken token = INSTANCES_MAP.get(executorService);
            if (null == token) {
                token = new TerminationToken();
                TerminationToken existingToken = INSTANCES_MAP.putIfAbsent(
                        executorService, token);
                if (null != existingToken) {
                    token = existingToken;
                }
            }
            return token;
        }
    }

}