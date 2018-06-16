package chapter3;

import org.apache.log4j.Logger;
import org.junit.*;

public class InstanceInitializationTest {

    private static Logger logger = Logger.getLogger(InstanceInitializationTest.class);

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
    public void testInstanceFactory() throws Exception {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                InstanceFactory.Instance instance = InstanceFactory.getInstance();
                logger.info("A thread instance=" + instance + " and var=" + instance.getVar());
            }
        });
        a.setName("A");
        a.start();
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                InstanceFactory.Instance instance = InstanceFactory.getInstance();
                logger.info("B thread instance=" + instance + " and var=" + instance.getVar());
            }
        });
        b.setName("B");
        b.start();
        try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSafeDoubleCheckedLocking() throws Exception {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                SafeDoubleCheckedLocking.Instance instance = SafeDoubleCheckedLocking.getInstance();
                logger.info("A thread instance=" + instance + " and var=" + instance.getVar());
            }
        });
        a.setName("A");
        a.start();
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                SafeDoubleCheckedLocking.Instance instance = SafeDoubleCheckedLocking.getInstance();
                logger.info("B thread instance=" + instance + " and var=" + instance.getVar());
            }
        });
        b.setName("B");
        b.start();
        try {
            Thread.sleep(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoubleCheckedLocking() throws Exception {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                DoubleCheckedLocking.Instance instance = DoubleCheckedLocking.getInstance();
                logger.info("A thread instance=" + instance + " and var=" + instance.getVar());
            }
        });
        a.setName("A");
        a.start();
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                DoubleCheckedLocking.Instance instance = DoubleCheckedLocking.getInstance();
                logger.info("B thread instance=" + instance + " and var=" + instance.getVar());
            }
        });
        b.setName("B");
        b.start();
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testsafeLazyInitialization() throws Exception {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                SafeLazyInitialization.Instance instance = SafeLazyInitialization.getInstance();
                logger.info("A thread instance=" + instance);
            }
        });
        a.setName("A");
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                SafeLazyInitialization.Instance instance = SafeLazyInitialization.getInstance();
                logger.info("B thread instance=" + instance);
            }
        });
        b.setName("B");
        a.start();
        b.start();
        try {
            Thread.sleep(1000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnsafeLazyInitialization() throws Exception {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                UnsafeLazyInitialization.Instance instance = UnsafeLazyInitialization.getInstance();
                logger.info("A thread instance=" + instance);
            }
        });
        a.setName("A");
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                UnsafeLazyInitialization.Instance instance = UnsafeLazyInitialization.getInstance();
                logger.info("B thread instance=" + instance);
            }
        });
        b.setName("B");
        a.start();
        b.start();
        try {
            Thread.sleep(2000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
