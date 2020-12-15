package com.allen.pattern.factory.singlemethod;

/**
 * @ClassName ReaderFactory
 * @Description 简单工厂只有一个统一的工厂类，而工厂方法是针对每个要创建的对象都会提供一个工厂类，这些工厂类都实现了一个工厂基类（本例中的ReaderFactory ）
 * （1）客户端不需要知道它所创建的对象的类。例子中我们不知道每个图片加载器具体叫什么名，只知道创建它的工厂名就完成了床架过程。
 * （2）客户端可以通过子类来指定创建对应的对象。以上场景使用于采用工厂方法模式。
 *
 * @Author Xu
 * @Date 2019/3/19 17:07
 **/
public interface ReaderFactory {
    Reader getReader();
}
