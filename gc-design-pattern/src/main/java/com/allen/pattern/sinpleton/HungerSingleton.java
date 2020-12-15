package com.allen.pattern.sinpleton;

/**
 * @ClassName HungerSingleton 它的定义就是确保某一个类只有一个实例，并且提供一个全局访问点。
 * @Description 饿汉模式
 * 这种方式在类加载时就完成了初始化，所以类加载较慢，但获取对象的速度快。 这种方式基于类加载机制避免了多线程的同步问题
 * ，但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化instance显然没有达到懒加载的效果
 * @Author Xu
 * @Date 2019/3/18 11:00
 **/
public class HungerSingleton {
    private static HungerSingleton instance = new HungerSingleton();

    private HungerSingleton(){}

    public static HungerSingleton getInstance(){
        return instance;
    }

}
