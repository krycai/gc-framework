package com.allen.boot.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * Created by xuguocai on 2021/3/25 17:06
 *
 * 实现 BeanFactoyAware 接口的 bean 可以直接访问 Spring 容器，被容器创建以后，它会拥有一个指向 Spring 容器的引用，
 * 可以利用该bean根据传入参数动态获取被spring工厂加载的bean
 *
 */
//@Component
public class BeanFactoryAwareConfig implements BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("--------BeanFactoryAware-------BeanFactoryAware");
    }
}
