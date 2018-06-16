package chapter11;

public interface IMsgQueue {

    void put(Message msg);

    Message take();

}
