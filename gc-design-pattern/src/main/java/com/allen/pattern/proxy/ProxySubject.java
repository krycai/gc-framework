package com.allen.pattern.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ProxySubject
 * @Description 代理
 * 代理对象：起到中介作用，连接客户端和目标对象
 * @Author Xu
 * @Date 2019/3/20 17:08
 **/
@Slf4j
public class ProxySubject implements Subject {
    @Override
    public void buyApple() {
      log.info("他人顺路帮realSubject去商城");
      Subject real = new RealSubject();
      real.buyApple();
      log.info("代理买苹果成功");
    }
}
