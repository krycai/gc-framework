package com.allen.pattern.State;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName StartState
 * @Description 具体状态，实现相关行为的具体状态类
 * @Author allen小哥
 * @Date 2019/4/1 19:37
 **/
@Slf4j
public class StartState implements State {
    @Override
    public void doAction(Context context) {
      log.info("开始执行StartState操作");
      context.setState(this);
    }

    @Override
    public String toString(){
        return "start state";
    }
}
