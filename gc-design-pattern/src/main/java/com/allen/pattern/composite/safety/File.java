package com.allen.pattern.composite.safety;

import lombok.Data;

/**
 * @ClassName File
 * @Description TODO
 * 组合中的对象声明接口，在适当的情况下，实现所有类共有接口的默认行为。声明一个接口用于访问和管理Component子部件。
 *
 * 1、组合模式，就是在一个对象中包含其他对象，这些被包含的对象可能是终点对象（不再包含别的对象），也有可能是非终点对象（其内部还包含其他对象，或叫组对象），
 * 我们将对象称为节点，即一个根节点包含许多子节点，这些子节点有的不再包含子节点，而有的仍然包含子节点，以此类推。
 *
 * 2、所谓组合模式，其实说的是对象包含对象的问题，通过组合的方式（在对象内部引用对象）来进行布局，我认为这种组合是区别于继承的，
 * 而另一层含义是指树形结构子节点的抽象（将叶子节点与数枝节点抽象为子节点），区别于普通的分别定义叶子节点与数枝节点的方式。
 *
 * @Author Xu
 * @Date 2019/3/26 19:32
 **/
@Data
public abstract class File {

    private String name;

    public File(String name){
        this.name = name;
    }

    public abstract void display();

}
