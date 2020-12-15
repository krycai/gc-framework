package com.allen.pattern.composite.safety;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ImageFile
 * @Description TODO
 * 叶子对象。叶子结点没有子结点
 * @Author Xu
 * @Date 2019/3/26 19:34
 **/
@Slf4j
public class ImageFile extends File {
    public ImageFile(String name) {
        super(name);
    }

    @Override
    public void display() {
      log.info("图片文件夹"+super.getName());
    }
}
