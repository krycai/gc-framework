package com.allen.pattern.strategy;

/**
 * @ClassName StrategyTest
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/21 9:36
 **/
public class StrategyTest {

    public static void main(String[] args){
        // 环境配置
        StrategyContext context = new StrategyContext();
        // 操作 eat 的策略
        context.change("eat");
        context.show();

        // 操作 sleep 策略
        context.change("sleep");
        context.show();

        // 操作 work 策略
        context.change("work");
        context.show();
    }

}
