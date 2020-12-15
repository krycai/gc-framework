package com.allen.pattern.chain;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Handler
 * @Description
 *
 * 抽象处理者角色：定义了处理请求的接口或者抽象类，提供了处理请求的的方法和设置下一个处理者的方法。
 *
 *
 * 定义：
 * 创建多个对象，使这些对象形成一条链，并沿着这条链传递请求，直到链上的某一个对象决定处理此请求。
 * 特点：
 * 1）接收请求的对象连接成一条链，对象之间存在层级关系。
 * 2）这些对象可处理请求，也可传递请求，直到有对象处理该请求。
 *
 *优点
 * 1）降低耦合度：客户端不需要知道请求由哪个处理者处理，而处理者也不需要知道处理者之间的传递关系，由系统灵活的组织和分配。
 * 2）良好的扩展性：增加处理者的实现很简单，只需重写处理请求业务逻辑的方法。
 * 缺点
 * 1）请求会从链头发出，直到有处理者响应，在责任链比较长的时候会影响系统性能。
 * 2）请求递归，调试排错比较麻烦。
 *
 *
 * @Author Xu
 * @Date 2019/3/28 9:18
 **/
@Slf4j
public abstract class Handler {

    private Handler nextHandler;
    private int level;

    public Handler(int level){
        this.level = level;
    }

    /**
     * 此方法是责任链执行的关键
     * @param demand
     */
    public final void handlerMessage(Demand demand){
        if (level ==demand.getLevel()){
            this.report(demand);
        }else {
            if (this.nextHandler != null){
                log.info("事情无法沟通，需上报给上一级经理独裁");
                // 若还有上一级，则继续上报
                this.nextHandler.handlerMessage(demand);
            }else {
                log.info("boss 在广州塔，已经没有最高的地方了");
            }
        }
    }

    public void setNextMessage(Handler handler){
        this.nextHandler = handler;
    }

    public abstract void report(Demand demand);
}
