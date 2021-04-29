package com.allen.pattern.factory.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName PcOperationContronller
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/19 18:57
 *
 * PC端的  操作系统 --》具体实现类  具体产品
 **/
@Slf4j
public class PcOperationContronller implements OperationContronller {
    @Override
    public void contron() {
      log.info("pc操作开始执行");
    }
}
