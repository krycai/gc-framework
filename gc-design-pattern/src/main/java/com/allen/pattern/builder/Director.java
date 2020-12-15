package com.allen.pattern.builder;

/**
 * @ClassName Director
 * @Description 电脑城老板委派任务给装机人员（Director）
 * @Author Xu
 * @Date 2019/3/20 15:31
 **/
public class Director {

    public void construct(Builder builder){
        builder.buildCpu();
        builder.buildHD();
        builder.buildMainBoard();
        builder.buildView();
    }

}
