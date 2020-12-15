package com.allen.pattern.composite.safety;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName VedioFile
 * @Description TODO
 * 叶子对象。叶子结点没有子结点
 * @Author Xu
 * @Date 2019/3/26 19:38
 **/
@Slf4j
public class VedioFile extends File {
    public VedioFile(String name) {
        super(name);
    }

    @Override
    public void display() {
      log.info("视频文件"+super.getName());
    }
}
