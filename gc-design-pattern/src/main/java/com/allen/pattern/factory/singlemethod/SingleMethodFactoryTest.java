package com.allen.pattern.factory.singlemethod;

/**
 * @ClassName SingleMethodFactoryTest
 * @Description 测试类
 * 定义一个用于创建对象的接口，让子类决定将哪一个类实例化。工厂方法模式让一个类的实例化延迟到其子类。
 * @Author Xu
 * @Date 2019/3/19 17:11
 **/
public class SingleMethodFactoryTest {

    public static void main(String[] args){
        // 读取jpg
        ReaderFactory factory = new JapReaderFactory();
        Reader reader = factory.getReader();
        reader.read();

        // 读取git
        ReaderFactory factory1 = new GitReaderFactory();
        Reader git = factory1.getReader();
        git.read();
    }
}
