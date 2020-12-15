package com.allen.pattern.factory.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName PcOperationContronller
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/19 18:57
 **/
@Slf4j
public class PcOperationContronller implements OperationContronller {
    @Override
    public void contron() {
      log.info("pc操作开始执行");
    }
}
