package com.allen.pattern.factory.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName AndroidSystemFactory
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/19 19:02
 *
 * 具体工厂。具体工厂是用于生产不同产品族。要创建一个产品，客户只需要使用其中一个工厂完全不需要实例化任何产品对象。  具体工厂
 *
 *   创建 Android 相关的产品
 **/
@Slf4j
public class AndroidSystemFactory implements SystemFactory {
    @Override
    public OperationContronller createOperationContronller() {
        log.info("Android 执行web");
        return new WebOperationContronller();
    }

    @Override
    public UIContronller createUIContronller() {
        log.info("android 执行ui");
        return new WebUIContronller();
    }
}
