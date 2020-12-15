package com.allen.pattern.State;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName StopState
 * @Description  具体状态，实现相关行为的具体状态类
 * @Author allen小哥
 * @Date 2019/4/1 19:38
 **/
@Slf4j
public class StopState implements State {
    @Override
    public void doAction(Context context) {
      log.info("开始执行StopState操作");
      context.setState(this);
    }

    @Override
    public String toString(){
        return "stop state";
    }
}
