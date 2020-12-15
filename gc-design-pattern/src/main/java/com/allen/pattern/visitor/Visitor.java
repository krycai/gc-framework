package com.allen.pattern.visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Visitor
 * @Description 访问者 它需要给出对每一个元素类访问时所产生的具体行为
 *
 * 实现抽象访问者所声明的方法，它影响到访问者访问到一个类后该干什么，要做什么事情。
 *
 * @Author allen小哥
 * @Date 2019/4/1 15:03
 **/
@Slf4j
public class Visitor implements IVisitor {
    @Override
    public void visit(ConcreteElementA A) {
        A.doSomething();
        log.info("访问A元素");
    }

    @Override
    public void visit(ConcreteElementB B) {
        B.doSomething();
        log.info("访问B元素");
    }
}
