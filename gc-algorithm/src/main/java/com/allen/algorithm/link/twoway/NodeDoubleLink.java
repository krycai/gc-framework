package com.allen.algorithm.link.twoway;

/**
 * Created by xuguocai on 2021/2/23 8:55 双节点链表结构
 */
public class NodeDoubleLink<T> {
    // 首节点
    private Node first;
    // 尾节点
    private Node last;
    // 链表长度
    private int size = 0;

    public NodeDoubleLink(){
        first = null;
        last = null;
    }

    // 插入首节点
    public void insertFirst(T data){
        Node<T> tNode = new Node<>(data);
        // 判断首节点是否位空
        if (first == null){
            last = tNode;
        }else {
            // 首节点不为空，则将新节点插入到首节点前面
            first.setPre(tNode);
            //把first节点往下移动
            tNode.setNext(first);
            // 跟前面的实现一样
//            first.pre = tNode;
//            tNode.next = first;
        }
        //把插入的节点作为新的节点
        first = tNode;
    }

    // 向尾节点插入数据
    public void insertLast(T data){
        Node<T> tNode = new Node<>(data);
        if (last == null){
            // 最后节点为空，则直接赋值为首节点 //todo 为什么
            first = tNode;
        }else {
            last.next = tNode;
            tNode.pre = last;
        }
        // //把最后个节点设置为最新的节点
        last = tNode;
    }

    // todo 判断双向链表为空的标识是首节点为空 ？
    public boolean isEmpty(){
        return first == null;
    }

    /**
     * 删除头节点时要去除两个指针,一个指向下个的next ,一个是next的previous指向前面的
     * @return
     */
    public Node deleteFirst(){
        if (first == null){
            throw new RuntimeException("链表数据不存在");
        }
        // 不为空
        Node node = first;
        // 判断首节点的下一个节点是否有数据
        if (first.next == null){
            // 没数据，即尾节点为空
            last = null;
        }else {
            // 既是中断首节点与下一个节点的联系
            first.next.pre = null;
        }
        // 将下一个节点作为首节点
        first = node.next;
        return node;
    }


    /**
     *  删除尾节点时要去除两个指针,一个指向上个的next ,一个是next的previous指向前面的
     * @return
     */
    public Node deleteLast(){
        if (first  == null){
            // last == null 尾节点没有，尾节点的上一个节点也没有，逆推 首节点也没有，故链表没有数据
            throw new RuntimeException("链表数据不存在");
        }
        // 尾节点存在
        Node node = last;
        // 若尾节点的上一个节点为空
        if (first.next == null) {
            // first.next为空说明尾节点为空
            last = null;
            //把第一个删除，此时第一个也就是最后一个
            first = null;
        }else {
            // 将前面的节点的下一个节点置空，也就是尾节点置空
            last.pre.next = null;
        }
        last = node.pre;

        return node;
    }

    /**
     * 根据元素删除：先查找元素对应的节点，再处理元素的上节点与下节点之间的关系
     * @param data
     * @return
     */
    public Node deleteByKey(T data){
        if (first == null){
            throw new RuntimeException("链表数据不存在");
        }
        // 从第一个节点开始判断
        Node current = first;
        while (current.value != data){
            if (current.next == null) {
                System.out.println("链表不存在这个元素："+data);
                return null;
            }
            current = current.next;
        }

        // 若存在，则删除
        if (current == first){
            // 若是首节点
            //指向下个就表示删除第一个
            first = first.next;
        }else {
            current.pre.next = current.next;
        }

        return current;
    }

    /**
     * 输出所有数据
     */
    public void display(){
        if (first == null){
            throw new RuntimeException("链表数据不存在");
        }
        Node current = first;
        while (current.next != null){
            System.out.println(current.value);
            current = current.next;
        }
        System.out.println(current.value);
        System.out.println("-----------------------------");
    }

    /**
     * 根据元素查找链表的数据：根据值是否相等遍历，再判断节点是否为空
     * @param value
     * @return
     */
    public Node findByValue(T value){
        if (first == null){
            throw new RuntimeException("链表数据不存在");
        }
        Node current = first;
        while (current.value != value){
            if (current.next == null){
                System.out.println("链表中没有次元素数据:"+value);
                return null;
            }

            current = current.next;
        }

        return current;
    }

    /**
     * 根据元素查找链表的数据:根据节点是否为空遍历，再比较对应的值
     * @param value
     * @return
     */
    public Node findByValue2(T value){
        Node current = first;
        while(current != null){
            if (current.value != value) {
                current = current.next;
            }else {
                break;
            }
        }
        if (current == null) {
            System.out.println("没找到");
            return null;
        }
        return current;
    }


    public Node findByPosition(int index){
        if (first == null){
            throw new RuntimeException("链表数据不存在");
        }
        Node current = first;
        //todo 为什么是index - 1，因为要使用遍历，让current指向下一个， 所以index - 1的下个node就是要找的值。 first已经算第一个元素，遍历一次，current指向下一个
        for (int i = 0;i<index -1;i++){
            System.out.print("遍历次数:"+i);
            current = current.next;
        }


        return current;
    }

    public static void main(String[] args) {

        NodeDoubleLink linkList = new NodeDoubleLink();
        linkList.insertFirst(21);
        linkList.insertFirst(22);
        linkList.insertFirst(23);
        linkList.insertLast(24);
        linkList.insertLast(25);
        linkList.insertLast(26);
        linkList.insertLast(27);

        linkList.display();
        // 按索引查找元素
        linkList.findByPosition(2).display();
        System.out.println("---查找-------------------------------------");

        linkList.findByValue2(25).display();

        System.out.println("--删除first-------------------------------------");

//        linkList.deleteFirst().display();
//        linkList.deleteFirst().display();
//        linkList.deleteFirst().display();
//        linkList.deleteFirst().display();

        System.out.println("-删除指定值---------------------------------------");
        linkList.deleteByKey(27).display();
//        linkList.deleteByKey(21).display();

        System.out.println("----------------------------------------");
        linkList.display();

    }

}
