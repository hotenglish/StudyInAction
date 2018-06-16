package chapter3;

import org.apache.log4j.Logger;

public class VolatileExample {

    private static Logger logger = Logger.getLogger(ReorderExample.class);

    int a = 0;
    volatile boolean flag = false;
    int i;

    public void writer() {
        a = 1; //1
        logger.info("VolatileExample writer step 1");
        flag = true; //2
        logger.info("VolatileExample writer step 2");
    }

    public void reader() {
        logger.info("VolatileExample reader method begin i=" + i + " a=" + a + " flag=" + flag);
        if (flag) { //3
            i = a; //4
            logger.info("VolatileExample reader execute step 4  i=" + i + " a=" + a);
            //бнбн
        }
    }

}
