package com.allen.pattern.builder;

/**
 * @ClassName Director
 * @Description 电脑城老板委派任务给装机人员（Director）
 *
 * 指挥者。构建一个使用Builder接口的对象。它主要是用于创建一个复杂的对象，它主要有两个作用，一是：隔离了客户与对象的生产过程，二是：负责控制产品对象的生产过程。
 *
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
