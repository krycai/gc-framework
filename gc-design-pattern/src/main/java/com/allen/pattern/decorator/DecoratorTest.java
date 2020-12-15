package com.allen.pattern.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName DecoratorTest
 * @Description 使用RedShapeDecorator 来装饰 Shape 对象
 *
 * 动态地给一个对象添加一些额外的职责。就增加功能来说，装饰器模式相比生成子类更为灵活。
 *
 * 优点：装饰类和被装饰类可以独立发展，不会相互耦合，装饰模式是继承的一个替代模式，装饰模式可以动态扩展一个实现类的功能。
 * 缺点：多层装饰比较复杂。
 * 使用场景： 1、扩展一个类的功能。 2、动态增加功能，动态撤销。
 * 注意事项：可代替继承。
 *
 * @Author Xu
 * @Date 2019/3/27 14:06
 **/
@Slf4j
public class DecoratorTest {

    public static void main(String[] args){
        Shape circle = new CircleShape();

        Shape red = new RedShapeDecorator(new CircleShape());

        Shape rectangle = new RedShapeDecorator(new RectangleShape());

        log.info("circle ===3333---");
        circle.draw();

        log.info("red ==== 1111-----");
        red.draw();

        log.info("rectangle====-0000----");
        rectangle.draw();
    }

}
