package com.allen.pattern.composite.open;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @ClassName Component
 * @Description 透明模式
 *
 * 透明模式是把用来组合使用的方法放到抽象类中，不管叶子对象还是树枝对象都有相同的结构，通过判断确认是叶子节点还是树枝节点，
 * 如果处理不当，这个会在运行期出现问题，不是很建议的方式。
 *
 * 透明模式的好处就是它基本遵循了依赖倒转原则，方便系统进行扩展。
 *
 * 在透明模式下，遍历整个树形结构是比较容易的，不用进行强制类型转换。
 *
 * @Author Xu
 * @Date 2019/3/27 9:35
 **/
@Slf4j
public abstract class Component {

    /**
     *个体整体都有的方法
     */
    public void operation(){
        // 编写业务
        log.info("执行操作");
    }

    /**
     * 增加节点
     * @param component
     */
    public abstract void add(Component component);

    /**
     * 移除节点
     * @param component
     */
    public abstract void remove(Component component);

    /**
     * 获取当前节点的分支节点
     * @return
     */
    public abstract List<Component> getChirldren();

}
