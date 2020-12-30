package com.allen.algorithm.link;

/**
 * Created by xuguocai on 2020/12/30 9:52
 */
public class Node<T> {

    public T value;
    public Node<T> next ;


    public Node(T value, Node<T> next ) {
        this.next = next;
        this.value = value;
    }
}
