package com.allen.pattern.State;

/**
 * @ClassName State
 * @Description  状态接口，它定义了每一个状态的行为集合，这些行为会在Context中得以使用。
 *
 * 定义：当一个对象的内在状态改变时允许改变其行为，这个对象看起来像是改变了其类。
 *
 * 在状态模式（State Pattern）中，类的行为是基于它的状态改变的。这种类型的设计模式属于行为型模式。
 *
 * @Author allen小哥
 * @Date 2019/4/1 19:34
 **/
public interface State {

    public void doAction(Context context);

}
