package com.allen.pattern.builder;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Computer
 * @Description 组装电脑:定义具体产品类（Product）
 * @Author Xu
 * @Date 2019/3/20 15:27
 **/
@Slf4j
public class Computer {

    private List<String> parts = new ArrayList<>();

    public void add(String part){
        parts.add(part);
    }

    public void show(){
        for (String part : parts){
            log.info("正在组装======》"+part);
        }
    }

}
