package com.allen.algorithm.tree;

import com.allen.algorithm.tree.base.TreeNode;
import org.junit.Test;

/**
 * @author xuguocai on 2021/5/7 15:36  平衡二叉树
 *
 * 一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 当我们判定其中任意一个节点如果不满足平衡二叉树时，那说明整棵树已经不是一颗平衡二叉树，我们可以对其进行阻断，不需要继续递归下去。
 */
public class TreeBalance {

    @Test
    public void test() {
        TreeNode root = TreeNode.createNode3();
        boolean validBST = IsBalanced(root);
        System.out.println(validBST);

    }

    /**
     * 判断 是否是 平衡二叉树
     * @param root
     * @return
     */
    public boolean balance(TreeNode root){
        if (root == null){
            return false;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        int tmpLeft = leftBalance(left);

        int tmpRight = leftBalance(right);

        int tmp = tmpLeft - tmpRight;
        int abs = Math.abs(tmp);

        System.out.println("tmpLeft:"+tmpLeft+" tmpRight:"+tmpRight+" abs:"+abs);
        //绝对值不超过1
        if (abs <= 1){
            return true;
        }

        return false;
    }

    /**
     * 获取 左子树  右子树的高度 ---》递归处理
     * @param root
     * @return
     */
    public int leftBalance(TreeNode root){
        int left = 1;
        int right = 1;
        if (root.left != null) {
            // 递归一次，高度加 1
            left = leftBalance(root.left) + 1;
        }
        if (root.right != null) {
            // 递归一次，高度加 1
            right = leftBalance(root.right) + 1;
        }
        System.out.println(left+" ===" +right);

        // 选择最高的的值
        if (left > right){
            return left;
        }
        else{
            return right;
        }

    }

    /**
     * 平衡二叉树
     *   1、左右子树的绝对值小于或者等于1 ，这里指这颗 二叉树所有的左右子树。
     *   2、每个层级的左右子树必须属于平衡二叉树
     *
     * @param root
     * @return
     */
    public boolean IsBalanced(TreeNode root) {
        if (root == null){
            return true;
        }

        // Check if subtrees have height within 1. If they do, check if the subtrees are balanced
        return Math.abs(height(root.left) - height(root.right)) < 2 // 判断 左右 子树
                && IsBalanced(root.left) // 判断 左子树 是否是 平衡二叉树
                && IsBalanced(root.right); // 判断 右子树 是否是 平衡二叉树
    }

    /**
     * 从 叶子节点 开始计算，也就是 0 开始逆推 ，每一层 加 1
     * @param root
     * @return
     */
    private int height(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(height(root.left), height(root.right));
    }


}
