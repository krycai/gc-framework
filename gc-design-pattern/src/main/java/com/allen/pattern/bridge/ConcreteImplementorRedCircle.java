package com.allen.pattern.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RedCircle
 * @Description 具体实现类 。
 * @Author Xu
 * @Date 2019/3/26 16:55
 **/
@Slf4j
public class ConcreteImplementorRedCircle implements ImplementorDrawAPI {
    @Override
    public void drawCircle(String type,int y) {
      log.info("开始画红色圆===="+type+"=-=-=---="+y);
    }
}
