package com.allen.pattern.mediator;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ColleagueA
 * @Description 同事类A
 * @Author allen小哥
 * @Date 2019/4/1 10:32
 **/
@Slf4j
public class ColleagueA extends AbstractColleague {
    @Override
    public void setNumber(int number, AbstractMediator A) {
        this.number = number;
        A.AaffectB();
        log.info("调用A影响B方法");
    }
}
