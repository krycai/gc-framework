package com.allen.pattern.observe;

/**
 * @ClassName observerTest
 * @Description TODO
 * @Author allen小哥
 * @Date 2019/4/1 9:43
 **/
public class observerTest {

    public static void main(String[] args){
        Subject subject = new ConcreteSubject();
        subject.add(new ConcreteObserver1());
        subject.add(new ConcreteObserver2());
        subject.doSomething();
    }

}
