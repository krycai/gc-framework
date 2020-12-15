package com.allen.pattern.sinpleton;

/**
 * 它的定义就是确保某一个类只有一个实例，并且提供一个全局访问点。
 * 懒汉模式（线程不安全）,随类加载，加载比较慢
 * 懒汉模式申明了一个静态对象，在用户第一次调用时初始化，虽然节约了资源，但第一次加载时需要实例化，反映稍慢一些，而且在多线程不能正常工作
 */
public class LazySinple {

    private static LazySinple  instance = null;

    private LazySinple(){}

    public static LazySinple getInstace(){
        if (instance == null){
            instance = new LazySinple();
        }
        return instance;
    }
}
