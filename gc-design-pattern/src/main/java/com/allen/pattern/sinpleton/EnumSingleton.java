package com.allen.pattern.sinpleton;

/**
 * @ClassName StaticSingleton 它的定义就是确保某一个类只有一个实例，并且提供一个全局访问点。
 * @Description 枚举单例
 * 默认枚举实例的创建是线程安全的，并且在任何情况下都是单例
 * @Author Xu
 * @Date 2019/3/18 11:13
 **/
public enum EnumSingleton {

    INSTANCE;

    public void doSomething(){

    }
}
