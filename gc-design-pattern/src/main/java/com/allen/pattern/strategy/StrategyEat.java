package com.allen.pattern.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName EatStrategy
 * @Description 具体策略类。封装了具体的算法实现。
 * @Author Xu
 * @Date 2019/3/21 9:28
 **/
@Slf4j
public class StrategyEat extends Strategy {

    @Override
    void show() {
      log.info("吃饭策略---》小明正在研究怎么吃饭策略");
    }

}
