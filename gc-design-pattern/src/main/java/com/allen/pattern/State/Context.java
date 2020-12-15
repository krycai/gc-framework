package com.allen.pattern.State;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Context
 * @Description TODO
 *
 * 它就是那个含有状态的对象，它可以处理一些请求，这些请求最终产生的响应会与状态相关
 *
 * @Author allen小哥
 * @Date 2019/4/1 19:34
 **/
@Slf4j
public class Context {

    private State state;

    public Context(){
        this.state = null;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }

}
