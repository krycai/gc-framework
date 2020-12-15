package com.allen.pattern.command;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: pattern
 * @description: Order 接口的实体类
 * @author: allen小哥
 * @Date: 2019-03-30 21:36
 **/
@Slf4j
public class BuyOrder implements Order {

    private Stock stock;

    public BuyOrder(Stock stock){
        this.stock =stock;
    }

    @Override
    public void execute() {
        log.info("BuyStock 开始执行");
        stock.buy();
    }
}
