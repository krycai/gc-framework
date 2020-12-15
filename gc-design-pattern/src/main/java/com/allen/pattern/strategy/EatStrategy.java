package com.allen.pattern.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName EatStrategy
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/21 9:28
 **/
@Slf4j
public class EatStrategy extends Strategy {
    @Override
    void show() {
      log.info("小明正在研究怎么吃饭");
    }
}
