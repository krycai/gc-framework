package com.allen.pattern.filter;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Target
 * @Description 目标程序类
 * @Author Xu
 * @Date 2019/3/26 17:51
 **/
@Slf4j
public class Target {

    public void execute(String name){
        log.info("目标出现，目标出现"+name);
    }

}
