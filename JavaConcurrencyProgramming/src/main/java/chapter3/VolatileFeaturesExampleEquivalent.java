package chapter3;

import org.apache.log4j.Logger;

public class VolatileFeaturesExampleEquivalent {

    private static Logger logger = Logger.getLogger(VolatileFeaturesExampleEquivalent.class);

    long vl = 0; // 64位的long型普通变量

    public synchronized long get() {  //对单个的普通变量的写用同一个锁同步
        logger.info("VolatileFeaturesExampleEquivalent get v1 as " + vl);
        return vl;
    }

    public void getAndIncrement() {   //普通方法调用
        long temp = get(); //调用已同步的读方法
        temp += 1;    //普通写操作
        set(temp);    //调用已同步的写方法
        logger.info("VolatileFeaturesExampleEquivalent getAndIncrement as " + vl);
    }

    public synchronized void set(long l) {//对单个的普通变量的读用同一个锁同步
        vl = l;
        logger.info("VolatileFeaturesExampleEquivalent set v1 as " + l);
    }

}
