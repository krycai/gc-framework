package com.allen.pattern.proxy.dynamic;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xuguocai 2020/2/18 18:13
 *
 * 代理类,实现的是接口
 *
 */
@Slf4j
public class HelloServiceProxy implements InvocationHandler {

    /**
     * 真实服务对象
     */
    private Object object;

    public Object bind(Object object){
        this.object = object;
        log.info("对象是:{}",this);
        log.info("=-=-=-：{}",object);
        //取得代理对象
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    /**
     * 通过代理对象调用方法首先进入这个方法
     * @param proxy  代理对象
     * @param method 被调用方法
     * @param args   方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("==========================");
        Object result = null;
        log.info("代理的对象:{}",proxy);
        log.info("执行的方法:{}",method);
        log.info("执行的参数:{}",args);
        result = method.invoke(object, args);
        log.info("---------------------------------");
        return result;
    }
}
