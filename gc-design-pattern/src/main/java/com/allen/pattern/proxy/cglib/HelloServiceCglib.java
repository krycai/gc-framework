package com.allen.pattern.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xuguocai 2020/2/18 18:31
 */
@Slf4j
public class HelloServiceCglib implements MethodInterceptor {

    private Object object;

    /**
     * 创建代理对象
     * @param object
     * @return
     */
    public Object getInstance(Object object){
        log.info("传入的对象:{}",object);
        this.object = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.object.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("===========进入回调方法============");
        log.info("传入的对象:{}",o);
        log.info("传入的方法:{}",method);
        log.info("传入的参数:{}",objects);
        Object resultObj = methodProxy.invokeSuper(o, objects);
        return resultObj;
    }
}
