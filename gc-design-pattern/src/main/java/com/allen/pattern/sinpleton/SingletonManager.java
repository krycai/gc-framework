package com.allen.pattern.sinpleton;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SingletonManager 它的定义就是确保某一个类只有一个实例，并且提供一个全局访问点。
 * @Description 使用容器实现单例模式
 * 将多种的单例类统一管理，在使用时根据key获取对象对应类型的对象。这种方式使得我们可以管理多种类型的单例，并且在使用时可以通过统一的接口进行获取操作，
 * 降低了用户的使用成本，也对用户隐藏了具体实现，降低了耦合度。
 * @Author Xu
 * @Date 2019/3/18 11:32
 **/
public class SingletonManager {

    private static Map<String,Object> objMap = new HashMap<>();

    private SingletonManager(){}

    public static void registObject(String key, Object instance){
        if (!objMap.containsKey(key)){
            objMap.put(key,instance);
        }
    }

    public static Object getObject(String key){
        return objMap.get(key);
    }

}
