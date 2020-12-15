package com.allen.pattern.strategy;

/**
 * @ClassName StrategyTest
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/21 9:36
 **/
public class StrategyTest {

    public static void main(String[] args){
        MethodStrategy methodStrategy = new MethodStrategy();
        methodStrategy.change("eat");
        methodStrategy.show();

       methodStrategy.change("sleep");
        methodStrategy.show();

        methodStrategy.change("work");
        methodStrategy.show();
    }

}
