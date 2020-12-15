package com.allen.pattern.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Adapter
 * @Description 适配者 适配器模式，即定义一个包装类，用于包装不兼容接口的对象
 * 作用:把一个类的接口变换成客户端所期待的另一种接口，从而使原本接口不匹配而无法一起工作的两个类能够在一起工作。
 * @Author Xu
 * @Date 2019/3/20 16:29
 **/
@Slf4j
public class Adapter extends Adaptee implements Target{

    //目标接口要求调用Request()这个方法名，但源类Adaptee没有方法Request()
    //因此适配器补充上这个方法名
    //但实际上Request()只是调用源类Adaptee的SpecificRequest()方法的内容
    //所以适配器只是将SpecificRequest()方法作了一层封装，封装成Target可以调用的Request()而已
    @Override
    public void request() {
        this.otherRequest();
        log.info("适配成功=======");
    }

}
