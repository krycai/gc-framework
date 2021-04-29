package com.allen.pattern.prototype;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ConcretePrototype
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/22 10:12
 *
 * 具体原型类。实现克隆的具体操作。实现方法放到 Prototype 类处理，也可以放到这个类
 **/
@Slf4j
public class ConcretePrototype extends Prototype {

    public void show(String type){
      log.info("调用展示方法===="+type);
    }

}
