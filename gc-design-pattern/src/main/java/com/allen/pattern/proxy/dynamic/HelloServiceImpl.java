package com.allen.pattern.proxy.dynamic;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xuguocai 2020/2/18 18:12
 *
 * 实现类
 *
 */
@Slf4j
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
      log.info("动态代理执行的方法:{}",name);
    }
}
