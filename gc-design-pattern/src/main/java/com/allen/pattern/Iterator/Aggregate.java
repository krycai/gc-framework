package com.allen.pattern.Iterator;

/**
 * @ClassName Aggregate
 * @Description 抽象迭代器：
 * 定义遍历元素所需要的方法，一般来说会有这么三个方法：取得第一个元素的方法first()，取得下一个元素的方法next()，
 * 判断是否遍历结束的方法isDone()（或者叫hasNext()），移出当前对象的方法remove(),
 *
 * @Author allen小哥
 * @Date 2019/4/2 9:48
 **/
public interface Aggregate {

    public void add(Object obj);

    public void remove(Object obj);

    public Iterator iterator();


}
