package com.allen.pattern.factory.single;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RectShape
 * @Description 长方形
 * @Author Xu
 * @Date 2019/3/18 19:30
 **/
@Slf4j
public class RectShape implements Shape {
    public RectShape(){
      log.info("长方形构造函数");
    }

    @Override
    public void draw() {
        log.info("开始画长方形");
    }
}
