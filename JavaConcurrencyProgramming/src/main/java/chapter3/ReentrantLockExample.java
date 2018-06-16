package chapter3;

import org.apache.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private static Logger logger = Logger.getLogger(ReentrantLockExample.class);

    int a = 0;
    ReentrantLock lock = new ReentrantLock();

    public void writer() {
        lock.lock(); //获取锁
        try {
            a++;
            logger.info("writer a++ is " + a);
        } finally {
            lock.unlock();  //释放锁
        }
    }

    public void reader() {
        lock.lock(); //获取锁
        try {
            int i = a;
            logger.info("reader i=" + i);
            //……
        } finally {
            lock.unlock(); //释放锁
        }
    }

}

