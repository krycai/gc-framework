package com.allen.pattern.sinpleton;

/**
 * @ClassName StaticSingleton 它的定义就是确保某一个类只有一个实例，并且提供一个全局访问点。
 * @Description 静态内部类单例模式
 * 第一次加载StaticSingleton类时并不会初始化StaticSingleton，只有第一次调用getInstance方法时虚拟机加载SingletonHelp 并初始化StaticSingleton ，
 * 这样不仅能确保线程安全也能保证StaticSingleton类的唯一性，所以推荐使用静态内部类单例模式。
 * @Author Xu
 * @Date 2019/3/18 11:13
 **/
public class StaticSingleton {

    private StaticSingleton(){}

    public static StaticSingleton getInstance(){
        return SingletonHelp.instance;
    }

    private static class SingletonHelp{
        private static StaticSingleton instance = new StaticSingleton();
    }
}
