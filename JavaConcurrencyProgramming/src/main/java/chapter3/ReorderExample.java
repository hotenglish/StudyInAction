package chapter3;

import org.apache.log4j.Logger;

class ReorderExample {

    private static Logger logger = Logger.getLogger(ReorderExample.class);

    int a = 0;
    boolean flag = false;
    int i;

    public void writer() {
        a = 1; //1
        logger.info("ReorderExample writer step 1");
        flag = true; //2
        logger.info("ReorderExample writer step 2");
    }

    public void reader() {
        logger.info("ReorderExample reader method begin i=" + i + " a=" + a + " flag=" + flag);
        if (flag) { //3
            i = a * a; //4
            logger.info("ReorderExample reader execute step 4  i=" + i + " a=" + a);
            //s……
        }
    }

}
