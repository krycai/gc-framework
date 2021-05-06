package com.allen.algorithm.tree;

import com.allen.algorithm.tree.base.TreeNode;
import org.junit.Test;

import java.util.Stack;

/**
 * @author xuguocai on 2021/5/6 14:07  二叉树的最大深度 （递归与非递归）
 */
public class TreeDepth {

    /**
     * 获取深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        else{
            return repeat(root);
        }
    }

    /**
     * 遍历 -->递归处理
     *  每次遍历一个节点，算出当前节点是否存在左右子节点；
     *  若无则当前深度为1，
     *  若有 则递归处理，获取到最底层的节点的深度为1，然后逆推累加1 （repeat(root.left) + 1）
     *
     * @param root
     * @return
     */
    public int repeat(TreeNode root) {
        int left = 1;
        int right = 1;
        if (root.left != null) {
            left = repeat(root.left) + 1;
        }
        if (root.right != null) {
            right = repeat(root.right) + 1;
        }
        if (left > right){
            return left;
        }
        else{
            return right;
        }

    }

    /**
     * 利用 栈的特点是 先进后出  非递归
     * @param head
     * @return
     */
    public int getMaxDepth(TreeNode head) {
        int maxDepth = 0; // 记录最大深度
        if (head == null) {
            return maxDepth;
        }

        Stack<TreeNode.DepthTreeNode> stack = new Stack<>();
        // 先将根结点压入栈中
        // 根节点入栈
        TreeNode.DepthTreeNode rootNode = new TreeNode.DepthTreeNode(1,head);
        stack.push(rootNode);

        System.out.print("前序遍历： ");
        while (!stack.isEmpty()) {
            TreeNode.DepthTreeNode node = stack.pop();
            System.out.print(node.getNode().value);
            System.out.print(" ");

            TreeNode left = node.getNode().left;
            TreeNode right = node.getNode().right;

            // 更新树的最大深度值
            maxDepth = Math.max(node.depth, maxDepth);
            // 将节点右边的压入栈中
            if (right != null) {
                TreeNode.DepthTreeNode rightNode = new TreeNode.DepthTreeNode(node.depth + 1, right);
                // 放进去，遍历的时候优先取 -- 先进后出
                stack.push(rightNode);
            }

            // 压入左边的栈中
            if (left != null) {
                TreeNode.DepthTreeNode leftNode = new TreeNode.DepthTreeNode(node.depth + 1, left);
                // 放进去，遍历的时候优先取 --- 先进后出
                stack.push(leftNode);
            }
        }
        System.out.println();
        return maxDepth;
    }


    /**
     * 非递归的使用栈来实现 DFS  --- 先序遍历
     * 依据栈的先进后出特性
     * 1. 将二叉树的头结点放入栈中
     * 2. 将头结点 1 从栈中弹出，并查看头结点是否有左右节点，将右节点和左节点压入栈中【3，2】
     * 3. 将头结点 2 从栈中弹出，并查看 2 节点是否有左右节点，将右节点和左节点压入栈中【3，5，4】
     * 4. 将头结点 4 从栈中弹出，无左右节点【3，5】
     * 5. 将头结点 5 从栈中弹出，无左右节点【3】
     * 6. 将头结点 3 从栈中弹出，并查看 3 节点是否有左右节点，将右节点和左节点压入栈中【7，6】
     * 7. 将头结点 6 从栈中弹出，无左右节点【7】
     * 8. 将头结点 7 从栈中弹出，无左右节点
     *
     * https://blog.csdn.net/gshtime/article/details/86175901
     *
     * @param head 二叉树
     */
    public void preOrderTraverse1(TreeNode head) {
        System.out.println(head);

        if (head == null) {
            return ;
        }

        Stack<TreeNode> stack = new Stack<>();
        // 先将根结点压入栈中
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.value);
            System.out.print(" ");

            // 将节点右边的压入栈中
            if (node.right != null) {
                stack.push(node.right);
            }

            // 压入左边的栈中
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    @Test
    public void test(){
        TreeNode root = TreeNode.createNode();
        // 递归处理
        int tmp = maxDepth(root);
        // 非递归，利用栈处理
        int maxDepth = getMaxDepth(root);

        System.out.println(tmp);
        System.out.println(maxDepth);

    }


}
