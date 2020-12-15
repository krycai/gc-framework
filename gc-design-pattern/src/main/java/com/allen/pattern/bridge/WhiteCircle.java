package com.allen.pattern.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName WhiteCircle
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/26 16:56
 **/
@Slf4j
public class WhiteCircle implements DrawAPI {
    @Override
    public void drawCircle(String type,int y) {
      log.info("开始画白色圆===="+type+"=-=-="+y);
    }
}
