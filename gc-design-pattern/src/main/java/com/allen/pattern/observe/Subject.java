package com.allen.pattern.observe;

import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

/**
 * @ClassName Subject
 * @Description 被观察者
 *
 * 优点
 * 观察者与被观察者之间是属于轻度的关联关系，并且是抽象耦合的，这样，对于两者来说都比较容易进行扩展。
 * 观察者模式是一种常用的触发机制，它形成一条触发链，依次对各个观察者的方法进行处理。但同时，这也算是观察者模式一个缺点，
 * 由于是链式触发，当观察者比较多的时候，性能问题是比较令人担忧的。并且，在链式结构中，比较容易出现循环引用的错误，造成系统假死。
 *
 * 缺点：
 * 1、如果一个被观察者对象有很多的直接和间接的观察者的话，将所有的观察者都通知到会花费很多时间。
 * 2、如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃。
 * 3、观察者模式没有相应的机制让观察者知道所观察的目标对象是怎么发生变化的，而仅仅只是知道观察目标发生了变化。
 *
 * 使用场景：
 * 一个抽象模型有两个方面，其中一个方面依赖于另一个方面。将这些方面封装在独立的对象中使它们可以各自独立地改变和复用。
 * 一个对象的改变将导致其他一个或多个对象也发生改变，而不知道具体有多少对象将发生改变，可以降低对象之间的耦合度。
 * 一个对象必须通知其他对象，而并不知道这些对象是谁。
 * 需要在系统中创建一个触发链，A对象的行为将影响B对象，B对象的行为将影响C对象……，可以使用观察者模式创建一种链式触发机制。
 *
 * @Author Xu
 * @Date 2019/4/1 9:32
 **/
@Slf4j
public abstract class Subject {

    private Vector<Observer> vector = new Vector();

    /**
     * 添加到容器
     * @param observer
     */
    public void add(Observer observer){
        this.vector.add(observer);
    }

    public void delete(Observer observer){
        this.vector.remove(observer);
    }

    // 观察事件处理
    protected void notifyObserver(){
        log.info("-------》通知相关的观察者《---------");
        for (Observer obj : vector){
            obj.update();
        }
    }

    public abstract void doSomething();

}
