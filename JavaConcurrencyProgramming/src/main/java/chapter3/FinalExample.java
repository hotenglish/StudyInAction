package chapter3;

import org.apache.log4j.Logger;

public class FinalExample {

    private static Logger logger = Logger.getLogger(FinalExample.class);

    int i; //普通变量
    final int j; //final变量
    static FinalExample obj;

    public FinalExample() { //构造函数
        i = 1;  //写普通域
        logger.info(" FinalExample initialize i=" + i);
        j = 2;  //写final域
        logger.info(" FinalExample initialize j=" + j);
    }

    public static void writer() { //写线程A执行
        obj = new FinalExample();
        logger.info(" FinalExample execute writer() obj =" + obj);
    }

    public static void reader() { //读线程B执行
        FinalExample object = obj; //读对象引用
        logger.info(" FinalExample execute reader() object =" + object);
        int a = object.i; //读普通域
        logger.info(" FinalExample execute reader() a =" + a);
        int b = object.j; //读final域
        logger.info(" FinalExample execute reader() b =" + b);
    }

}
