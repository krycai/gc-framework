package com.allen.boot.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by xuguocai on 2021/3/26 9:26
 *
 * Bean后置处理器
 *
 */
public class AllenBeanPostProcessor implements BeanPostProcessor {

    public AllenBeanPostProcessor(){
        System.out.println("这是BeanPostProcessor实现类构造器！！");
    }

    /**
     * 在每一个bean对象的初始化方法调用之前回调
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("AllenBeanPostProcessor 的 postProcessBeforeInitialization 的方法");
        return bean;
    }

    /**
     * 在每个bean对象的初始化方法调用之后被回调
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("AllenBeanPostProcessor 的 postProcessAfterInitialization 的方法");
        return bean;
    }
}
