package com.allen.pattern.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Facades
 * @Description 外观模式
 * 定义了一个高层、统一的接口，外部与通过这个统一的接口对子系统中的一群接口进行访问。
 * 通过创建一个统一的类，用来包装子系统中一个或多个复杂的类，客户端可以通过调用外观类的方法来调用内部子系统中所有方法
 * @Author Xu
 * @Date 2019/3/20 17:33
 **/
@Slf4j
public class Facades {

    FacadeLight light;
    FacadeTelevision television;

    public Facades(FacadeLight light,FacadeTelevision television){
        this.light = light;
        this.television =television;
    }

    public void on(){
        light.on();
        television.on();
    }

    public void off(){
        light.off();
        television.off();
    }

}
