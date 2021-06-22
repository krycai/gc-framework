package com.allen.pattern.interpreter;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: pattern
 * @description: TerminalExpression 接口的实体类  终结符表达式。实现与文法中的终结符相关的解释操作。实现抽象表达式中所要求的方法。文法中每一个终结符都有一个具体的终结表达式与之相对应。
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
