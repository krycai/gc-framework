package com.allen.boot.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

/**
 * Created by xuguocai on 2021/3/26 8:53
 *
 * 加载顺序： BeanNameAware  --》BeanFactoryAware --》InitializingBean
 *
 */
@Component
public class Person implements InitializingBean, BeanFactoryAware, BeanNameAware, DisposableBean {

    private String name;

    private Integer age;

    private Integer money;

    public Person() {
        System.out.println("第二步：执行Person类的无参构造函数");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("第三步：调用setName方法");
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("第四步：调用setAge方法");
        this.age = age;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware 的 setBeanFactory方法");

    }

    @Override
    public void setBeanName(String s) {
        System.out.println("BeanNameAware 的 setBeanName 的方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean 的 destroy 的方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("第六步：调用 InitializingBean的 afterPropertiesSet方法");
    }
}
