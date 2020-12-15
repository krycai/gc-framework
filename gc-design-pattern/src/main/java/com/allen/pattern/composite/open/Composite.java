package com.allen.pattern.composite.open;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Composite
 * @Description 构件
 * @Author Xu
 * @Date 2019/3/27 9:48
 **/
@Slf4j
public class Composite extends Component {

    /**
     * 构件容器
     */
    private List<Component> list = new ArrayList<>();

    @Override
    public void add(Component component) {
        this.list.add(component);
    }

    @Override
    public void remove(Component component) {
        this.list.remove(component);
    }

    @Override
    public List<Component> getChirldren() {
        return this.list;
    }
}
