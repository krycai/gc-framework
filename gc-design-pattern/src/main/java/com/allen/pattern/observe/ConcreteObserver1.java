package com.allen.pattern.observe;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ConcreteObserver1
 * @Description 具体观察者
 * @Author allen小哥
 * @Date 2019/4/1 9:41
 **/
@Slf4j
public class ConcreteObserver1 implements Observer {
    @Override
    public void update() {
      log.info("观察者对象1正在执行");
    }
}
