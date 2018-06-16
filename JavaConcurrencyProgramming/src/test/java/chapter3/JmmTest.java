package chapter3;

import org.apache.log4j.Logger;
import org.junit.*;

public class JmmTest {

    private static Logger logger = Logger.getLogger(JmmTest.class);

    private static ReorderExample reorderExample = new ReorderExample();

    private static VolatileExample volatileExample = new VolatileExample();

    private static VolatileFeaturesExample volatileFeaturesExample = new VolatileFeaturesExample();

    private static SynchronizedExample synchronizedExample = new SynchronizedExample();

    private static ReentrantLockExample reentrantLockExample = new ReentrantLockExample();

    private static VolatileFeaturesExampleEquivalent volatileFeaturesExampleEquivalent = new VolatileFeaturesExampleEquivalent();

    private static MonitorExample monitorExample = new MonitorExample();

    private static FinalExample finalExample = null;

    private static FinalReferenceExample finalReferenceExample = null;

    private static FinalReferenceEscapeExample finalReferenceEscapeExample=null;
/*
    {
        finalExample.writer();
    }*/

    /**
     * @BeforeClass:这个注解表示这个方法会在所有测试方法执行之前执行 因为是static修饰的静态方法，所有只会执行一次。通常用来进行一些
     * 资源的加载，如通过JUnit测试Spring相关的东西时，可以在这个方法
     * 中加载Spring的配置文件
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        //logger.info("this is before class");
    }

    /**
     * @AfterClass:这个注解表示这个方法会在所有方法执行完毕之后执行，通常用来释放资源
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        //logger.info("this is after class");
    }

    /**
     * @Before:这个注解表示这个方法会在每个测试方法执行之前执行一次 有多少个测试方法就会执行多少次
     */
    @Before
    public void setUp() throws Exception {
        //logger.info("this is before");
    }

    /**
     * @After:这个注解表示这个方法会在每个测试方法执行之后执行一次 有多少个测试方法就会执行多少次
     */
    @After
    public void tearDown() throws Exception {
        logger.info("this is Down");
    }

    @Test
    public void testFinalReferenceEscapeExample() throws Exception {
        Thread writer = new Thread(new Runnable() {
            @Override
            public void run() {
                finalReferenceEscapeExample.writer();
            }
        });

        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                finalReferenceEscapeExample.reader();
            }
        });
        writer.start();
        reader.start();
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFinalReferenceExample() throws Exception {
        Thread writerOne = new Thread(new Runnable() {
            @Override
            public void run() {
                finalReferenceExample.writerOne();
            }
        });
        writerOne.setName("writerOne");
        writerOne.start();
        Thread writerTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                finalReferenceExample.writerTwo();
            }
        });
        writerTwo.setName("writerTwo");
        writerTwo.start();
        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                finalReferenceExample.reader();
            }
        });
        reader.setName("reader");
        reader.start();
        try {
            Thread.sleep(5000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFinalExample() throws Exception {
        Thread writer = new Thread(new Runnable() {
            @Override
            public void run() {
                finalExample.writer();
            }
        });
        writer.setName("writer");
        writer.start();
        try {
            Thread.sleep(20l);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                finalExample.reader();
            }
        });
        reader.setName("reader");
        reader.start();
        try {
            Thread.sleep(1000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMonitorExample() throws Exception {
        Thread writer = new Thread(new Runnable() {
            @Override
            public void run() {
                monitorExample.writer();
            }
        });
        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                monitorExample.reader();
            }
        });
        writer.start();
        reader.start();
        try {
            Thread.sleep(3000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReentrantLockExample() throws Exception {
        Thread writer = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockExample.writer();
            }
        });
        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockExample.reader();
            }
        });
        writer.start();
        reader.start();
        try {
            Thread.sleep(5000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testVolatileFeaturesExampleEquivalent() throws Exception {
        Thread writer = new Thread(new Runnable() {
            @Override
            public void run() {
                volatileFeaturesExampleEquivalent.set(4);
                volatileFeaturesExampleEquivalent.getAndIncrement();
            }
        });
        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info(volatileFeaturesExampleEquivalent.get());
            }
        });
        writer.start();
        reader.start();
        try {
            Thread.sleep(5000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testVolatileFeaturesExample() throws Exception {
        Thread writer = new Thread(new Runnable() {
            @Override
            public void run() {
                volatileFeaturesExample.set(4);
                volatileFeaturesExample.getAndIncrement();
            }
        });
        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info(volatileFeaturesExample.get());
            }
        });
        writer.start();
        reader.start();
        try {
            Thread.sleep(2000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSynchronizedExample() throws Exception {
        Thread writer = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedExample.writer();
            }
        });
        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedExample.reader();
            }
        });
        writer.start();
        reader.start();
        try {
            Thread.sleep(7000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testVolatileExample() throws Exception {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                volatileExample.writer();
            }
        });
        a.start();
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                volatileExample.reader();
            }
        });
        b.start();
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReorderExample() throws Exception {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {

                reorderExample.writer();
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                reorderExample.reader();
            }
        });
        a.start();
        b.start();
        try {
            Thread.sleep(8000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
