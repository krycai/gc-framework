package com.allen.pattern.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName SleepStrategy
 * @Description 具体策略类。封装了具体的算法实现。
 * @Author Xu
 * @Date 2019/3/21 9:29
 **/
@Slf4j
public class StrategySleep extends Strategy {
    @Override
    void show() {
      log.info("睡觉策略---》小花正在实现上床睡觉策略");
    }
}
