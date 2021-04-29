package com.allen.pattern.factory.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName WebOperationContronller
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/19 18:55
 *
 * WEB端的  操作系统 --》具体实现类  具体工厂
 **/
@Slf4j
public class WebOperationContronller implements OperationContronller {
    @Override
    public void contron() {
      log.info("WEB端的  操作系统 操作执行开始");
    }

}
