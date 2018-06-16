package chapter11;

import org.apache.log4j.Logger;
import org.junit.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedTransferQueue;
import static org.junit.Assert.assertThat;

public class MsgQueueManagerTest {

    private static Logger logger = Logger.getLogger(MsgQueueManagerTest.class);

    private static CountDownLatch end=new CountDownLatch(1);

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
    public void MsgQueueManagerTest() throws Exception {
        MsgQueueManager msgQueueManager = new MsgQueueManager();
        msgQueueManager.put(new Message("1"));
        msgQueueManager.put(new Message("2"));
        msgQueueManager.put(new Message("3"));
        logger.info(msgQueueManager.take().getContent());
        logger.info(msgQueueManager.take().getContent());
        logger.info(msgQueueManager.take().getContent());
        //logger.info(msgQueueManager.take().getContent());
    }

    @Test
    public void MsgQueueManagerTest2() throws Exception {
        IMsgQueue messageQueue = MsgQueueFactory.getMessageQueue();
        for (int i = 0; i < 10; i++) {
            messageQueue.put(new Message(String.valueOf(i)));
        }
        messageQueue.put(new Message("End")); //End作为消息结束标记
        MsgQueueManager.BlockingQueueInstance blockingQueueInstance=MsgQueueManager.getInstance();
        BlockingQueue<Message> subMsgQueue1= new LinkedTransferQueue<Message>();
        blockingQueueInstance.addSubMsgQueue(subMsgQueue1);
        BlockingQueue<Message> subMsgQueue2= new LinkedTransferQueue<Message>();
        blockingQueueInstance.addSubMsgQueue(subMsgQueue2);
        DispatchMessageTask dispatchMessageTask =new DispatchMessageTask();
        Thread thread=new Thread(dispatchMessageTask);
        thread.start();
        end.await();
        for(Message message:subMsgQueue1){
            logger.info(message);
        }
        for(Message message:subMsgQueue2){
            logger.info(message);
        }
    }

    static class DispatchMessageTask implements Runnable {

        public void run() {
            BlockingQueue<Message> subQueue;
            for (; ; ) {
                //如果没有数据，则阻塞在这里
                Message msg = null;
                msg = MsgQueueFactory.getMessageQueue().take();
                //如果为空，则表示没有Session机器连接上来，
                //需要等待，直到Session有机器连接上来
                while ((subQueue = MsgQueueManager.getInstance().getSubQueue()) == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                }

                try {
                    subQueue.put(msg);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                /* 若Message内容为End，则作为跳出循环的标记*/
                if("End".equals(msg.getContent())){
                    break;
                }
            }
            end.countDown();
        }
    }

}
