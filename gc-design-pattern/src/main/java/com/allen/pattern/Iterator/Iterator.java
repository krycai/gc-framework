package com.allen.pattern.Iterator;

/**
 * @ClassName Iterator
 * @Description 抽象容器：一般是一个接口，提供一个iterator()方法，例如java中的Collection接口，List接口，Set接口等
 * @Author allen小哥
 * @Date 2019/4/2 9:42
 **/
public interface Iterator {

    public Object next();
    public boolean hasNext();

}
