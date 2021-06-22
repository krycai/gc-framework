package com.allen.pattern.Iterator;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ConcreteAggregate
 * @Description 迭代器实现：实现迭代器接口中定义的方法，完成集合的迭代。  具体聚合类。实现方法，返回该聚合对象的迭代器。
 * @Author allen小哥
 * @Date 2019/4/2 9:49
 **/
@Slf4j
public class ConcreteAggregate implements Aggregate {

    private List list = new ArrayList();

    @Override
    public void add(Object obj) {
        log.info("增加的元素有="+obj);
        list.add(obj);
    }

    @Override
    public void remove(Object obj) {
        log.info("去掉的元素有="+obj);
        list.remove(obj);
    }

    @Override
    public Iterator iterator() {
        // 与迭代器相关联
        return new ConcreteIterator(list);
    }
}
