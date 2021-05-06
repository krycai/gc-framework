package com.allen.pattern.prototype;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @ClassName PrototypeTest
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/22 10:14
 **/
@Slf4j
public class PrototypeTest {

    public static void main(String[] args) throws Exception {
        ConcretePrototype prototype = new ConcretePrototype();
        for (int i=0;i<5;i++){
            ConcretePrototype obj = (ConcretePrototype) prototype.clone();
            obj.show("浅拷贝");
            ConcretePrototype deepCp =(ConcretePrototype) prototype.deepClone();
            deepCp.show("深拷贝");
        }
    }


}
