package com.allen.pattern.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName MethodStrategy
 * @Description 策略模式: 定义了一组算法，将每个算法都封装起来，并且使它们之间可以互换
 * 在策略模式（Strategy Pattern）中，一个类的行为或其算法可以在运行时更改。这种类型的设计模式属于行为型模式。
 *
 * 在策略模式中，我们创建表示各种策略的对象和一个行为随着策略对象改变而改变的 context 对象。策略对象改变 context 对象的执行算法。
 * 优点： 1、算法可以自由切换。 2、避免使用多重条件判断。 3、扩展性良好。
 *
 * 缺点： 1、策略类会增多。 2、所有策略类都需要对外暴露。
 *
 * 使用场景：
 * 1、如果在一个系统里面有许多类，它们之间的区别仅在于它们的行为，那么使用策略模式可以动态地让一个对象在许多行为中选择一种行为。
 * 2、一个系统需要动态地在几种算法中选择一种。
 * 3、如果一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重的条件选择语句来实现。
 *
 * 注意事项：如果一个系统的策略多于四个，就需要考虑使用混合模式，解决策略类膨胀的问题。
 *
 *
 *
 * @Author Xu
 * @Date 2019/3/21 9:32
 **/
@Slf4j
public class MethodStrategy {

    private Strategy strategy;

    public void change(String type){
        switch (type){
            case "eat":
                strategy = new EatStrategy();
                break;
            case "sleep":
                strategy = new SleepStrategy();
                break;
            case "work":
                strategy = new WorkStrategy();
                break;
        }
    }

    public void show(){
        strategy.show();
    }

}
