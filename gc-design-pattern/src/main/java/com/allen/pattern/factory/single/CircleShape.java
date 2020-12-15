package com.allen.pattern.factory.single;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName CircleShape
 * @Description 圆形
 * @Author Xu
 * @Date 2019/3/18 19:24
 **/
@Slf4j
public class CircleShape implements Shape{

    public CircleShape(){
        System.out.println("圆形构造函数");
    }
    public void draw(){
        log.info("开始画圆形");
    }

}
