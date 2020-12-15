package com.allen.pattern.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Adapter2
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/20 16:35
 **/
@Slf4j
public class Adapter2 implements Target {
    // 组合
    private Adaptee adaptee;

    // 需构造，不然报空异常
    public Adapter2(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        this.adaptee.otherRequest();
        log.info("组合模式执行适配模式，执行成功====");
    }
}
