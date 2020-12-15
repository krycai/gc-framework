package com.allen.pattern.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Circle
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/26 16:59
 **/
@Slf4j
public class Circle extends Shape {

    private int y;

    protected Circle(int y,DrawAPI drawAPI) {
        super(drawAPI);
        this.y = y;
    }

    @Override
    public void draw() {
     log.info("原型图====");
     drawAPI.drawCircle("type",y);
    }
}
