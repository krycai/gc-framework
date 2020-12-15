package com.allen.pattern.factory.abstractfactory;

/**
 * @ClassName SystemFactory
 * @Description 适用场景：
（1）和工厂方法一样客户端不需要知道它所创建的对象的类。
（2）需要一组对象共同完成某种功能时。并且可能存在多组对象完成不同功能的情况。
（3）系统结构稳定，不会频繁的增加对象。（因为一旦增加就需要修改原有代码，不符合开闭原则）
 * @Author Xu
 * @Date 2019/3/19
 * 18:59
 **/
public interface SystemFactory {

    public OperationContronller createOperationContronller();

    public UIContronller createUIContronller();

}
