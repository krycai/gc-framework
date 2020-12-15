package com.allen.pattern.chain;

/**
 * @ClassName ManagerDemand
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/28 9:36
 **/
public class ManagerDemand implements Demand {
    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public String getDetail() {
        return "加一张露一点点的图";
    }
}
