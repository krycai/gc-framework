package com.allen.algorithm.link.simple;

/**
 * Created by xuguocai on 2021/2/22 15:38  单链表
 */
public class NodeSimpleLink<T> {
    //声明头节点尾节点
    private Node<T> head;
    private Node<T> last;
    //链表的大小
    private int size;
    //计算被修改的次数
    private int modcount;

    public NodeSimpleLink(){
        //实例化头结点(哨兵节点)
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

    public static void test(){
        NodeSimpleLink<String> list = addNode();

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

    public static NodeSimpleLink addNode(){
        NodeSimpleLink<String> list = new NodeSimpleLink<>();

        //测试add
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");

        list.add(0,"newone");

        list.add(1,"newtwo");
        return list;
    }

    /**
     *  删除链表倒数第N个元素，如删除 倒数第三个  ，index = 3
     *
     *  先找要删除元素的上一个节点A，根据A找到下一个节点获取删除节点B和在下一个节点C，然后A的下一个节点指向C，B节点的下一个节点置空。
     * @param index
     */
    public static void deleteLastN(NodeSimpleLink nodeSimpleLink,int index){
//        NodeSimpleLink nodeSimpleLink = addNode();
        System.out.println(nodeSimpleLink);
        int length = nodeSimpleLink.size;
        // 根据倒数 第三个 位置，获得 正序 位置 ;
        int position = length - index ;
        Node tmp = nodeSimpleLink.head;
        // 获取节点A
        for (int i =0;i<position;i++){
            tmp = tmp.next;
        }
        if (tmp.next != null){
            // 节点B
            Node next = tmp.next;
            // 节点A 指向 节点C
            tmp.next = next.next;
            // 处理节点B的下一个节点置空
            next.setNext(null);
            System.out.println(next);

            System.out.println(nodeSimpleLink);
        }
    }

    public static void mergeLink(NodeSimpleLink<Integer> first,NodeSimpleLink<Integer> second){
        Node<Integer> headA = first.head.next;
        Node<Integer> lastA = first.last;

        Node<Integer> headB = second.head.next;
        Node<Integer> lastB = second.last;



    }


    public static void main(String[] args) {
//        test();
//        deleteLastN(addNode(),3);

        NodeSimpleLink<Integer> list = new NodeSimpleLink<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        NodeSimpleLink<Integer> second = new NodeSimpleLink<>();
        second.add(5);
        second.add(6);
        second.add(7);
        second.add(8);

        mergeLink(list,second);
    }
}
