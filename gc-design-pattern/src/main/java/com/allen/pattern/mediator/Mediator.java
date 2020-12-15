package com.allen.pattern.mediator;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Mediator
 * @Description 具体中介类
 * @Author allen小哥
 * @Date 2019/4/1 10:43
 **/
@Slf4j
public class Mediator extends AbstractMediator {
    public Mediator(AbstractColleague A, AbstractColleague B) {
        super(A, B);
    }

    /**
     * 中介处理关联关系
     */
    @Override
    public void AaffectB() {
        /**
         * 获取A的值赋予B，即A影响B
         */
        log.info("获取A的值赋予B，即A影响B");
        int number = colleagueA.getNumber();
        colleagueB.setNumber(number * 10);
    }

    /**
     * 中介处理关联关系
     */
    @Override
    public void BaffectA() {
        /**
         * 获取B的值赋予A，即B影响A
         */
        log.info("获取B的值赋予A，即B影响A");
        int number = colleagueB.getNumber();
        colleagueA.setNumber(number * 200);
    }
}
