package com.allen.pattern.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by xuguocai on 2021/2/9 13:48  处理代理类
 */
public class AliCglibProxyFactory {

    /**
     * 根据类clazz获取代理类
     * @param clazz
     * @return
     */
    public static Object getProxy(Class<?> clazz) {
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new AliMethodInterceptor());
        // 创建代理类
        return enhancer.create();
    }

}
