package com.allen.pattern.template;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName FoodMenu
 * @Description 模板模式
 * 定义一个模板结构，将具体内容延迟到子类去实现。
 * 在不改变模板结构的前提下在子类中重新定义模板中的内容。
 *
 * 提高代码复用性
 * 将相同部分的代码放在抽象的父类中，而将不同的代码放入不同的子类中
 * 实现了反向控制
 * 通过一个父类调用其子类的操作，通过对子类的具体实现扩展不同的行为，实现了反向控制 & 符合“开闭原则”
 *
 * 缺点：
 * 引入了抽象类，每一个不同的实现都需要一个子类来实现，导致类的个数增加，从而增加了系统实现的复杂度。
 *
 * 应用场景
 * 一次性实现一个算法的不变的部分，并将可变的行为留给子类来实现；
 * 各子类中公共的行为应被提取出来并集中到一个公共父类中以避免代码重复；
 * 控制子类的扩展。
 * @Author Xu
 * @Date 2019/3/20 17:56
 **/
@Slf4j
public abstract class FoodMenu {

    // 过程
    final void process(){
        pourOil();
        heatOil();
        pourVetetable();
        addSource();
        fry();
    }

    // 倒油
     void pourOil(){
        log.info("开始倒油");
     }

    // 热油
    void heatOil(){
         log.info("热锅");
    }

    //倒菜
    abstract void pourVetetable();

    //加调味
    abstract void addSource();

    void fry(){
      log.info("开始炒菜啦啦啦啦啦啦");
    }

}
