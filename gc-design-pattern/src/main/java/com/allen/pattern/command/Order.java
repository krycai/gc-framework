package com.allen.pattern.command;

/**
 * @program: pattern
 * @description: 命令接口  抽象命令类。用来声明执行操作的接口。
 *
 * 是一种数据驱动的设计模式，它属于行为型模式。请求以命令的形式包裹在对象中，并传给调用对象。
 * 调用对象寻找可以处理该命令的合适的对象，并把该命令传给相应的对象，该对象执行命令。
 *
 * 优点： 1、降低了系统耦合度。 2、新的命令可以很容易添加到系统中去。
 *
 * 缺点：使用命令模式可能会导致某些系统有过多的具体命令类。
 *
 * 对命令请求者（Broker）和命令实现者（Stock）的解耦，方便对命令进行各种控制。
 *
 *
 * @author: allen小哥
 * @Date: 2019-03-30 18:06
 **/
public interface Order {

    void execute();

}
