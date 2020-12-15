package com.allen.pattern.chain;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ManagerHandler
 * @Description TODO
 *
 * 具体处理者角色：实现或者继承抽象这角色，具体逻辑根据实际的架构来定，后面会提到
 *
 * @Author Xu
 * @Date 2019/3/28 9:48
 **/
@Slf4j
public class ManagerHandler extends Handler {
    public ManagerHandler(int level) {
        super(1);
    }

    @Override
    public void report(Demand demand) {
      log.info("经理的需求:"+demand.getDetail());
      log.info(getClass().getSimpleName()+":这技术是行不通的，挺你");
      log.info(getClass().getCanonicalName()+"=="+getClass().getName()+"=--"+getClass().getTypeName());
    }
}
