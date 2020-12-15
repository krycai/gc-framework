package com.allen.pattern.composite.safety;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Folder
 * @Description 安全模式
 * 容器对象，定义有枝节点行为，用来存储子部件，在Component接口中实现与子部件有关操作，如增加( add)和删除(remove)等
 *
 * 安全模式在抽象组件中只定义一些默认的行为或属性，它是把树枝节点和树叶节点彻底分开
 *
 * 安全模式与依赖倒置原则冲突；
 *
 * 安全模式在遍历树形结构的的时候需要进行强制类型转换
 *
 * @Author Xu
 * @Date 2019/3/26 19:35
 **/
@Slf4j
public class Folder extends File {
    private List<File> files;

    public Folder(String name) {
        super(name);
        files = new ArrayList<>();
    }

    @Override
    public void display() {
        for (File file : files){
            log.info("文件夹展示"+file);
        }
    }

    public void add(File file){
        files.add(file);
    }

    public void remove(File file){
        files.remove(file);
    }

}
