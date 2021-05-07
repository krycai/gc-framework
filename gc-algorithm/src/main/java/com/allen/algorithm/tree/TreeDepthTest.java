package com.allen.algorithm.tree;

import com.allen.algorithm.tree.base.TreeNode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author xuguocai on 2021/5/6 17:02  二叉树遍历
 */
public class TreeDepthTest {

    @Test
    public void test() {
        TreeNode root = TreeNode.createNode();
        postOrderTraverse12(root);
    }

    /**
     * 前序遍历  --- 递归版本   根结点 ---> 左子树 ---> 右子树
     * @param root
     */
    public void preOrderTraverse1(TreeNode root) {
        if (root != null) {
            System.out.print(root.value+"  ");
            preOrderTraverse1(root.left);
            preOrderTraverse1(root.right);
        }
    }

    /**
     * 前序遍历  --- 非递归版本
     *
     * a)访问之，并把结点node入栈，当前结点置为左孩子；
     *
     * b)判断结点node是否为空，若为空，则取出栈顶结点并出栈，将右孩子置为当前结点；否则重复a)步直到当前结点为空或者栈为空
     * （可以发现栈中的结点就是为了访问右孩子才存储的）
     *
     * @param root
     */
    public void preOrderTraverse2(TreeNode root) {
        // 利用链表作为栈的特点 --- 先进后出
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                System.out.print(pNode.value+"  ");
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                pNode = node.right;
            }
        }
    }

    /**
     * 中序遍历  --- 递归版本   左子树---> 根结点 ---> 右子树
     * @param root
     */
    public void inOrderTraverse1(TreeNode root) {
        if (root != null) {
            inOrderTraverse1(root.left);
            System.out.print(root.value+"  ");
            inOrderTraverse1(root.right);
        }
    }

    /**
     * 中序遍历  --- 非递归版本
     * @param root
     */
    public void inOrderTraverse2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                System.out.print(node.value+"  ");
                pNode = node.right;
            }
        }
    }

    /**
     * 后序遍历 --- 递归版本  左子树 ---> 右子树 ---> 根结点
     *  首先，遍历左子树
     *    当前节点的左子树不存在，则遍历当前节点的右子树；
     *    当前节点的左子树存在，则遍历当前节点的左子树；
     *
     *  接着上面的思路，
     *    当前节点没有左子树，则看查有木有右子树，没有左、右子树，则输出当前节点的值
     *
     *  最后
     *    左、右子树递归（由上往下、由下往上）处理，并输出值。
     *
     *
     * @param root
     */
    public void postOrderTraverse1(TreeNode root) {
        if (root != null) {
            postOrderTraverse1(root.left);
            postOrderTraverse1(root.right);
            System.out.print(root.value+"  ");
        }
    }

    /**
     * 后序遍历 --- 非递归版本
     *
     * lastVisited这个结点指针解释：
     * 根结点先进入stack栈，然后左子结点或右子结点，特别是右子结点弹出来之后，再弹出根结点，这个lastVisited这个结点指针就指示右子结点已经被访问完了，可以弹出根结点了。
     *
     * @param root
     * @return
     */
    public List<Integer> postOrderTraverse12(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        TreeNode lastVisited = null;

        while(!stack.isEmpty() || root != null) {
            if (root != null) {
                // 节点存在，则放进栈中并继续获取左节点
                stack.push(root);
                root = root.left;
            } else {// 栈 先进后出 特点
                TreeNode node = stack.peek();
                // 判断当前节点是否存在 右节点，存在则继续遍历右节点
                if (node.right != null && lastVisited != node.right) {
                    root = node.right;
                } else {
                    System.out.print(node.value+"  ");
                    list.add(node.value);
                    lastVisited = stack.pop();
                }
            }
        }
//        for (Integer item : list){
//            System.out.print(item+"  ");
//        }
        return list;
    }

    /**
     * 层次遍历  只需按层次遍历即可
     *
     * 层次遍历的代码比较简单，只需要一个队列即可，先在队列中加入根结点。之后对于任意一个结点来说，在其出队列的时候，访问之。同时如果左孩子和右孩子有不为空的，入队列。
     *
     * @param root
     */
    public void levelTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 添加到链表后面
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 从链表取出第一个元素
            TreeNode node = queue.poll();
            System.out.print(node.value+"  ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }
}
