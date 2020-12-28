package com.allen.algorithm.tree;

import org.junit.Test;

/**
 * Created by xuguocai on 2020/12/28 14:53
 */
public class TreeTest {

    @Test
    public void test1(){
        BinaryNode node = new BinaryNode() ;
        //创建二叉树
        node = node.createNode() ;
        System.out.println(node);

        //层序遍历二叉树
        node.levelIterator(node) ;

    }
}
