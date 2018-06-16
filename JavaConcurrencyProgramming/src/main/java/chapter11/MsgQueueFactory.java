package chapter11;

public class MsgQueueFactory {

    private static MsgQueueManager msgQueueManager;

    public static synchronized IMsgQueue getMessageQueue() {
        if(msgQueueManager==null){
            msgQueueManager=new MsgQueueManager();
        }
        return msgQueueManager;
    }

}
