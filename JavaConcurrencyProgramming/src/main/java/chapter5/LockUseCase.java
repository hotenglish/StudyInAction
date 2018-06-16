package chapter5;

import org.apache.log4j.Logger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUseCase {

    private static Logger logger = Logger.getLogger(LockUseCase.class);

    private static int count=0;

    public void lock() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            logger.info(count++);
        } finally {
            lock.unlock();
        }
    }
}
