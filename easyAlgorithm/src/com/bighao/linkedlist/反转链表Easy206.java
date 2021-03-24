package com.bighao.linkedlist;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2020/12/3 14:56
 * <p>
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * <p>
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * 方法1：栈可以解决: 栈是先进后出的
 * 方法2：迭代或递归，
 * 步骤：当前节点设置为整个链表的第二个节点，
 * 1.缓存当前节点的next为tmp节点
 * 2.将该节点的next指向头结点，
 * 3.将头结点改成当前节点，当前节点改为tmp节点进行下一次遍历
 */
public class 反转链表Easy206 {


    /**
     * 递归 从后往前 进行反转
     */
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 这个n返回的永远都是原链表的最后一个节点
        ListNode n = reverseList2(head.next);
        // 将当前节点的下一个节点的next指向当前节点
        head.next.next = head;
        head.next = null;
        // 将原链表的最后一个节点作为链表头返回
        return n;
    }

    /**
     * 迭代 从前往后 进行反转
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            // 缓存当前节点的next节点
            ListNode tmp = cur.next;
            // 将当前节点的next指向上一个节点
            cur.next = prev;
            // 准备下一次迭代：将原当前节点作为上一个节点，将原当前节点的next作为新的当前节点
            prev = cur;
            cur = tmp;
        }
        return prev;
    }
    /*public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode n = head.next;
        head.next = null;
        while (n != null) {
            ListNode tmp = n.next;
            n.next = head;
            head = n;
            n = tmp;
        }
        return head;
    }*/

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
        ListNode listNode = reverseList(l1);

        ListNode temp = listNode;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}


