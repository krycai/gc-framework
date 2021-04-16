package com.allen.java.base;

/**
 * Created by xuguocai on 2021/2/9 10:46
 *
 * 非静态代码块与构造函数的区别是： 非静态代码块是给所有对象进行统一初始化，而构造函数是给对应的对象初始化，因为构造函数是可以多个的，
 * 运行哪个构造函数就会建立什么样的对象，但无论建立哪个对象，都会先执行相同的构造代码块。也就是说，构造代码块中定义的是不同对象共性的初始化内容。
 *
 */
public class StaticDemo {
    // 构造函数是给对应的对象初始化
    public StaticDemo(){
        System.out.println("构造函数");
    }
    // 针对的是类
    static{
        System.out.println("静态代码块");
    }

    /**
     * static{}静态代码块与{}非静态代码块(构造代码块):
     *
     * 相同点： 都是在JVM加载类时且在构造方法执行之前执行，在类中都可以定义多个，定义多个时按定义的顺序执行，一般在代码块中对一些static变量进行赋值。
     *
     * 不同点： 静态代码块在非静态代码块之前执行(静态代码块 -> 非静态代码块 -> 构造方法)。静态代码块只在第一次new执行一次，之后不再执行，
     * 而非静态代码块在每new一次就执行一次。 非静态代码块可在普通方法中定义(不过作用不大)；而静态代码块不行。
     */
    // 非静态代码块是给所有对象进行统一初始化
    {
        System.out.println("非静态代码块");
    }

    public static void test(){
        System.out.println("静态方法");
    }

    public static void main(String[] args) {
        StaticDemo staticDemo = new StaticDemo();
        StaticDemo.test();
    }
}
