package com.allen.pattern.builder;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ConcreteBuilder
 * @Description 装机人员（具体）  具体建造者。实现/继承抽象接口/类，构建和装配各个部件。
 * @Author Xu
 * @Date 2019/3/20 15:33
 **/
@Slf4j
public class ConcreteBuilder extends Builder {

    Computer computer = new Computer();

    @Override
    public void buildCpu() {
        log.info("开始执行组装CPU");
        computer.add("CPU");
    }

    @Override
    public void buildMainBoard() {
        log.info("开始执行组装主板");
        computer.add("主板");
    }

    @Override
    public void buildView() {
        log.info("开始执行组装显示器");
        computer.add("显示器");
    }

    @Override
    public void buildHD() {
        log.info("开始执行组装硬盘");
        computer.add("硬盘");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
