package com.allen.pattern.interpreter;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: pattern
 * @description: TerminalExpression 接口的实体类
 * @author: allen小哥
 * @Date: 2019-03-31 17:59
 **/
@Slf4j
public class TerminalExpression implements Expression {

    private String date;

    public TerminalExpression(String date){
        this.date = date;
    }

    @Override
    public boolean interpret(String context) {
        // 解析判断
        if (date.contains(context)){
            log.info("开始判断："+context);
            return true;
        }
        return false;
    }
}
