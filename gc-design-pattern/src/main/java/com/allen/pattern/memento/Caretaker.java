package com.allen.pattern.memento;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Caretaker
 * @Description 管理角色：对备忘录进行管理，保存和提供备忘录
 *
 * 而Caretaker类就是用来管理备忘录类的，用来向备忘录对象中写入状态或者取回状态。
 *
 * @Author allen小哥
 * @Date 2019/4/1 16:56
 **/
@Slf4j
public class Caretaker {

    private Memento memento;

    public void setMemento(Memento memento){
        this.memento = memento;
    }

    public Memento getMemento(){
        return memento;
    }

}
