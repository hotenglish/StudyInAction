package chapter3;

import org.apache.log4j.Logger;

public class FinalReferenceExample {
    final int[] intArray; //final是引用类型
    static FinalReferenceExample obj;

    private static Logger logger = Logger.getLogger(FinalExample.class);

    public FinalReferenceExample() {//构造函数
        intArray = new int[1]; //1
        logger.info(" FinalReferenceExample initialize intArray=" + intArray);
        intArray[0] = 1;  //2
        logger.info(" FinalReferenceExample initialize intArray[0]=" + intArray[0]);
    }

    public static void writerOne() { //写线程A执行
        logger.info(" FinalReferenceExample writeOne() begin");
        obj = new FinalReferenceExample(); //3
        logger.info(" FinalReferenceExample writeOne() obj=" + obj);
    }

    public static void writerTwo() { //写线程B执行
        logger.info(" FinalReferenceExample writeTwo() ");
        obj.intArray[0] = 2; //4
        logger.info(" FinalReferenceExample writeTwo()  intArray[0]=" + obj.intArray[0]);
    }

    public static void reader() { //读线程C执行
        if (obj != null) {  //5
            logger.info(" FinalReferenceExample reader() obj.intArray[0]=" + obj.intArray[0]);
            int temp = obj.intArray[0]; //6
            logger.info(" FinalReferenceExample reader() temp=" + temp);
        }
    }
}
