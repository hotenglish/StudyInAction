package chapter3;

import org.apache.log4j.Logger;

class SynchronizedExample {

    private static Logger logger = Logger.getLogger(SynchronizedExample.class);

    int a = 0;
    boolean flag = false;
    int i;

    public synchronized void writer() { //获取锁
        a = 1;
        logger.info("SynchronizedExample writer step 1");
        flag = true;
        logger.info("SynchronizedExample writer step 2");
    } //释放锁

    public synchronized void reader() { //获取锁
        logger.info("SynchronizedExample reader method begin i=" + i + " a=" + a + " flag=" + flag);
        if (flag) {
            int i = a;
            logger.info("SynchronizedExample reader execute step 4  i=" + i + " a=" + a);
            //……
        } //释放锁
    }
}
