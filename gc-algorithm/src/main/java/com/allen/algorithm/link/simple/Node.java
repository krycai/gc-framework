package com.allen.algorithm.link.simple;

/**
 * Created by xuguocai on 2020/12/30 9:52  单向节点链表由节点构成
 */
public class Node<T> {

    public T value;  //数据data
    public Node<T> next ;  //指向下一个节点

    public Node(){}

    public Node(T value, Node<T> next ) {
        this.next = next;
        this.value = value;
    }

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
