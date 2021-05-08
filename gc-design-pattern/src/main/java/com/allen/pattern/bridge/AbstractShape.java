package com.allen.pattern.bridge;

/**
 * @ClassName Shape
 * @Description   抽象类。 ---》作为桥接 （抽象类 与 接口 ）
 * @Author Xu
 * @Date 2019/3/26 16:57
 **/
public abstract class AbstractShape {

    protected ImplementorDrawAPI implementorDrawAPI;

    protected AbstractShape(ImplementorDrawAPI implementorDrawAPI){
        this.implementorDrawAPI = implementorDrawAPI;
    }

    public abstract void draw();

}
