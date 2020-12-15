package com.allen.pattern.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RectangleShape
 * @Description 实现接口的实体类
 * @Author Xu
 * @Date 2019/3/27 13:56
 **/
@Slf4j
public class RectangleShape implements Shape {
    @Override
    public void draw() {
        log.info("画长方形");
    }
}
