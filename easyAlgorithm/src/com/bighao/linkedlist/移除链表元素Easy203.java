package com.bighao.linkedlist;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/7 4:23
 */
public class 移除链表元素Easy203 {

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode sentinel = new ListNode();
        sentinel.next = head;

        ListNode pre = sentinel;
        ListNode cur = sentinel.next;
        while (cur != null) {
            // 如果需要删除当前节点
            if (cur.val == val) {
                pre.next = cur.next;
                cur.next = null;
            } else {
                // 不需要删除当前节点的话，更新pre节点，否则不更新
                pre = cur;
            }
            // cur节点更新成pre.next
            cur = pre.next;
        }
        return sentinel.next;
    }


}


