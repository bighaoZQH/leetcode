package com.bighao.linkedlist;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2020/12/6 23:02
 *
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 *
 * 迭代 或 递归
 */
public class 两两交换链表中的节点Medium24 {

    /**
     * 递归 从尾往头 两两交换
     */
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 将当前局部链表头的 next 作为 新的局部的链表头
        ListNode newHead = head.next;
        // 递归得到当前局部链表头的next节点
        head.next = swapPairs2(newHead.next);
        // 将新的链表头的next指向当前节点
        newHead.next = head;
        // 返回最新的局部链表头
        return newHead;
    }

    /**
     * 迭代，从头往尾 两两交换
     */
    public static ListNode swapPairs(ListNode head) {
        // 临时头节点，挂在原链表头的前面
        ListNode tempHead = new ListNode();
        tempHead.next = head;
        // 当前节点
        ListNode cur = head;
        // 当前节点的前置节点
        ListNode prev = tempHead;
        // 如果cur 以及 cur的next不为空，才能进行两两交换
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            // 下面的两行代码进行两两交换
            cur.next = next.next;
            next.next = cur;
            prev.next = next;
            // 准备下一次迭代：更新前置节点为当前节点
            prev = cur;
            // 准备下一次迭代：更新当前节点为当前节点的next
            cur = cur.next;
        }
        head = tempHead.next;
        // gc
        tempHead = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        ListNode listNode = swapPairs(l1);

        ListNode temp = listNode;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}


