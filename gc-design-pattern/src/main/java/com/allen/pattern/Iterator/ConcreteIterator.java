package com.allen.pattern.Iterator;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ConcreteIterator
 * @Description 具体容器：就是抽象容器的具体实现类，比如List接口的有序列表实现ArrayList，List接口的链表实现LinkList，Set接口的哈希列表的实现HashSet等。
 * @Author allen小哥
 *
 * 具体迭代器。利用这个具体的迭代器能够对具体的聚合对象进行遍历。每一个聚合对象都应该对应一个具体的迭代器。
 *
 * @Date 2019/4/2 9:44
 **/
@Slf4j
public class ConcreteIterator implements Iterator {

    private List list = new ArrayList();
    private int cursor = 0;

    public ConcreteIterator(List list){
        log.info("初始化数组");
        this.list = list;
    }

    @Override
    public Object next() {
        log.info("执行next操作");
        Object obj = null;
        if (this.hasNext()){
            log.info("执行hasNext操作");
            obj = this.list.get(cursor++);
        }
        return obj;
    }

    @Override
    public boolean hasNext() {
        log.info("调用hasNext方法");
        if (cursor == list.size()){
            log.info("数组已经没有元素");
            return false;
        }
        return true;
    }
}
