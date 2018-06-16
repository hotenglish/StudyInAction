package chapter11;

import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * 总消息队列管理
 */
public class MsgQueueManager implements IMsgQueue {

    private static Logger logger = Logger.getLogger(MsgQueueManager.class);

    /**
     * 消息总队列
     */
    public final BlockingQueue<Message> messageQueue;

    public MsgQueueManager() {
        messageQueue = new LinkedTransferQueue<Message>();
    }

    public void put(Message msg) {
        try {
            messageQueue.put(msg);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Message take() {
        try {
            return messageQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    public static BlockingQueueInstance getInstance() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static BlockingQueueInstance instance = new BlockingQueueInstance();
    }

    static class BlockingQueueInstance {

        private List<BlockingQueue<Message>> subMsgQueues = new ArrayList<BlockingQueue<Message>>();

        /**
         * 均衡获取一个子队列。
         *
         * @return
         */
        public BlockingQueue<Message> getSubQueue() {
            int errorCount = 0;
            for (; ; ) {
                if (subMsgQueues.isEmpty()) {
                    return null;
                }
                int index = (int) (System.nanoTime() % subMsgQueues.size());
                try {
                    return subMsgQueues.get(index);
                } catch (Exception e) {
                    // 出现错误表示，在获取队列大小之后，队列进行了一次删除操作
                    logger.error("获取子队列出现错误", e);
                    if ((++errorCount) < 3) {
                        continue;
                    }
                }
            }
        }

        public void addSubMsgQueue(BlockingQueue<Message> subMsgQueue) {
            subMsgQueues.add(subMsgQueue);
        }
    }

}
