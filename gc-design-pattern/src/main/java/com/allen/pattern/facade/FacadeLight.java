package com.allen.pattern.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName FacadeLight
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/20 17:29
 **/
@Slf4j
public class FacadeLight {

    public void on(){
        log.info("开启灯光");
    }

    public void off(){
        log.info("关闭灯光");
    }

}
