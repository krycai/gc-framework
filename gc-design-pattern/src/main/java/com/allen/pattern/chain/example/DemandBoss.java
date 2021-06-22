package com.allen.pattern.chain.example;

/**
 * @ClassName BossDemand
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/28 9:36
 **/
public class DemandBoss implements Demand {
    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public String getDetail() {
        return "加一张露一点点的图";
    }
}
