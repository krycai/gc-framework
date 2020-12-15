package com.allen.pattern.mediator;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName AbstractMediator
 * @Description 抽象中介类
 * @Author allen小哥
 * @Date 2019/4/1 10:33
 **/
@Slf4j
public abstract class AbstractMediator {

    protected AbstractColleague colleagueA;
    protected AbstractColleague colleagueB;

    // 中介类初始化其他对象
    public AbstractMediator(AbstractColleague A,AbstractColleague B){
        colleagueA = A;
        colleagueB = B;
    }

    // A 影响 B
    public abstract void AaffectB();

    // B 影响 A
    public abstract void BaffectA();

}
