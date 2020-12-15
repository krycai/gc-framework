package com.allen.pattern.prototype;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @ClassName Prototype
 * @Description 原型模式
 * 用原型实例指定创建对象的种类，并通过拷贝这些原型创建新的对象。
 *核心：
 * 1、实现Cloneable接口。在java语言有一个Cloneable接口，它的作用只有一个，就是在运行时通知虚拟机可以安全地在实现了此接口的类上使用clone方法。在java虚拟机中，
 * 只有实现了这个接口的类才可以被拷贝，否则在运行时会抛出CloneNotSupportedException异常。
 * 2、重写Object类中的clone方法。Java中，所有类的父类都是Object类，Object类中有一个clone方法，作用是返回对象的一个拷贝，但是其作用域protected类型的，
 * 一般的类无法调用，因此，Prototype类需要将clone方法的作用域修改为public类型。
 *
 * 好处：
 *  1、创建对象比直接new一个对象在性能上要好的多，因为Object类的clone方法是一个本地方法，它直接操作内存中的二进制流，特别是复制大对象时，性能的差别非常明显。
 *  2、简化对象的创建，使得创建对象就像我们在编辑文档时的复制粘贴一样简单
 * @Author Xu
 * @Date 2019/3/22 10:06
 **/
@Slf4j
public class Prototype implements Cloneable{

    /**
     * ArrayList不是基本类型，所以成员变量list，不会被拷贝，需要我们自己实现深拷贝
     */
    private ArrayList list = new ArrayList();

    /**
     * 复制（浅复制）
     * 将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
     * @return
     */
    @Override
    public Prototype clone(){
        Prototype prototype = null;
        try {
            prototype = (Prototype)super.clone();
            log.info("浅复制成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return prototype;
    }


    /**
     * 深复制
     * 将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。
     * @return
     */
    public Prototype deepClone(){
        Prototype deepCp = null;
        try {
            deepCp =(Prototype) super.clone();
            deepCp.list = (ArrayList) this.list.clone();
            log.info("执行深拷贝");
        }catch (Exception e){
            e.printStackTrace();
        }
        return deepCp;
    }

}
