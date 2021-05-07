package com.allen.algorithm.tree;

import com.allen.algorithm.tree.base.TreeNode;
import org.junit.Test;

/**
 * @author xuguocai on 2021/5/7 13:53  删除二叉搜索树中的节点
 *
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 *
 * 思路：
 *
 * 如果删除的节点没有左右节点，直接删除；如果只有左节点，左节点赋值给准备删除的节点；如果只有右节点，右节点赋值给准备删除的节点；
 * 如果左右节点都有，我们找出要删除的节点的所有右节点中的最小值赋值给要删除的节点，并且把这个最小的节点删掉，或者找出要删除的所有左节点中的最大值赋值给要删除的节点，
 * 并把这个最大的节点删除，大功告成.
 *
 */
public class TreeSearchRemove {

    @Test
    public void test() {
        TreeNode root = TreeNode.createNode2();
        TreeNode treeNode = deleteNode(root,9);
        System.out.println(treeNode);

    }

    /**
     * One step right and then always left
     *
     * 选择 左节点 的值  ---》小值
    */
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null){
            root = root.left;
        }
        return root.value;
    }

    /**
     * One step left and then always right
     *
     * 选择 右节点 的值 ---》大值
    */
    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.value;
    }

    /**
     *  递归处理：删除二叉搜索树中的节点 。 todo 对删除节点的处理不是很理解
     *
     * @param root 当前节点
     * @param key 要查找的值
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return null;
        }

        // delete from the right subtree 往右边节点查找-- 》二叉搜索树 特性：首节点 大于 左节点，右节点 大于 首节点。右节点 大于 左节点
        if (key > root.value){
            root.right = deleteNode(root.right, key);
        }
        // delete from the left subtree  往 左边节点 查找
        else if (key < root.value){
            root.left = deleteNode(root.left, key);
        }
        // delete the current node  删除当前节点
        else {
            // the node is a leaf  若是 子节点 叶子，则直接删除
            if (root.left == null && root.right == null){
                root = null;
            }
            // the node is not a leaf and has a right child 弱存在 右子节点
            else if (root.right != null) {
                // 选择 左节点 的值
                root.value = successor(root);
                root.right = deleteNode(root.right, root.value);
            }
            // the node is not a leaf, has no right child, and has a left child   若存在 左节点
            else {
                // 选择 右节点 的值
                root.value = predecessor(root);
                root.left = deleteNode(root.left, root.value);
            }
        }
        return root;
    }

}
