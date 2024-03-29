package com.elson.demo_6_1_2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloServiceProxy implements InvocationHandler {

    /**
     * 真实服务对象.
     */
    private Object target;

    /**
     * 绑定委托对象并返回一个代理类.
     */
    Object bind(Object object) {
        this.target = object;
        // 取得代理对象.
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this); // JDK代理需要提供接口
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("JDK 动态代理");
        Object result;
        // 反射方法前调用.
        System.err.println("准备说hello.");
        // 执行方法,相当于调用HelloServiceImpl类的sayHello方法.
        result = method.invoke(target, args);
        // 反射方法后调用.
        System.err.println("说过hello了.");
        return result;
    }
}