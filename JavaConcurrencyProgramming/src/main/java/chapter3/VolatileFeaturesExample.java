package chapter3;

import org.apache.log4j.Logger;

public class VolatileFeaturesExample {

    private static Logger logger = Logger.getLogger(VolatileFeaturesExample.class);

    volatile long vl = 0;//使用volatile声明64位的long型变量

    public void set(long l) {
        vl = l; //单个volatile变量的写
        logger.info("VolatileFeaturesExample set v1 as " + l);
    }

    public void getAndIncrement() {
        vl++;   //复合（多个）volatile变量的读/写
        logger.info("VolatileFeaturesExample getAndIncrement as " + vl);
    }

    public long get() {
        logger.info("VolatileFeaturesExample get v1 as " + vl);
        return vl; //单个volatile变量的读
    }
}
