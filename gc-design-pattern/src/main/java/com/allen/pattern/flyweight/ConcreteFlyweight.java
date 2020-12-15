package com.allen.pattern.flyweight;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ConcreteFlyweight
 * @Description 具体享元(ConcreteFlyweight)角色
 * 实现抽象享元角色所规定出的接口。如果有内蕴状态的话，必须负责为内蕴状态提供存储空间
 * @Author Xu
 * @Date 2019/3/27 15:16
 **/
@Slf4j
public class ConcreteFlyweight implements Flyweight {

    private Character character = null;

    /**
     * 构造函数，内蕴状态作为参数传入
     * @param character
     */
    public ConcreteFlyweight(Character character){
        this.character =character;
    }

    /**
     * 外蕴状态作为参数传入方法中，改变方法的行为，
     * 但是并不改变对象的内蕴状态。
     */
    @Override
    public void operation(String state) {
      log.info("具体的享元模式:"+state);
    }
}
