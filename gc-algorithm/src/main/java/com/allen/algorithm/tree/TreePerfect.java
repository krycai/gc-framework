package com.allen.algorithm.tree;

import com.allen.algorithm.tree.base.TreeNode;
import org.junit.Test;

/**
 * @author xuguocai on 2021/5/8 9:09  完全二叉树的节点个数
 */
public class TreePerfect {

    @Test
    public void test() {
        TreeNode root = TreeNode.createNode4();
        int i = countNodes(root);
        System.out.println(i);

    }

    /**
     * 给出一个完全二叉树，求出该树的节点个数。
     * @param root
     * @return
     */
    public int countTree(TreeNode root){
        if (root == null){
            return 0;
        }
        // 表示当前节点与左右节点的个数，以此递归处理
        int tmp = 1 + countTree(root.left) + countTree(root.right);

        return tmp;
    }

    /**
     * 给出一个完全二叉树，求出该树的节点个数。
     *
     * 完全二叉树，从左到右分布，左边不存在，则不是完全二叉树
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 计算 左右树 高度
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        // 判断左子树 是否是 满二叉树
        if (left == right) {
            //递归 右子树 ，1 << left 表示 2 的 left 次方
            return countNodes(root.right) + (1 << left);
        } else {
            // 右子树是满二叉树，则递归 左子树
            return countNodes(root.left) + (1 << right);
        }
    }

    /**
     * 计算节点高度
     * @param root
     * @return
     */
    private int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            // 计算左节点，直至左节点为空（完全二叉树 从 左到右 的特性）
            root = root.left;
        }
        return level;
    }

}
