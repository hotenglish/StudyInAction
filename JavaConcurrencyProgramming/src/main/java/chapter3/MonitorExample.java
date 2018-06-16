package chapter3;

import org.apache.log4j.Logger;

public class MonitorExample {

    private static Logger logger = Logger.getLogger(VolatileFeaturesExampleEquivalent.class);

    int a = 0;

    public synchronized void writer() { //1
        logger.info("write ready " + a);
        a++;  //2
        logger.info("writed " + a);
    } //3

    public synchronized void reader() { //4
        logger.info("read ready " + a);
        int i = a; //5
        //бнбн
        logger.info("read  " + i);
    } //6

}
