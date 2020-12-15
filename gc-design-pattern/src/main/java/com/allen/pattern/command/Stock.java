package com.allen.pattern.command;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: pattern
 * @description: 请求类 来充当Receiver。
 * @author: allen小哥
 * @Date: 2019-03-30 21:30
 **/
@Slf4j
public class Stock {

    private String name = "ABC";
    private int quantity = 10;

    public void buy(){
        log.info("buy ==-- name="+name+",quantity="+quantity);
    }

    public void sell(){
        log.info("sell == name="+name+",quantity="+quantity);
    }

}
