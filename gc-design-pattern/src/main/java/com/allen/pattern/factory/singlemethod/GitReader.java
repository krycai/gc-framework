package com.allen.pattern.factory.singlemethod;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName GitReader
 * @Description TODO  具体产品。
 * @Author Xu
 * @Date 2019/3/19 17:06
 **/
@Slf4j
public class GitReader implements Reader {
    @Override
    public void read() {
      log.info("执行git实现类");
    }
}
