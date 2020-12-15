package com.allen.pattern.flyweight;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ConcreteCompositeFlyweight
 * @Description 复合享元角色
 * 复合享元角色所代表的对象是不可以共享的，但是一个复合享元对象可以分解成为多个本身是单纯享元对象的组合。复合享元角色又称作不可共享的享元对象
 * @Author Xu
 * @Date 2019/3/27 15:35
 **/
@Slf4j
public class ConcreteCompositeFlyweight implements Flyweight {

    private Map<Character,Flyweight> hash = new HashMap<>();

    /**
     * 增加一个新的单纯享元对象到聚集中
     */
    public void add(Character key,Flyweight value){
        this.hash.put(key,value);
    }

    /**
     * 外蕴状态作为参数传入到方法中
     */
    @Override
    public void operation(String state) {
        log.info("执行享元模式");
        Flyweight fly = null;
        for (Object key:hash.keySet()){
            fly = hash.get(key);
            fly.operation(state);
        }
    }
}
