package com.allen.pattern.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName FacadeTelevision
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/20 17:31
 **/
@Slf4j
public class FacadeTelevision {

    public void on(){
        log.info("开启电视机");
    }

    public void off(){
        log.info("关闭电视机");
    }

}
