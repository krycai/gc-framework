package com.allen.pattern.factory.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName WebUIContronller
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/19 18:54
 *
 * WEB端的界面  --》具体实现类  具体产品
 **/
@Slf4j
public class WebUIContronller implements UIContronller {
    @Override
    public void display() {
      log.info("WebUI 执行器开始");
    }

}
