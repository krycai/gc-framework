package com.allen.pattern.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ClientTest
 * @Description TODO
 *
 * 　享元模式的优点在于它大幅度地降低内存中对象的数量。但是，它做到这一点所付出的代价也是很高的：
 * ●　　享元模式使得系统更加复杂。为了使对象可以共享，需要将一些状态外部化，这使得程序的逻辑复杂化。
 * ●　　享元模式将享元对象的状态外部化，而读取外部状态使得运行时间稍微变长。
 *
 * @Author Xu
 * @Date 2019/3/27 15:24
 **/
public class ClientTest {

    public static void main(String[] args){

        FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight =factory.getFactory(new Character('a'));

        flyweight.operation("66666");

        //内蕴状态
        Flyweight weight = factory.getFactory(new Character('b'));
        // 外蕴状态
        weight.operation("88888888");

        System.out.println("=========复合享元模式=========");
        List<Character> list = new ArrayList<>();
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('d');

        FlyweightFactory doubl = new FlyweightFactory();
        Flyweight one = doubl.getFactory(list);
        Flyweight two = doubl.getFactory(list);
        one.operation("2");

        System.out.println("复合享元模式是否共享对象==："+(one == two));

        System.out.println("-------单享元模式---------");
        Character character = 'm';
        Flyweight three = doubl.getFactory(character);
        Flyweight four = doubl.getFactory(character);
        System.out.println("单享元模式是否共享对象==="+(three == four));

    }


}
