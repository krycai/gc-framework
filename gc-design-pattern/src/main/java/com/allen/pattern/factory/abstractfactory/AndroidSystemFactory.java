package com.allen.pattern.factory.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName AndroidSystemFactory
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/19 19:02
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
