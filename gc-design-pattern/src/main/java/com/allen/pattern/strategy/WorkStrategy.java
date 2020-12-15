package com.allen.pattern.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName WorkStrategy
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/21 9:30
 **/
@Slf4j
public class WorkStrategy extends Strategy {
    @Override
    void show() {
      log.info("小笑正在执行上班策略");
    }
}
