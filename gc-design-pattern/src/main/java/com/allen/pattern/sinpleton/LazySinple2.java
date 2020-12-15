package com.allen.pattern.sinpleton;

/**
 * @ClassName LazySinple2 (线程安全) 它的定义就是确保某一个类只有一个实例，并且提供一个全局访问点。
 * @Description 在多线程中很好的工作，但是每次调用getInstance方法时都需要进行同步，造成不必要的同步开销，而且大部分时候我们是用不到同步的，所以不建议用这种模式
 * @Author Xu
 * @Date 2019/3/18 11:44
 **/
public class LazySinple2 {
    private static LazySinple2  instance = null;

    private LazySinple2(){}

    public static synchronized LazySinple2 getInstace(){
        if (instance == null){
            instance = new LazySinple2();
        }
        return instance;
    }
}
