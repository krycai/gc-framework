package com.allen.pattern.memento;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName MementoTest
 * @Description TODO
 *
 * 备忘录模式的优点有：
 * 当发起人角色中的状态改变时，有可能这是个错误的改变，我们使用备忘录模式就可以把这个错误的改变还原。
 * 备份的状态是保存在发起人角色之外的，这样，发起人角色就不需要对各个备份的状态进行管理。
 *
 * 备忘录模式的缺点：
 *在实际应用中，备忘录模式都是多状态和多备份的，发起人角色的状态需要存储到备忘录对象中，对资源的消耗是比较严重的。
 *
 *
 * @Author allen小哥
 * @Date 2019/4/1 17:02
 **/
@Slf4j
public class MementoTest {

    public static void main(String[] args){
        Originator originator = new Originator();
        originator.setStatus("发起者状态");
        log.info("初始状态==============="+originator.getStatus());
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());
        originator.setStatus("发起者状态2");
        log.info("改变后的状态========"+originator.getStatus());
        originator.restoreMemento(caretaker.getMemento());
        log.info("恢复后的状态======"+originator.getStatus());
    }

}
