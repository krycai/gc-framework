package com.allen.pattern.chain.example;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName BossHandler
 * @Description TODO
 *
 * 具体处理者角色：实现或者继承抽象这角色，具体逻辑根据实际的架构来定，后面会提到
 *
 * @Author Xu
 * @Date 2019/3/28 9:49
 **/
@Slf4j
public class HandlerBoss extends Handler {
    public HandlerBoss(int level) {
        super(0);
    }

    @Override
    public void report(Demand demand) {
        log.info("boss 需求:"+demand.getDetail());
        log.info(getClass().getSimpleName()+":你们先陈述各自的意见及事态的优缺点，不行再干一架");
    }
}
