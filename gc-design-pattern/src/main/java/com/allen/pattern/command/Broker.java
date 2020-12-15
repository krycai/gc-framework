package com.allen.pattern.command;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: pattern
 * @description: Broker 命令调用类
 * @author: allen小哥
 * @Date: 2019-03-30 21:40
 **/
@Slf4j
public class Broker {

    private List<Order> list = new ArrayList<>();

    public void takeOrder(Order order){
        list.add(order);
    }

    public void placeOrders(){
        log.info("开始执行调度命令");
        for (Order order :list){
            order.execute();
        }
        list.clear();
    }

}
