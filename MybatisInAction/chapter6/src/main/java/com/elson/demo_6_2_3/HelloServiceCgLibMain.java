package com.elson.demo_6_2_3;

import com.elson.demo_6_1_2.HelloService;
import com.elson.demo_6_1_2.HelloServiceImpl;

/**
 * 6-2-2 JDK动态代理
 */
public class HelloServiceCgLibMain {

    public static void main(String args[]) {
        HelloServiceCgLib helloServiceCgLib=new HelloServiceCgLib();
        HelloService proxy =(HelloService)helloServiceCgLib.getInstance(new HelloServiceImpl());
        proxy.sayHello("张三");
    }

}
