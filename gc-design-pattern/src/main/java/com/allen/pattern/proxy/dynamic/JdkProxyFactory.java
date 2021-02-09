package com.allen.pattern.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * Created by xuguocai on 2021/2/9 14:00  获取代理对象的工厂类
 */
public class JdkProxyFactory {
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 目标类的类加载
                target.getClass().getInterfaces(),  // 代理需要实现的接口，可指定多个
                new JdkInvocationHandler(target)   // 代理对象对应的自定义 InvocationHandler
        );
    }
}
