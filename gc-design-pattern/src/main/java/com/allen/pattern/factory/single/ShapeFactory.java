package com.allen.pattern.factory.single;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ShapeFactory
 * @Description 图形工厂
 * @Author Xu
 * @Date 2019/3/18 19:34
 **/
@Slf4j
public class ShapeFactory {

    public static Shape getShape(String type){
        Shape shape = null;
        if (type.equals("circle")){
            shape = new CircleShape();
        }else if (type.equals("rect")){
            shape = new RectShape();
        }else if (type.equals("triangle")){
            shape = new TriangleShape();
        }else {
            log.info("不存在这种图形");
        }
        return shape;
    }
}
