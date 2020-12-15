package com.allen.pattern.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName SleepStrategy
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/21 9:29
 **/
@Slf4j
public class SleepStrategy extends Strategy {
    @Override
    void show() {
      log.info("小花正在实现上床睡觉策略");
    }
}
