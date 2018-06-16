package chapter3;

public class VolatileBarrierExample {
    int a = 0;
    volatile int v1 = 1;
    volatile int v2 = 2;

    void readAndWrite() {
        int i = v1;   //第一个volatile读
        int j = v2;   //第二个volatile读
        a = i + j;    //普通写
        v1 = i + 1;   // 第一个volatile写
        v2 = j * 2;   //第二个 volatile写
    }

    //…                 //其他方法
}
