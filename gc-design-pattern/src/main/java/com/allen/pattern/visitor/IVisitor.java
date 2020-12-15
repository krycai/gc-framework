package com.allen.pattern.visitor;

/**
 * @ClassName IVisitor
 * @Description 抽象访问者
 *
 * 它的参数就是可以访问的元素，它的方法个数理论上来讲与元素个数（Element的实现类个数）是一样的，从这点不难看出，访问者模式要求元素类的个数不能改变（不能改变的意思是说，如果元素类的个数经常改变，则说明不适合使用访问者模式）。
 *
 * 抽象类或者接口，声明访问者可以访问哪些元素，具体到程序中就是visit方法中的参数定义哪些对象是可以被访问的。
 *
 * @Author allen小哥
 * @Date 2019/4/1 14:59
 **/
public interface IVisitor {

    public void visit(ConcreteElementA A);

    public void visit(ConcreteElementB B);


}
