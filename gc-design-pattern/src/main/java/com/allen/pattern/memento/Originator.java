package com.allen.pattern.memento;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Originator
 * @Description 发起人：记录当前时刻的内部状态，负责定义哪些属于备份范围的状态，负责创建和恢复备忘录数据
 *
 * 定义：在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。这样就可以将该对象恢复到原先保存的状态
 *
 *Originator类中的state变量需要备份，以便在需要的时候恢复；
 *
 * @Author allen小哥
 * @Date 2019/4/1 16:59
 **/
@Slf4j
public class Originator {

    private String status = "";

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public Memento createMemento(){
         return new Memento(this.status);
    }

    public void restoreMemento(Memento memento){
        this.setStatus(memento.getStatus());
    }

}
