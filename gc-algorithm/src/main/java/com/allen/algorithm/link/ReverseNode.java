package com.allen.algorithm.link;

import com.allen.algorithm.link.simple.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 三种方式反向打印单向链表： Function: 三种方式反向打印单向链表
 *
 * Created by xuguocai on 2020/12/30 8:57
 */
public class ReverseNode {

    /**
     * 利用栈的先进后出特性
     * @param node
     */
    public void reverseStackNode(Node node){

        System.out.println("====翻转之前====");

        Stack<Node> stack = new Stack<>() ;
        while (node != null){

            System.out.print(node.value + "===>");

            stack.push(node) ;
            node = node.next ;
        }

        System.out.println("");

        System.out.println("====翻转之后====");
        while (!stack.isEmpty()){
            System.out.print(stack.pop().value + "===>");
        }

    }

    /**
     *  利用数组的倒序处理
     * @param node
     */
    public void reverseListNode(Node node){
        System.out.println("=----反转之前----=");
        List<Node> list = new ArrayList<>();
        while (node != null){
            System.out.print(node.value + "===>");
            list.add(node);
            node = node.next;
        }
        System.out.println("=----反转之后----=");

        for (int i = list.size()-1;i>=0; i--){
            Node temp = list.get(i);
            System.out.print(temp.value + "===>");
        }
    }

    /**
     * 利用头插法插入链表 ： 遍历next的值，赋予链表的开头
     * @param head
     */
    public  void reverseLinkNode(Node head) {
        if (head == null) {
            return ;
        }
        System.out.println("=----反转之前----=");
        //最终翻转之后的 Node
        Node node ;

        Node pre = head;
        Node cur = head.next;
        Node next ;
        while(cur != null){
            next = cur.next;
            //链表的头插法（获取下一个节点，放到头部）
            cur.next = pre;
            pre = cur;
            cur = next;

        }
        head.next = null;
        node = pre;

        System.out.println("=----反转之后----=");
        //遍历新链表
        while (node != null){
            System.out.print(node.value + "===>");
            node = node.next ;
        }

    }


    /**
     * 递归 :单向遍历，最后没有值，则输出，依次递归处理
     * @param node
     */
    public void recNode(Node node){

        if (node == null){
            return ;
        }

        if (node.next != null){
            recNode(node.next) ;
        }
        System.out.print(node.value+"===>");
    }

}
