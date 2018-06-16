package chapter3;

import org.apache.log4j.Logger;

public class FinalReferenceEscapeExample {

    private static Logger logger = Logger.getLogger(FinalReferenceEscapeExample.class);

    final int i;
    static FinalReferenceEscapeExample obj;

    public FinalReferenceEscapeExample() {
        i = 1; //1写final域
        logger.info(" FinalReferenceEscapeExample initialize i=" + i);
        obj = this; //2 this引用在此“逸出”
        logger.info(" FinalReferenceEscapeExample initialize obj ="+this);
    }

    public static void writer() {
        new FinalReferenceEscapeExample();
        logger.info(" FinalReferenceEscapeExample execute writer() obj =" + obj);
    }

    public static void reader() {
        if (obj != null) { //3
            logger.info(" FinalReferenceEscapeExample reader() obj=" + obj);
            int temp = obj.i; //4
            logger.info(" FinalReferenceEscapeExample reader() temp=" + temp);
        }
    }
}
