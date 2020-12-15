package com.allen.pattern.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RedShapeDecorator
 * @Description 扩展了 ShapeDecorator 类的实体装饰类
 * @Author Xu
 * @Date 2019/3/27 14:01
 **/
@Slf4j
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape shape) {
        // 装饰
        super(shape);
    }

    @Override
    public void draw() {
        /**
         * super 调用父类
         * 直接写上属性，即继承父类的属性，拿来自己用
         */
        //super.draw();
        shape.draw();
        setRedShape(shape);
    }

    public void setRedShape(Shape shape){
        log.info("设置红色图形");
    }
}
