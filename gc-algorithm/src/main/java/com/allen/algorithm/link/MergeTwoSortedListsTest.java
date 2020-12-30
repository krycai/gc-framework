package com.allen.algorithm.link;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by xuguocai on 2020/12/30 11:01
 */
public class MergeTwoSortedListsTest {

    MergeTwoSortedLists mergeTwoSortedLists ;
    @Before
    public void setUp() throws Exception {
        mergeTwoSortedLists = new MergeTwoSortedLists();
    }

    @Test
    public void mergeTwoLists() throws Exception {
        MergeTwoSortedLists.ListNode l1 = new MergeTwoSortedLists.ListNode(1) ;
        MergeTwoSortedLists.ListNode l1_2 = new MergeTwoSortedLists.ListNode(4);
        l1.next = l1_2 ;
        MergeTwoSortedLists.ListNode l1_3 = new MergeTwoSortedLists.ListNode(5) ;
        l1_2.next = l1_3 ;

        MergeTwoSortedLists.ListNode l2 = new MergeTwoSortedLists.ListNode(1) ;
        MergeTwoSortedLists.ListNode l2_2 = new MergeTwoSortedLists.ListNode(3) ;
        l2.next = l2_2 ;
        MergeTwoSortedLists.ListNode l2_3 = new MergeTwoSortedLists.ListNode(6) ;
        l2_2.next = l2_3 ;
        MergeTwoSortedLists.ListNode l2_4 = new MergeTwoSortedLists.ListNode(9) ;
        l2_3.next = l2_4 ;
        MergeTwoSortedLists.ListNode listNode = mergeTwoSortedLists.mergeTwoLists(l1, l2);


        MergeTwoSortedLists.ListNode node1 = new MergeTwoSortedLists.ListNode(1) ;
        MergeTwoSortedLists.ListNode node2 = new MergeTwoSortedLists.ListNode(1);
        node1.next = node2;
        MergeTwoSortedLists.ListNode node3 = new MergeTwoSortedLists.ListNode(3) ;
        node2.next= node3 ;
        MergeTwoSortedLists.ListNode node4 = new MergeTwoSortedLists.ListNode(4) ;
        node3.next = node4 ;
        MergeTwoSortedLists.ListNode node5 = new MergeTwoSortedLists.ListNode(5) ;
        node4.next = node5 ;
        MergeTwoSortedLists.ListNode node6 = new MergeTwoSortedLists.ListNode(6) ;
        node5.next = node6 ;
        MergeTwoSortedLists.ListNode node7 = new MergeTwoSortedLists.ListNode(9) ;
        node6.next = node7 ;
        System.out.println(node1.toString().equals(listNode.toString()));
//        Assert.assertEquals(node1.toString(),listNode.toString());


    }

    @Test
    public void mergeTwoLists2() throws Exception {

        MergeTwoSortedLists.ListNode l2 = new MergeTwoSortedLists.ListNode(1) ;
        MergeTwoSortedLists.ListNode l2_2 = new MergeTwoSortedLists.ListNode(3) ;
        l2.next = l2_2 ;
        MergeTwoSortedLists.ListNode l2_3 = new MergeTwoSortedLists.ListNode(6) ;
        l2_2.next = l2_3 ;
        MergeTwoSortedLists.ListNode l2_4 = new MergeTwoSortedLists.ListNode(9) ;
        l2_3.next = l2_4 ;
        MergeTwoSortedLists.ListNode listNode = mergeTwoSortedLists.mergeTwoLists(null, l2);

        System.out.println(listNode.toString());


    }

    @Test
    public void mergeTwoLists3() throws Exception {

        MergeTwoSortedLists.ListNode l2 = new MergeTwoSortedLists.ListNode(1) ;
        MergeTwoSortedLists.ListNode l2_2 = new MergeTwoSortedLists.ListNode(3) ;
        l2.next = l2_2 ;
        MergeTwoSortedLists.ListNode l2_3 = new MergeTwoSortedLists.ListNode(6) ;
        l2_2.next = l2_3 ;
        MergeTwoSortedLists.ListNode l2_4 = new MergeTwoSortedLists.ListNode(9) ;
        l2_3.next = l2_4 ;
        MergeTwoSortedLists.ListNode listNode = mergeTwoSortedLists.mergeTwoLists(l2, null);


        MergeTwoSortedLists.ListNode node1 = new MergeTwoSortedLists.ListNode(1) ;
        MergeTwoSortedLists.ListNode node2 = new MergeTwoSortedLists.ListNode(3);
        node1.next = node2;
        MergeTwoSortedLists.ListNode node3 = new MergeTwoSortedLists.ListNode(6) ;
        node2.next= node3 ;
        MergeTwoSortedLists.ListNode node4 = new MergeTwoSortedLists.ListNode(9) ;
        node3.next = node4 ;
        System.out.println(node1.toString().equals(listNode.toString()));
//        Assert.assertEquals(node1.toString(),listNode.toString());

    }
}
