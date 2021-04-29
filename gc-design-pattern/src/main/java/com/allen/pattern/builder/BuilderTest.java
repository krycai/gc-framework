package com.allen.pattern.builder;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName BuilderTest
 * @Description 建造者模式: 客户端调用-小成到电脑城找老板买电脑
 * 电脑城老板（Diretor）和小成（Client）进行需求沟通（买来打游戏？学习？看片？）
 * 了解需求后，电脑城老板将小成需要的主机划分为各个部件（Builder）的建造请求（CPU、主板blabla）
 * 指挥装机人员（ConcreteBuilder）去构建组件；
 * 将组件组装起来成小成需要的电脑（Product）
 *
 * 定义:将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
 *
 *
 * @Author Xu
 * @Date 2019/3/20 15:37
 **/
@Slf4j
public class BuilderTest {

    public static void main(String[] args){
        // 定义组装类
        Builder builder = new ConcreteBuilder();
        // 定义指挥
        Director director = new Director();
        // 指挥 指导 组装
        director.construct(builder);
        // 查看是否组装成功
        Computer computer = builder.getComputer();
        computer.show();
    }

}
