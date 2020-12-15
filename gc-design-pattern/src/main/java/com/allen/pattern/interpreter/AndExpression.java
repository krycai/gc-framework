package com.allen.pattern.interpreter;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: pattern
 * @description: AndExpression 接口的实体类
 * @author: allen小哥
 * @Date: 2019-03-31 18:01
 **/
@Slf4j
public class AndExpression implements Expression {

    private Expression expr1 = null;
    private Expression expr2 = null;

    public AndExpression(Expression expr1,Expression expr2){
        this.expr1 =expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        log.info("执行 and 操作");
        return expr1.interpret(context) && expr2.interpret(context);
    }
}
