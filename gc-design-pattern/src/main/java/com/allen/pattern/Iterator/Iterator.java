package com.allen.pattern.Iterator;

/**
 * @ClassName Iterator
 * @Description 抽象容器：一般是一个接口，提供一个iterator()方法，例如java中的Collection接口，List接口，Set接口等
 *
 * 抽象迭代器：所有迭代器都需要实现的接口，提供了游走聚合对象元素之间的方法。
 *
 * @Author allen小哥
 * @Date 2019/4/2 9:42
 **/
public interface Iterator {

    public Object next();
    public boolean hasNext();

}
