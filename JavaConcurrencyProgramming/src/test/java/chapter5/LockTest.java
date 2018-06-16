package chapter5;

import chapter4.SleepUtils;
import org.apache.log4j.Logger;
import org.junit.*;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class LockTest {

    private static Logger logger = Logger.getLogger(LockTest.class);

    static CountDownLatch start = new CountDownLatch(1);

    static CountDownLatch end;

    static BoundedQueue<String> queue = new BoundedQueue<String>(3);

    static FairAndUnfairTest fairAndUnfairTest=new FairAndUnfairTest();

    static ConditionUseCase conditionUseCase=new ConditionUseCase();

    /**
     * @BeforeClass:这个注解表示这个方法会在所有测试方法执行之前执行 因为是static修饰的静态方法，所有只会执行一次。通常用来进行一些
     * 资源的加载，如通过JUnit测试Spring相关的东西时，可以在这个方法
     * 中加载Spring的配置文件
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        logger.info("this is before class");
    }

    /**
     * @AfterClass:这个注解表示这个方法会在所有方法执行完毕之后执行，通常用来释放资源
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        logger.info("this is after class");
    }

    /**
     * @Before:这个注解表示这个方法会在每个测试方法执行之前执行一次 有多少个测试方法就会执行多少次
     */
    @Before
    public void setUp() throws Exception {
        logger.info("this is before");
    }

    /**
     * @After:这个注解表示这个方法会在每个测试方法执行之后执行一次 有多少个测试方法就会执行多少次
     */
    @After
    public void tearDown() throws Exception {
        logger.info("this is Down");
    }

    @Test
    public void conditionUseCaseTest() throws Exception {
        int ThreadCount = 5;
        end = new CountDownLatch(ThreadCount * 2);
        for (int i = 0; i < ThreadCount; i++) {
            ConditionUseCaseWaiter conditionUseCaseWaiter = new ConditionUseCaseWaiter();
            conditionUseCaseWaiter.setName("conditionUseCaseWaiter-" + i);
            conditionUseCaseWaiter.start();
        }
        for (int i = 0; i < ThreadCount; i++) {
            ConditionUseCaseSignaler conditionUseCaseSignaler = new ConditionUseCaseSignaler();
            conditionUseCaseSignaler.setName("conditionUseCaseSignaler-" + i);
            conditionUseCaseSignaler.start();
        }
        start.countDown();
        end.await();
    }

    private static class ConditionUseCaseWaiter extends Thread {

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                logger.info("conditionUseCase.conditionWait()");
                conditionUseCase.conditionWait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            end.countDown();
        }
    }

    private static class ConditionUseCaseSignaler extends Thread {

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                logger.info("conditionUseCase.conditionSignal()");
                conditionUseCase.conditionSignal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            end.countDown();
        }
    }

    @Test
    public void fairAndUnfairTest() throws Exception {
        fairAndUnfairTest.fair();
        //fairAndUnfairTest.unfair();
    }

    @Test
    public void boundedQueueTest() throws Exception {
        int ThreadCount = 10;
        end = new CountDownLatch(ThreadCount * 2);
        for (int i = 0; i < ThreadCount; i++) {
            BoundedQueueThreadProducer boundedQueueThreadProducer = new BoundedQueueThreadProducer();
            boundedQueueThreadProducer.setName("boundedQueueThreadProducer-" + i);
            boundedQueueThreadProducer.start();
        }
        for (int i = 0; i < ThreadCount; i++) {
            BoundedQueueThreadConsumer boundedQueueThreadConsumer = new BoundedQueueThreadConsumer();
            boundedQueueThreadConsumer.setName("boundedQueueThreadConsumer-" + i);
            boundedQueueThreadConsumer.start();
        }
        start.countDown();
        end.await();
        Assert.assertEquals(0,queue.getSize());
    }

    private static class BoundedQueueThreadProducer extends Thread {

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                try {
                    logger.info("add(" + this.getName() + "-" + i + ")");
                    queue.add(this.getName() + "-" + i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            end.countDown();
        }
    }

    private static class BoundedQueueThreadConsumer extends Thread {

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                try {
                    logger.info(this.getName() + "-" + i + " remove " + queue.remove());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            end.countDown();
        }
    }

    @Test
    public void lockUseCaseTest() {
        int ThreadCount = 10;
        end = new CountDownLatch(ThreadCount);
        for (int i = 0; i < ThreadCount; i++) {
            LockUseCaseThread lockUseCaseThread = new LockUseCaseThread();
            lockUseCaseThread.setName("lockUseCaseThread-" + i);
            lockUseCaseThread.start();
        }
        start.countDown();
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class LockUseCaseThread extends Thread {

        private LockUseCase lockUseCase = new LockUseCase();

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                lockUseCase.lock();
            }
            end.countDown();
        }
    }

    @Test
    public void mutexTest(){
        final Lock lock = new Mutex();
        int ThreadCount = 10;
        // 启动10个线程
        for (int i = 0; i < ThreadCount; i++) {
            Worker w = new Worker(lock);
            w.setDaemon(true);
            w.start();
        }
        // 每隔1秒换行
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }

    @Test
    public void twinsLockTest() {
        final Lock lock = new TwinsLock();
        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker(lock);
            w.setDaemon(true);
            w.start();
        }
        // 每隔1秒换行
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }

    private static class Worker extends Thread {

        private static Lock lock;

        public Worker(Lock newKindOfLock) {
            lock=newKindOfLock;
        }

        public void run() {
            while (true) {
                lock.lock();
                try {
                    SleepUtils.second(1);
                    System.out.println(getName());
                    SleepUtils.second(1);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    @Test
    public void cacheTest() throws Exception {
        int ThreadCount = 10;
        end = new CountDownLatch(ThreadCount);
        for (int i = 0; i < ThreadCount; i++) {
            Thread cacheThread = new CacheThread(10);
            cacheThread.setName("cacheThread-" + i);
            cacheThread.start();
        }
        start.countDown();
        end.await();
        Map<String, Object> map = Cache.getMap();
        for (String key : map.keySet()) {
            logger.info(key + " = " + map.get(key));
        }
        Cache.clear();
        Assert.assertEquals(0,map.size());
    }

    private static class CacheThread extends Thread {

        private static AtomicInteger count = new AtomicInteger();

        private int state;

        public CacheThread(int state) {
            this.state = state;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < state; i++) {
                Cache.input(this.getName() + "-" + i, count.getAndIncrement());
            }
            end.countDown();
        }
    }
}
