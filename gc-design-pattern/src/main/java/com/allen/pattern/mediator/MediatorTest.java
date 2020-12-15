package com.allen.pattern.mediator;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName MediatorTest
 * @Description TODO
 * @Author allen小哥
 * @Date 2019/4/1 10:48
 **/
@Slf4j
public class MediatorTest {

    public static void main(String[] args){
        AbstractColleague colleagueA = new ColleagueA();
        AbstractColleague colleagueB = new ColleagueB();
        // 中介类与同事类的关系
        AbstractMediator am = new Mediator(colleagueA,colleagueB);

        log.info("=======colleagueA======");
        colleagueA.setNumber(100,am);
        colleagueA.getNumber();

        log.info("-------colleagueB--------");
        colleagueB.setNumber(200,am);
        colleagueB.getNumber();
    }


}
