package com.allen.pattern.interpreter;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: pattern
 * @description: InterpretTest
 * @author: allen小哥
 * @Date: 2019-03-31 18:10
 **/
@Slf4j
public class InterpretTest {

    // one two 是男性
    public static Expression getMale(){
        Expression one = new TerminalExpression("one");
        Expression two = new TerminalExpression("two");
        return new OrExpression(one,two);
    }

    // three 是女性
    public static Expression getMarriWomam(){
        Expression three = new TerminalExpression("three");
        Expression marri = new TerminalExpression("marri");
        return new AndExpression(three,marri);
    }

    public static void main(String[] args){
        Expression isMale = getMale();
        Expression isWoman = getMarriWomam();
        log.info("one id man?"+isMale.interpret("one"));
        log.info("three is woman ?"+isWoman.interpret("three"));
    }
}
