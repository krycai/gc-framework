package com.allen.pattern.factory.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName WebUIContronller
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/19 18:54
 **/
@Slf4j
public class WebUIContronller implements UIContronller {
    @Override
    public void display() {
      log.info("ui执行器开始");
    }

}
