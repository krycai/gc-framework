package com.allen.algorithm.link.twoway;

/**
 * Created by xuguocai on 2021/2/23 9:44  双向链表节点
 */
public class Node<T> {
    // 数据data域
    public T value;
    // 后继指针域
    public Node<T> next ;
    // 前驱指针域
    public Node<T> pre ;

    public Node(){

    }

    public Node(T value) {
        this.value = value;
        pre = null;
        next = null;
    }

    public Node(T value, Node<T> next, Node<T> pre) {
        this.value = value;
        this.next = next;
        this.pre = pre;
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

    public Node<T> getPre() {
        return pre;
    }

    public void setPre(Node<T> pre) {
        this.pre = pre;
    }

    public void display(){
        System.out.println("当前值："+value);
    }

    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                ", pre=" + pre +
                '}';
    }
}
