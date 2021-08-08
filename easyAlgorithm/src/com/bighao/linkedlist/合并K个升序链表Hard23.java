package com.bighao.linkedlist;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/7 21:56
 */
public class 合并K个升序链表Hard23 {

    // 分治合并 跟归并排序一个思想
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        if (left > right) {
            return null;
        }

        int mid = (right - left) / 2 + left;
        ListNode leftMergedList = merge(lists, left, mid);
        ListNode rightMergedList = merge(lists, mid + 1, right);
        return mergeTwoList(leftMergedList, rightMergedList);
    }

    // 顺序合并
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode head = lists[0];
        for (int i = 1; i < lists.length; i++) {
            head = mergeTwoList(head, lists[i]);
        }

        return head;
    }

    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
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

        prev.next = l1 == null ? l2 : l1;
        return dummyHead.next;
    }

    public static void main(String[] args) {

    }

}
