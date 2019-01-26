package com.manage.platform.exception;


import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;


public class ExceptionDispose implements MethodBeforeAdvice, AfterReturningAdvice, MethodInterceptor, ThrowsAdvice {

    /*
     * (non-Javadoc)
     *
     * @see net.sf.cglib.proxy.MethodInterceptor#intercept(java.lang.Object,
     * java.lang.reflect.Method, java.lang.Object[],
     * net.sf.cglib.proxy.MethodProxy)
     */

    public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
        // TODO Auto-generated method stub
        return null;
    }


    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
        // TODO Auto-generated method stub
    }

    // 方法後通知
    public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
        // TODO Auto-generated method stub

    }

    public Object invoke(MethodInvocation arg0) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println(arg0.getMethod().getName() + " invoking.....");

        return arg0.proceed();
    }

    public void afterThrowing(Throwable throwable) {
    }

    public void afterThrowing(Method method, Object[] args, Object target, Throwable throwable) {
        System.out.println("产生异常的方法名称：  " + method.getName());

        for (Object o : args) {
            System.out.println("方法的参数：   " + o.toString());
        }

        System.out.println("代理对象：   " + target.getClass().getName());
        System.out.println("抛出的异常:    " + throwable.getMessage() + ">>>>>>>" + throwable.getCause());
        System.out.println("异常详细信息：↓");

        throwable.printStackTrace();
    }
}
