package com.allen.boot.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Created by xuguocai on 2021/3/26 9:05
 *
 * 是beanFactory的后置处理器
 *
 * 调用时机：在BeanFactory标准初始化之后调用，这时所有的bean定义已经保存加载到beanFactory，但是bean的实例还未创建
 *
 * 能干什么：来定制和修改BeanFactory的内容，如覆盖或添加属性
 */
//@Component
public class AllenBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public AllenBeanFactoryPostProcessor() {
        // 调用父类
        super();
        System.out.println("这是BeanFactoryPostProcessor实现类构造器！！");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor 的 postProcessBeanFactory 方法");

        // 根据 person 名字 获取bean信息
        BeanDefinition bd = configurableListableBeanFactory.getBeanDefinition("person");
        // 给bean 赋值
        bd.getPropertyValues().addPropertyValue("name", "110");

    }
}
