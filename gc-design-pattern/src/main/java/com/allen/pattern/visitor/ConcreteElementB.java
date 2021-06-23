package com.allen.pattern.visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ConcreteElementB
 * @Description 元素类  具体元素 。实现Accept操作。
 *
 * 实现抽象元素类所声明的accept方法，通常都是visitor.visit(this)，基本上已经形成一种定式了
 *
 * @Author allen小哥
 * @Date 2019/4/1 14:59
 **/
@Slf4j
public class ConcreteElementB extends Element {
    @Override
    public void accept(IVisitor visitor) {
      log.info("元素B正在接受访问");
      visitor.visit(this);
    }

    @Override
    public void doSomething() {
        log.info("元素B正在执行一些操作");
    }
}
