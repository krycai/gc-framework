package com.allen.pattern.chain.example;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ChainTest
 * @Description
 *
 * 责任链模式和观察者模式存在一个共同点，就是传递责任链模式是一级一级的传递，形成一条链，链节点（处理者）之间是存在传递关系的。
 * 而观察者模式的被观察者向观察者们的传递，并不是具体观察者之间的传递，观察者之间是不存在关联的。
 *
 *
 *
 *
 * @Author Xu
 * @Date 2019/3/28 10:04
 **/
@Slf4j
public class ChainTest {

    public static void main(String[] args){
        // 级别低
        Demand manageMemand = new DemandManager();
        // 级别高
        Demand bossMemand = new DemandBoss();

        HandlerBoss boss = new HandlerBoss(0);
        HandlerManager manager = new HandlerManager(1);

        // 上传上级
        manager.setNextMessage(boss);
        // 执行本部命令
        manager.handlerMessage(manageMemand);
        log.info("============上级===============");
        manager.handlerMessage(bossMemand);

    }

}
