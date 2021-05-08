package com.allen.algorithm.tree;

import com.allen.algorithm.tree.base.TreeNode;
import org.junit.Test;

/**
 * @author xuguocai on 2021/5/8 10:28  二叉树的剪枝
 *
 * 给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。返回移除了所有不包含 1 的子树的原二叉树。( 节点 X 的子树为 X 本身，以及所有 X 的后代。)
 */
public class TreePruning {

    @Test
    public void test() {
        TreeNode root = TreeNode.createNode5();
        TreeNode treeNode = pruneTree(root);
        System.out.println(treeNode);

    }


    public TreeNode pruneTree(TreeNode root){
        deal(root);
        return root;
    }

    /**
     * 根据 value 不为 1 的节点，都考虑删除
     *
     * 移除了所有不包含 1 的子树的原二叉树。( 节点 X 的子树为 X 本身，以及所有 X 的后代。)
     *
     * 思路：从最后叶子节点开始查找，递归处理
     *
     * @param node
     * @return
     */
    public TreeNode deal(TreeNode node){
        if (node == null) {
            return null;
        }
        node.left = deal(node.left);
        node.right = deal(node.right);

        // 从 叶子节点 开始判断，值为 0 ，则将 当前节点 置空
        if (node.left == null && node.right == null && node.value == 0){
            return null;
        }

        return node;
    }

}
