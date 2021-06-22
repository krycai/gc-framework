package com.allen.pattern.observe;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ConcreteSubject
 * @Description 具体被观察者
 * @Author allen小哥
 * @Date 2019/4/1 9:40
 **/
@Slf4j
public class ConcreteSubject extends Subject {
    @Override
    public void doSomething() {
      log.info("目标主体发生改变---》被观察者事件发生");
      // 发现有观察的对象，执行
      this.notifyObserver();
    }
}
