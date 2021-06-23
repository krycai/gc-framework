package com.allen.pattern.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName WorkStrategy
 * @Description 具体策略类。封装了具体的算法实现。
 * @Author Xu
 * @Date 2019/3/21 9:30
 **/
@Slf4j
public class StrategyWork extends Strategy {

    @Override
    void show() {
      log.info("上班策略---》小笑正在执行上班策略");
    }

}
