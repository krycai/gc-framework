package com.allen.pattern.memento;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Memento
 * @Description 备忘录：负责存储发起人对象的内部状态，在需要的时候提供发起人需要的内部状态。
 *
 * Memento类中，也有一个state变量，用来存储Originator类中state变量的临时状态
 *
 * @Author allen小哥
 * @Date 2019/4/1 16:54
 **/
@Slf4j
public class Memento {

    private String status = "";

    public Memento(String status){
        this.status = status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

}
