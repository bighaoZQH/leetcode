package com.bighao.linkedlist;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/1/14 11:10
 * <p>
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class 合并两个有序链表Easy21 {

    /**
     * 递归
     */
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val) {
            // 子问题
            l1.next = mergeTwoLists2(l1.next, l2);
            // 返回当前节点作为子问题的结果
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

    /**
     * 哨兵迭代
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();

        ListNode prev = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        // 因为是排好序的，所以当其中一个链表遍历结束后，就把另一个链表的剩余部分直接放到哨兵后面就行了
        prev.next = l1 == null ? l1 : l2;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);

        l1.next = l2;
        l2.next = l3;

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(3);
        ListNode a3 = new ListNode(4);
        a1.next = a2;
        a2.next = a3;

        ListNode listNode = mergeTwoLists2(l1, a1);
        ListNode temp = listNode;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

}
