package com.allen.pattern.flyweight;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FlyweightFactory
 * @Description 享元工厂(FlyweightFactory)角色
 * 本角 色负责创建和管理享元角色。本角色必须保证享元对象可以被系统适当地共享。
 * 当一个客户端对象调用一个享元对象的时候，享元工厂角色会检查系统中是否已经有 一个符合要求的享元对象。
 * 如果已经有了，享元工厂角色就应当提供这个已有的享元对象；如果系统中没有一个适当的享元对象的话，享元工厂角色就应当创建一个 合适的享元对象。
 * @Author Xu
 * @Date 2019/3/27 15:20
 **/
@Slf4j
public class FlyweightFactory {

    private Map<Character,Flyweight> map = new HashMap<>();

    /**
     * 单个享元模式
     * @param state
     * @return
     */
    public Flyweight getFactory(Character state){
        log.info("state====="+state);
        Flyweight flyweight = map.get(state);
        if (flyweight == null){
            //内蕴状态
            flyweight = new ConcreteFlyweight(state);
            map.put(state,flyweight);
        }
        return flyweight;
    }

    /**
     * 复合享元模式
     */
    public Flyweight getFactory(List<Character> characterList){
        ConcreteCompositeFlyweight flyweight = new ConcreteCompositeFlyweight();

        for (Character character : characterList){
            flyweight.add(character,getFactory(character));
        }
        return flyweight;
    }

}
