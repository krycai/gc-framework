package com.allen.pattern.sinpleton;

/**
 * @ClassName DoubleSingleton 它的定义就是确保某一个类只有一个实例，并且提供一个全局访问点。
 * @Description 双重检查模式 （DCL）DCL优点是资源利用率高，第一次执行getInstance时单例对象才被实例化，效率高。缺点是第一次加载时反应稍慢一些，在高并发环境下也有一定的缺陷，
 * 虽然发生的概率很小。DCL虽然在一定程度解决了资源的消耗和多余的同步，线程安全等问题，但是他还是在某些情况会出现失效的问题，
 * 也就是DCL失效，在《java并发编程实践》一书建议用静态内部类单例模式来替代DCL
 * @Author Xu
 * @Date 2019/3/18 11:04
 **/
public class DoubleSingleton {
     private volatile static DoubleSingleton instance = null;

    private DoubleSingleton(){ }

     public static DoubleSingleton getInstance(){
         if (instance == null){
             synchronized (DoubleSingleton.class){
                 if (instance == null){
                     instance = new DoubleSingleton();
                 }
             }
         }
         return instance;
     }
}
