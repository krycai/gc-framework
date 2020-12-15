package com.allen.pattern.observe;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ConcreteObserver2
 * @Description 具体观察者
 * @Author allen小哥
 * @Date 2019/4/1 9:42
 **/
@Slf4j
public class ConcreteObserver2 implements Observer {
    @Override
    public void update() {
      log.info("观察者对象2正在执行");
    }
}
