package com.allen.pattern.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Adaptee
 * @Description 源目标====被适配者
 * @Author Xu
 * @Date 2019/3/20 16:28
 **/
@Slf4j
public class Adaptee {

    public void otherRequest(){
        log.info("开始调用这个接口:otherRquest");
    }

}
