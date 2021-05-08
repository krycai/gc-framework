package com.allen.pattern.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Circle
 * @Description TODO  扩充抽象类。
 * @Author Xu
 * @Date 2019/3/26 16:59
 **/
@Slf4j
public class RefinedAbstractionCircle extends AbstractShape {

    private int y;

    protected RefinedAbstractionCircle(int y, ImplementorDrawAPI implementorDrawAPI) {
        super(implementorDrawAPI);
        this.y = y;
    }

    @Override
    public void draw() {
     log.info("原型图====");
     implementorDrawAPI.drawCircle("type",y);
    }
}
