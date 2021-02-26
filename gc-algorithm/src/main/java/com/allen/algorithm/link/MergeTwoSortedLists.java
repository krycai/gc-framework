package com.allen.algorithm.link;

/**
 * 合并两个排好序的链表
 *   每次比较两个链表的头结点，将较小结点放到新的链表，最后将新链表指向剩余的链表
 *
 * Created by xuguocai on 2020/12/30 10:52
 */
public class MergeTwoSortedLists {

    /**
     * 1. 声明一个头结点(哨兵节点)
     * 2. 将头结点的引用赋值给一个临时结点，也可以叫做下一结点。
     * 3. 进行循环比较，每次都将指向值较小的那个结点(较小值的引用赋值给 lastNode )。
     * 4. 再去掉较小值链表的头结点，指针后移。
     * 5. lastNode 指针也向后移，由于 lastNode 是 head 的引用，这样可以保证最终 head 的值是往后更新的。
     * 6. 当其中一个链表的指针移到最后时跳出循环。
     * 7. 由于这两个链表已经是排好序的，所以剩下的链表必定是最大的值，只需要将指针指向它即可。
     * 8. 由于 head 链表的第一个结点是初始化的0，所以只需要返回 0 的下一个结点即是合并了的链表。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0) ;
        ListNode lastNode = head ;

        while (l1 != null  && l2 != null){
            if (l1.currentVal < l2.currentVal){
                lastNode.next = l1 ;
                l1 = l1.next ;
            } else {
                lastNode.next = l2 ;
                l2 = l2.next ;
            }
            // 最后一个节点变化为下一个节点，位置更替
            lastNode =lastNode.next ;
        }
        System.out.println("新链表head:"+head);
        System.out.println("新链表lastNode:"+lastNode);

        if (l1 == null){
            // while 循环后最后的一个节点，这里是补全。
            lastNode.next = l2 ;
        }
        if (l2 == null){
            // while 循环后最后的一个节点，这里是补全。
            lastNode.next = l1 ;
        }

        System.out.println("新链表head.next:"+head.next);
        return head.next ;
    }

    /**
     * 两个数相加 ==》两个链表的节点同位置相加 == 》要求：个位，等于10的 取 0，向后节点加一
     * @param l1
     * @param l2
     * @return  todo 过于局限，当链表长度不相等的情况出现问题
     */
    public ListNode addTwoLists(ListNode l1, ListNode l2) {
        // 构造哨兵节点
        ListNode head = new ListNode(0) ;
        ListNode lastNode = head ;

        int tmp = 0;
        while (l1 != null  && l2 != null){
            int l1value = l1.currentVal;
            int l2value = l2.currentVal;
            // 相加
            tmp = l1value+l2value +tmp;

            int value = tmp % 10;

            if (value == 0){
                ListNode tmpNode = new ListNode(0) ;
                lastNode.next = tmpNode;
            }else {
                ListNode tmpNode = new ListNode(value) ;
                lastNode.next = tmpNode;
            }
            tmp = tmp /10;

            l1 = l1.next ;
            l2 = l2.next ;

            // 最后一个节点变化为下一个节点，位置更替
            lastNode =lastNode.next ;
        }

        System.out.println("新链表head.next:"+head.next);
        return head.next ;
    }

    /**
     * 两个数相加 ==> 两个链表的长度未知，每个元素的值都是 个位，多一个向后节点加一。 过程涉及的算法有： 求余运算符（%）  除法运算符（/）
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoLists2(ListNode l1, ListNode l2) {
        // 构造哨兵节点
        ListNode head = new ListNode(0) ;
        ListNode lastNode = head ;

        int tmp = 0;
        while (l1 !=null || l2 !=null || tmp != 0){
            if (l1 != null){
                tmp += l1.currentVal;
                l1 = l1.next;
            }
            if (l2 != null){
                tmp += l2.currentVal;
                l2 = l2.next;
            }
            // tmp%10 如 10%10 取余为0；8%10 取余为 8
            ListNode node = new ListNode(tmp%10);
            // tmp/10 如 10/10 取整为 1；9/10 取整为 0
            tmp = tmp/10; // 作为下一个节点的变量
            lastNode.next = node;
            lastNode = lastNode.next;
        }

        System.out.println("新链表:"+head.next);
        return head.next;
    }

    public static class ListNode {
        /**
         * 当前值
         */
        int currentVal;

        /**
         * 下一个节点
         */
        ListNode next;

        ListNode(int val) {
            currentVal = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "currentVal=" + currentVal +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();
//        ListNode one = new ListNode(1);
//        ListNode one2 = new ListNode(3);
//        one.next = one2;
//        ListNode one3 = new ListNode(5);
//        one2.next = one3;
//        ListNode one4 = new ListNode(8);
//        one3.next = one4;
//
//        ListNode two = new ListNode(1);
//        ListNode two2 = new ListNode(2);
//        two.next = two2;
//        ListNode two3 = new ListNode(4);
//        two2.next = two3;
//        ListNode two4 = new ListNode(6);
//        two3.next = two4;
//
//        mergeTwoSortedLists.mergeTwoLists(one,two);

        ListNode one = new ListNode(1);
        ListNode one2 = new ListNode(3);
        one.next = one2;
        ListNode one3 = new ListNode(4);
        one2.next = one3;

        ListNode two = new ListNode(4);
        ListNode two2 = new ListNode(7);
        two.next = two2;
        ListNode two3 = new ListNode(3);
        two2.next = two3;
        ListNode two4 = new ListNode(3);
        two3.next = two4;

        mergeTwoSortedLists.addTwoLists2(one,two);
    }
}
