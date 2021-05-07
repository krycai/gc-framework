package com.allen.algorithm.tree.base;

/**
 * @author xuguocai on 2021/5/6 14:04 树节点
 */
public class TreeNode {
    public int value;

    public TreeNode left;

    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public static TreeNode createNode(){
        TreeNode head = new TreeNode(1);

        TreeNode second = new TreeNode(2);
        TreeNode three = new TreeNode(3);

        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode eight = new TreeNode(8);

        head.right = three;
        head.left = second;

        second.right = five;
        second.left = four;

        five.right = eight;
        five.left = seven;

        three.right = six;

        return head;
    }

    public static TreeNode createNode2(){
        TreeNode head = new TreeNode(9);

        TreeNode second = new TreeNode(5);
        TreeNode three = new TreeNode(15);

        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(7);
        TreeNode six = new TreeNode(11);
        TreeNode seven = new TreeNode(21);
        TreeNode eight = new TreeNode(14);

        head.right = three;
        head.left = second;

        second.right = five;
        second.left = four;

        three.right = seven;
        three.left = six;

        six.right = eight ;

        return head;
    }


    public static TreeNode createNode3(){
        TreeNode head = new TreeNode(9);

        TreeNode second = new TreeNode(5);
        TreeNode three = new TreeNode(15);
        head.right = three;
        head.left = second;

        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(7);
        second.right = five;
        second.left = four;

        TreeNode six = new TreeNode(11);
        TreeNode seven = new TreeNode(21);
        three.right = seven;
        three.left = six;

        TreeNode eight = new TreeNode(14);
        six.right = eight ;









        return head;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public static class DepthTreeNode{
        public int depth; //当前节点的深度
        public TreeNode node; // 当前节点

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public TreeNode getNode() {
            return node;
        }

        public void setNode(TreeNode node) {
            this.node = node;
        }

        public DepthTreeNode(int depth, TreeNode node) {
            this.depth = depth;
            this.node = node;
        }
    }
}
