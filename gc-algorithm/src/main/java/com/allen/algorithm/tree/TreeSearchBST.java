package com.allen.algorithm.tree;

import com.allen.algorithm.tree.base.TreeNode;
import org.junit.Test;

/**
 * @author xuguocai on 2021/5/7 11:23  二叉搜索树中的搜索
 *
 * 递归与迭代的区别
 *
 *
 * 递归：重复调用函数自身实现循环称为递归；
 *
 * 迭代：利用变量的原值推出新值称为迭代，或者说迭代是函数内某段代码实现循环；
 *
 */
public class TreeSearchBST {

    @Test
    public void test() {
        TreeNode root = TreeNode.createNode2();
        TreeNode treeNode = searchBST2(root, 5);
        System.out.println(treeNode);
    }

    //递归
    public TreeNode searchBST(TreeNode root, int val) {
        // 不存在，返回空
        if (root == null){
            return null;
        }
        // 根节点 大于 val ，则查找左子树
        if (root.value > val) {
            return searchBST(root.left, val);
        } else if (root.value < val) {
            // 跟节点 小于 val ，则查右子树
            return searchBST(root.right, val);
        } else {
            // 找到，则返回
            return root;
        }
    }

    //迭代
    public TreeNode searchBST2(TreeNode root, int val) {
        // 迭代遍历
        while (root != null) {
            if (root.value == val) {
                return root;
            } else if (root.value > val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return null;
    }

}
