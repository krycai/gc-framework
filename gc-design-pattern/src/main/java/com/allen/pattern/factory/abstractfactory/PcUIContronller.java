package com.allen.pattern.factory.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName PcUIContronller
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/19 18:58
 **/
@Slf4j
public class PcUIContronller implements UIContronller {
    @Override
    public void display() {
      log.info("pc UI开始执行");
    }
}
