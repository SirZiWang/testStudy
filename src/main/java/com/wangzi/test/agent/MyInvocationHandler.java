package com.wangzi.test.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler{
	
	  //目标对象
    private Object obj = null;


    //获取目标对象的代理对象
    public Object getProxy(Object obj){
        this.obj = obj;
//        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), obj.getClass().getInterfaces(), this);
    }

    //控制执行目标对象的方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("目标对象方法执行之前");
        Object result = method.invoke(obj, args);
        System.out.println("目标对象方法执行之后");
        return result;
    }
}
