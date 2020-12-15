package com.allen.pattern.factory.single;

/**
 * @ClassName SingleFactoryTest
 * @Description　定义一个工厂类，根据传入的参数不同返回不同的实例，被创建的实例具有共同的父类或接口。
 * 适用场景：
　　（1）需要创建的对象较少。
　　（2）客户端不关心对象的创建过程。
 * @Author Xu
 * @Date 2019/3/18 19:40
 **/
public class SingleFactoryTest {

    public static void main(String[] args){
        Shape shape = ShapeFactory.getShape("circle");
        shape.draw();
        Shape shape2 = ShapeFactory.getShape("rect");
        shape2.draw();
        Shape shape3 = ShapeFactory.getShape("triangle");
        shape3.draw();
    }

}
