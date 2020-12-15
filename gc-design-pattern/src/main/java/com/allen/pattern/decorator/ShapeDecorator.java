package com.allen.pattern.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ShapeDecorator
 * @Description 实现了 Shape 接口的抽象装饰类
 * @Author Xu
 * @Date 2019/3/27 13:58
 **/
@Slf4j
public abstract class ShapeDecorator implements Shape {

    protected Shape shape;

    public ShapeDecorator(Shape shape){
        this.shape = shape;
    }

    @Override
    public void draw() {
        log.info("图形装饰类执行");
        this.shape.draw();
    }
}
