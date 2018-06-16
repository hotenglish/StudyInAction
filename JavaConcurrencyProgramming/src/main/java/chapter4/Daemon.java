package chapter4;

import chapter3.FinalReferenceEscapeExample;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Daemon {

    private static Logger logger = Logger.getLogger(Daemon.class);

    public static void main(String args[]) {
        Thread thread = new Thread(new DaemonRunner());
        thread.setDaemon(true);
        thread.start();
        try {
            System.in.read();   // 接受输入，使程序在此停顿，一旦接收到用户输入，main线程结束，守护线程自动结束
        } catch (IOException ex) {}
    }

    static class DaemonRunner implements Runnable {

        @Override
        public void run() {
            try {
                SleepUtils.second(10);
                logger.info("______");
            } finally {
                logger.info("DaemonRunner finally run.");
            }
        }
    }
}
