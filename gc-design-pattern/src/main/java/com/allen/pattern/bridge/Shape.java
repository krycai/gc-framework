package com.allen.pattern.bridge;

/**
 * @ClassName Shape
 * @Description
 * @Author Xu
 * @Date 2019/3/26 16:57
 **/
public abstract class Shape {

    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}
