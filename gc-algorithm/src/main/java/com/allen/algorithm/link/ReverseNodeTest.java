package com.allen.algorithm.link;

import com.allen.algorithm.link.simple.Node;
import org.junit.Test;

/**
 * Created by xuguocai on 2020/12/30 9:51
 */
public class ReverseNodeTest {

    /**
     * 利用栈的先进后出特性
     * @throws Exception
     */
    @Test
    public void reverseStackNode() throws Exception {
        Node<String> node4 = new Node<>("4",null) ;
        Node<String> node3 = new Node<>("3",node4);
        Node<String> node2 = new Node<>("2",node3);
        Node<String> node1 = new Node("1",node2) ;

        ReverseNode reverseNode = new ReverseNode() ;
        reverseNode.reverseStackNode(node1);
    }

    /**
     * 数组倒序
     * @throws Exception
     */
    @Test
    public void reverseListNode() throws Exception {
        Node<String> node4 = new Node<>("4",null) ;
        Node<String> node3 = new Node<>("3",node4);
        Node<String> node2 = new Node<>("2",node3);
        Node<String> node1 = new Node("1",node2) ;

        ReverseNode reverseNode = new ReverseNode() ;
        reverseNode.reverseListNode(node1);
    }

    @Test
    public void reverseNode12() throws Exception {

        Node<String> node1 = new Node("1",null) ;

        ReverseNode reverseNode = new ReverseNode() ;
        reverseNode.reverseStackNode(node1);
    }

    @Test
    public void reverseNode13() throws Exception {

        Node<String> node1 = null ;

        ReverseNode reverseNode = new ReverseNode() ;
        reverseNode.reverseStackNode(node1);
    }


    /**
     * 头插法
     * @throws Exception
     */
    @Test
    public void reverseLinkNode() throws Exception {
        Node<String> node4 = new Node<>("4",null) ;
        Node<String> node3 = new Node<>("3",node4);
        Node<String> node2 = new Node<>("2",node3);
        Node<String> node1 = new Node("1",node2) ;

        ReverseNode reverseNode = new ReverseNode() ;
        reverseNode.reverseLinkNode(node1);

    }

    /**
     * 递归处理
     */
    @Test
    public void recNodeTest31(){
        Node<String> node4 = new Node<>("4",null) ;
        Node<String> node3 = new Node<>("3",node4);
        Node<String> node2 = new Node<>("2",node3);
        Node<String> node1 = new Node("1",node2) ;

        ReverseNode reverseNode = new ReverseNode() ;
        reverseNode.recNode(node1);
    }

}
