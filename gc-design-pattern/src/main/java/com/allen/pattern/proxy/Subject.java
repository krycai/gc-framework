package com.allen.pattern.proxy;

/**
 * @ClassName Subject
 * @Description 代理模式
 * 给目标对象提供一个代理对象，并由代理对象控制对目标对象的引用
 * 通过引入代理对象的方式来间接访问目标对象
 * 防止直接访问目标对象给系统带来的不必要复杂性。
 * @Author Xu
 * @Date 2019/3/20 17:06
 **/
public interface Subject {

    public void buyApple();

}
