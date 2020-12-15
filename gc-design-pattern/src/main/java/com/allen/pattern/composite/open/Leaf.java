package com.allen.pattern.composite.open;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @ClassName Leaf
 * @Description 叶子节点
 * @Author Xu
 * @Date 2019/3/27 9:50
 **/
@Slf4j
public class Leaf extends Component {
    @Override
    public void add(Component component) {
      log.info("添加叶子节点");
    }

    @Override
    public void remove(Component component) {
        log.info("移除叶子节点");
    }

    @Override
    public List<Component> getChirldren() {
        log.info("获取叶子节点的分支节点");
        return null;
    }
}
