package com.allen.algorithm.link;

/**
 * Created by xuguocai on 2021/2/22 15:38  单链表
 */
public class SimpleNode<T> {
    //声明头节点尾节点
    private Node<T> head;
    private Node<T> last;
    //链表的大小
    private int size;
    //计算被修改的次数
    private int modcount;

    public SimpleNode(){
        //实例化头结点
        head = new Node<T>();
        last = head;
    }

    @Override
    public String toString() {
        return "SimpleNode{" +
                "head=" + head +
                ", last=" + last +
                ", size=" + size +
                ", modcount=" + modcount +
                '}';
    }

    /**
     *返回单链表中存储的元素总数
     */
    public int size() {
        return size;
    }

    /**
     * 添加元素
     * @param e 数据
     */
    public void add(T e) {
        //以e实例化一个节点
        Node<T> node = new Node<T>(e);
        //往尾节点后追加节点，//todo 以下两步能否直接等于一步操作 ：不能，最后一个节点是动态变化的，每增加一个元素，最后节点都会变成最新的节点
        last.setNext(node);
        //该节点设为最后一个节点
        last = node;
        size++;
        modcount++;
    }

    /**
     *指定位置插入元素,返回插入的节点数据
     */
    public T add(int index, T e) {
        if (index < 0 || index > size - 1){
            return null;
        }
        //实例化一个节点
        Node<T> node = new Node<T>(e);
        //找到插入的原节点
        Node<T> oldNode = get(index);
        //当索引为0时
        if (index == 0) {
            // 头节点连接新节点
            head.setNext(node);
        } else {
            //找到插入节点的上一个
            Node<T> bNode = get(index - 1);
            bNode.setNext(node);
        }
        // 新节点 连接 老节点
        node.setNext(oldNode);
        size++;
        modcount++;
        return oldNode.getValue();
    }

    /**
     *获取指定索引位置的节点对象
     */
    public Node<T> get(int index) {
        // 判断链表的长度
        if (index < 0 || index > size - 1){
            return null;
        }
        //将头结点的下一个节点赋给Node
        Node<T> node = head.getNext();
        // 链表长度为 4，index 为2 ，遍历循环，当i= 1时既是获取的节点。同时覆盖前面获取的节点
        for (int i = 0; i < index; i++) {
            //获取node的下一个节点
            node = node.getNext();
        }
        return node;
    }

    /**
     *获取指定索引位置的数据
     */
    public T getValue(int index) {
        if (index < 0 || index > size - 1){
            return null;
        }
        Node<T> node = get(index);
        return node.getValue();
    }

    /**
     *根据链表的索引，找到索引节点，删除并返回删除的节点e数据
     */
    public T delete(int index) {
        // 判断链表的长度
        if (index < 0 || index > size - 1){
            return null;
        }
        //当索引为1，令头结点的下一个节点为头结点 //todo 为第一个元素的时候怎么处理 ?  第一个元素既是首节点的第一个元素，不是首节点
        if (index == 0) {
            // 获取首节点的下一个节点
            Node<T> node = head.getNext();
            // 头节点的下一个节点为首节点的下一个节点的下一个节点
            head.setNext(node.getNext());
            return node.getValue();
        }
        //获取要删除节点的前一个节点
        Node<T> bNode = get(index - 1);
        //获取要删除的节点
        Node<T> Node = bNode.getNext();
        //获取要删除节点的下一个节点
        Node<T> nNode = Node.getNext();
        //删除该节点
        bNode.setNext(nNode);
        //清除Node的下一个节点
        Node.setNext(null);
        size--;
        modcount++;
        //返回节点中的数据域
        return Node.getValue();
    }

    /**
     *修改指定位置的数据域并返回修改后的数据
     */
    public T set(int index, T e) {
        if (index < 0 || index > size - 1){
            return null;
        }
        //获取指定位置的原节点
        Node<T> node = get(index);
        node.setValue(e);
        modcount++;
        return node.getValue();
    }

    public static void main(String[] args) {
        SimpleNode<String> list = new SimpleNode<>();

        //测试add
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        System.out.println("添加："+list);
        list.add(0,"newone");
        System.out.println("插入:"+list);
        list.add(1,"newtwo");
        System.out.println("插入|插入："+list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.getValue(i)+" ");
        }

        //测试set
        System.out.println();
        list.set(0, "111");
        list.set(1, "222");
        System.out.println(list.getValue(0) + "   " + list.getValue(1));

        //测试delete
        System.out.println();
        System.out.println("删除前的链表="+list);
        System.out.println(list.delete(1));
        System.out.println("删除后的链表:"+list);
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            Node temp = list.get(i);
            if (temp !=null){
                System.out.print(list.getValue(i)+" ");
            }
        }
    }
}
