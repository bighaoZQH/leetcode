package com.bighao.linkedlist;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/8 13:44
 */
public class 对链表进行插入排序Medium147 {

    public ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        ListNode prev = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val < prev.val) {
                // 从头开始找位置
                ListNode temp = dummyHead;
                // s 2 3 1 4 5
                // 找到第一个temp.next.val >= cur.val的
                while (temp.next.val < cur.val) {
                    temp = temp.next;
                }
                // 先更新prev的next
                prev.next = cur.next;
                // 将cur插入到temp和temp.next的中间
                cur.next = temp.next;
                temp.next = cur;
                // cur继续往后移动
                cur = prev.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }

}
