package com.allen.pattern.factory.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName PcSystemFactory
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/19 19:04
 **/
@Slf4j
public class PcSystemFactory implements SystemFactory {
    @Override
    public OperationContronller createOperationContronller() {
        log.info("pc 开始执行操作");
        return new PcOperationContronller();
    }

    @Override
    public UIContronller createUIContronller() {
        log.info("pc 开始执行UI");
        return new PcUIContronller();
    }
}
