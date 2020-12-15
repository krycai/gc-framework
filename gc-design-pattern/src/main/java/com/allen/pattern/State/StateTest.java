package com.allen.pattern.State;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName StateTest
 * @Description TODO
 *
 * 优点： 1、封装了转换规则。 2、枚举可能的状态，在枚举状态之前需要确定状态种类。
 * 3、将所有与某个状态有关的行为放到一个类中，并且可以方便地增加新的状态，只需要改变对象状态即可改变对象的行为。
 * 4、允许状态转换逻辑与状态对象合成一体，而不是某一个巨大的条件语句块。 5、可以让多个环境对象共享一个状态对象，从而减少系统中对象的个数。
 *
 * 缺点： 1、状态模式的使用必然会增加系统类和对象的个数。 2、状态模式的结构与实现都较为复杂，如果使用不当将导致程序结构和代码的混乱。
 * 3、状态模式对"开闭原则"的支持并不太好，对于可以切换状态的状态模式，增加新的状态类需要修改那些负责状态转换的源代码，否则无法切换到新增状态，
 * 而且修改某个状态类的行为也需修改对应类的源代码。
 *
 * 使用场景： 1、行为随状态改变而改变的场景。 2、条件、分支语句的代替者。
 *
 *状态模式解决的问题：状态模式主要解决的是当控制一个对象状态的条件表达式过于复杂时的情况。把状态的判断逻辑转移到表示不同状态的一系列类中，可以把复杂的判断逻辑简化。
 *
 *
 *
 * @Author allen小哥
 * @Date 2019/4/1 19:41
 **/
@Slf4j
public class StateTest {

    public static void main(String[] args){
        Context context = new Context();
        StartState start = new StartState();
        start.doAction(context);

        log.info("开始执行操作："+context.getState().toString());

        StopState stop = new StopState();
        stop.doAction(context);
        log.info("开始执行停止操作："+context.getState().toString());




    }

}
