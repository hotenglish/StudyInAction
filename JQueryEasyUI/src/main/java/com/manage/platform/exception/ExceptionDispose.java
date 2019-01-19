package com.manage.platform.exception;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

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

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        // TODO Auto-generated method stub
    }

    // 方法後通知
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        // TODO Auto-generated method stub
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println(methodInvocation.getMethod().getName() + " invoking.....");
        return methodInvocation.proceed();
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
