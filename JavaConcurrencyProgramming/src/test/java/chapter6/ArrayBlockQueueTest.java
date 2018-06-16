package chapter6;

import org.apache.log4j.Logger;
import org.junit.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockQueueTest {

    private static Logger logger = Logger.getLogger(ArrayBlockQueueTest.class);

    /**
     * @BeforeClass:这个注解表示这个方法会在所有测试方法执行之前执行
     * 因为是static修饰的静态方法，所有只会执行一次。通常用来进行一些
     * 资源的加载，如通过JUnit测试Spring相关的东西时，可以在这个方法
     * 中加载Spring的配置文件
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        logger.info("this is before class");
    }

    /**
     *  @AfterClass:这个注解表示这个方法会在所有方法执行完毕之后执行，通常用来释放资源
     *  */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        logger.info("this is after class");
    }

    /**
     * @Before:这个注解表示这个方法会在每个测试方法执行之前执行一次
     * 有多少个测试方法就会执行多少次
     */
    @Before
    public void setUp() throws Exception {
        logger.info("this is before");
    }

    /**
     * @After:这个注解表示这个方法会在每个测试方法执行之后执行一次
     * 有多少个测试方法就会执行多少次
     */
    @After
    public void tearDown() throws Exception {
        logger.info("this is Down");
    }

    @Test
    public void ArrayBlockQueueTest() throws Exception {
        BlockingQueue<String> queue=new ArrayBlockingQueue<String>(3);
        queue.put("1");
        queue.put("2");
        queue.put("3");
        System.out.println(queue.take());
        queue.put("4");

        //System.out.println(queue.toArray(new String[0]));
        //String e1=queue.take();


    }

    @Test
    public void ArrayBlockQueueTest2() throws Exception {

/*        for(int i=73;i<=150;i++){
            System.out.println("SERVICE_TYPE_VW_SERVICE_TYPE_SPARE"+i+" = "+i+";");
        }*/


        for(int i=102;i<=150;i++){
            System.out.println("SERVICE_MODE_VW_SPARE_MODE"+i+ " = "+i+";");
        }
    }
}
