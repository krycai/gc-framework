package com.allen.algorithm.tree;

import java.util.LinkedList;

/**
 *  二叉树层序排序
 *   借助链表先进先出特点
 *
 * Created by xuguocai on 2020/12/28 13:56
 */
public class BinaryNode {

    private Object data ;
    // 左节点
    private BinaryNode left ;
    // 右节点
    private BinaryNode right ;

    public BinaryNode() {
    }

    public BinaryNode(Object data, BinaryNode left, BinaryNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }

    public BinaryNode createNode(){
        BinaryNode node = new BinaryNode("1",null,null) ;
        BinaryNode left2 = new BinaryNode("2",null,null) ;
        BinaryNode left3 = new BinaryNode("3",null,null) ;
        BinaryNode left4 = new BinaryNode("4",null,null) ;
        BinaryNode left5 = new BinaryNode("5",null,null) ;
        BinaryNode left6 = new BinaryNode("6",null,null) ;
        // 左边数据
        node.setLeft(left2) ;
        node.setRight(left3);

        left2.setLeft(left4);
        // 右边数据
        left2.setRight(left6);

        left3.setRight(left5) ;

        return node ;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    /**
     * 二叉树的层序遍历 借助于队列来实现 借助队列的先进先出的特性
     *
     * 首先将根节点入队列 然后遍历队列。
     * 首先将根节点打印出来，接着判断左节点是否为空 不为空则加入队列
     * @param node
     */
    public void levelIterator(BinaryNode node){
        // 链表处理
        LinkedList<BinaryNode> queue = new LinkedList<>() ;

        //先将根节点入队
        queue.offer(node) ;
        BinaryNode current ;
        while (!queue.isEmpty()){
            current = queue.poll();

            System.out.print(current.data+"--->");

            if (current.getLeft() != null){
                queue.offer(current.getLeft()) ;
            }
            if (current.getRight() != null){
                queue.offer(current.getRight()) ;
            }
        }
    }

    public static void main(String[] args) {
        BinaryNode node = new BinaryNode() ;
        //创建二叉树
        node = node.createNode() ;
        System.out.println(node);

        //层序遍历二叉树
        node.levelIterator(node) ;

    }
}

