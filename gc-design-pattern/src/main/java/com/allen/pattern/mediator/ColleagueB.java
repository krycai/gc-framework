package com.allen.pattern.mediator;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ColleagueB
 * @Description 同事类B
 * @Author allen小哥
 * @Date 2019/4/1 10:39
 **/
@Slf4j
public class ColleagueB extends AbstractColleague {
    @Override
    public void setNumber(int number, AbstractMediator B) {
        this.number = number;
        B.BaffectA();
        log.info("调用B影响A方法");
    }
}
