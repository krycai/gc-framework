package com.allen.pattern.bridge;

/**
 * @ClassName DrawAPI
 * @Description 桥接模式
 * 是用于把抽象化与实现化解耦，使得二者可以独立变化。这种类型的设计模式属于结构型模式，它通过提供抽象化和实现化之间的桥接结构，来实现二者的解耦。
 * 使得实体类的功能独立于接口实现类
 * 优点：
 *  1、抽象和实现的分离。 2、优秀的扩展能力。 3、实现细节对客户透明。
 *  缺点：
 *  桥接模式的引入会增加系统的理解与设计难度，由于聚合关联关系建立在抽象层，要求开发者针对抽象进行设计与编程。
 * @Author Xu
 * @Date 2019/3/26 16:53
 **/
public interface DrawAPI {
    public void drawCircle(String type,int y);
}
