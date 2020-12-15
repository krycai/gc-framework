package com.allen.pattern.factory.single;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName TriangleShape
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/18 19:32
 **/
@Slf4j
public class TriangleShape implements Shape {
    public TriangleShape(){
      log.info("三角形构造函数");
    }


    @Override
    public void draw() {
        log.info("开始画三角形");
    }
}
