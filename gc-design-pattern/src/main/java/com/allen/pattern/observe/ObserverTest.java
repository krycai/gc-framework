package com.allen.pattern.observe;

/**
 * @ClassName observerTest
 * @Description TODO
 * @Author allen小哥
 * @Date 2019/4/1 9:43
 **/
public class ObserverTest {

    public static void main(String[] args){
        // 主体状态
        Subject subject = new ConcreteSubject();
        // 给主体状态添加 观察者
        subject.add(new ConcreteObserver1());
        subject.add(new ConcreteObserver2());
        // 主体状态发现变化
        subject.doSomething();
    }

}
