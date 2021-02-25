package com.allen.algorithm.link;

/**
 * 链表是否有环
 *
 * Function:是否是环链表，采用快慢指针，一个走的快些一个走的慢些 如果最终相遇了就说明是环
 *   就相当于在一个环形跑道里跑步，速度不一样的最终一定会相遇。
 *
 * Created by xuguocai on 2020/12/29 10:31
 */
public class LinkLoop {
    public static class Node{
        private Object data ;
        public Node next ;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(Object data) {
            this.data = data ;
        }
    }

    /**
     * 判断链表是否有环： 走得快追上走得慢的
     * @param node
     * @return
     */
    public boolean isLoop(Node node){
        // 当前节点
        Node slow = node ;
        // 下一个节点
        Node fast = node.next ;

        while (slow.next != null){
            // 获取节点数据
            Object dataSlow = slow.data;
            // 获取节点数据
            Object dataFast = fast.data;

            //说明有环(慢的被快的追上，对象是一样的)
            if (dataFast == dataSlow){
                return true ;
            }

            //一共只有两个节点，但却不是环形链表的情况，判断NPE
            if (fast.next == null){
                return false ;
            }
            //slow走慢点  fast走快点 ，最后 fast 会追上 slow
            // slow 走一步
            slow = slow.next ;
            // fast 走两步
            fast = fast.next.next ;

            //如果走的快的发现为空 说明不存在环
            if (fast == null){
                return false ;
            }
        }
        return false ;
    }
}
