package com.allen.pattern.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RealSubject
 * @Description 目标对象
 * @Author Xu
 * @Date 2019/3/20 17:07
 **/
@Slf4j
public class RealSubject implements Subject {

    @Override
    public void buyApple() {
      log.info("主人要买苹果了");
    }

}
