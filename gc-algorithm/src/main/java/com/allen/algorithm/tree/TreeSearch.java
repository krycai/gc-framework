package com.allen.algorithm.tree;

import com.allen.algorithm.tree.base.TreeNode;
import org.junit.Test;

/**
 * @author xuguocai on 2021/5/7 10:11 二叉搜索树
 *
 * 对于任意一个节点，我们不光需要左节点值小于该节点，并且左子树上的所有节点值都需要小于该节点。（右节点一致）所以我们在此引入上界与下界，
 * 用以保存之前的节点中出现的最大值与最小值。
 *
 *
 *
 *
 */
public class TreeSearch {

    @Test
    public void test() {
        TreeNode root = TreeNode.createNode2();
        boolean validBST = isValidBST(root);
        System.out.println(validBST);
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null){
            return true;
        }
        return isValidBST2(root, null, null);
    }

    /**
     * 递归处理，每次遍历，取当前节点与最大 最小值 比较
     *
     * @param root  当前节点
     * @param min  最小值，相对于 右节点 判断
     * @param max  最大值，相对于 左节点  判断
     * @return
     */
    private boolean isValidBST2(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.value <= min.value){
            return false;
        }
        if (max != null && root.value >= max.value){
            return false;
        }
        return isValidBST2(root.left, min, root) && isValidBST2(root.right, root, max);
    }


}
