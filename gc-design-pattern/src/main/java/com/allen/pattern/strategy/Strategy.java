package com.allen.pattern.strategy;

/**
 * @ClassName Strategy
 * @Description TODO   抽象策略类。定义所有支持算法的公共接口。Context使用这个接口来调用某个Concretestrategy定义的算法。
 * @Author Xu
 * @Date 2019/3/21 9:26
 **/
public abstract class Strategy {

    abstract void show();

}
